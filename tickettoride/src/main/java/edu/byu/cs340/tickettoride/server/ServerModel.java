package edu.byu.cs340.tickettoride.server;

import java.util.List;
import java.util.Map;

import edu.byu.cs340.tickettoride.server.Model.CommandList;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandFactory;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.DAOFactory;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.GameDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.UserDAO;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.MapUsers;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerModel {
    public static final ServerModel SINGLETON = new ServerModel();
    private ServerModel(){}

/*
    public CommandList getCommandList() { return commandList; }

    private CommandList commandList = new CommandList();

    public MapGames getMapNewGames() {
        return mapGames;
    }

    public MapGames getMapStartedGames() {
        return startedGames;
    }

    public MapUsers getMapUsers() {
        return mapUsers;
    }
*/
    //for testing purposes only
    public void Reset() {
    }

    public Game getStartedGame(ID id) {
        return startedGames.getGame(id);
    }

    public Game getGame(ID id) {
        return unstartedGames.getGame(id);
    }

    public void updateGame(Game game, ServerCommandData data) {
        if (dataBaseActive) {
            String gameData = DatabaseSerializer.toString(game);
            String commandData = DatabaseSerializer.toString(data);
            gameDAO.updateGame(commandData, gameData, game.getId().toString());
        }
        startedGames.replace(game);
        unstartedGames.replace(game);
    }


    public Player getPlayerInGame(ID game, Username username) {
        return startedGames.getPlayer(game, username);
    }

    public int getGameTurn(ID game) {
        return startedGames.getGame(game).getPlayerTurnIndex();
    }

    public void AddFactory(DAOFactory factory, int deltas)  {
        this.gameDAO = factory.generateGameDAO(deltas);
        this.userDAO = factory.generateUserDAO();

        Map<String, String> users = userDAO.getUsers();
        for (Map.Entry<String, String> entry: users.entrySet()) {
            this.users.addUser(entry.getKey(), entry.getValue());
            String commandList = userDAO.getCommands(entry.getKey());
            ClientCommandList list = (ClientCommandList) DatabaseSerializer.fromString(commandList);
            try {
                this.commandList.AddCommands(new Username(entry.getKey()), list);
            }
            catch (Username.InvalidUserNameException e) {
                e.printStackTrace();
            }
        }

        List<String> ids = gameDAO.getIDs();
        for (String id : ids) {
            String gameData = gameDAO.getGame(id);
            if (gameData != null) {
                Game game = (Game) DatabaseSerializer.fromString(gameData);
                if (game == null) {
                    continue;
                }
                if (game.isGameStarted()) {
                    startedGames.addGame(game);
                } else {
                    unstartedGames.addGame(game);
                }
            }

            List<String> commands = gameDAO.getCommands(id);
            if (commands != null) {
                for (String command : commands) {
                    ServerCommandData commandData = (ServerCommandData) DatabaseSerializer.fromString(command);
                    ServerCommandFactory.Generate(commandData).execute();
                }
            }
        }
        dataBaseActive = true;
    }

    public User getUser(Username username) {
        return users.getUser(username);
    }


    public ClientCommandList getCommands(Username username) {
        String emptyCommands = DatabaseSerializer.toString(new ClientCommandList());
        if (dataBaseActive)
            userDAO.updateCommandQueue(username.getUsername(), emptyCommands);
        return commandList.GetCommands(username);
    }

    public List<Game> getUnstartedGames() {
        return unstartedGames.getAsListGames();
    }

    public void addUser(Username username, Password password) {
        if (dataBaseActive)
            userDAO.register(username.getUsername(), password.getPassword());
        users.addUser(new User(username, password));
    }

    public void startGame(ID id) {
        startedGames.addGame(unstartedGames.getGame(id));
        unstartedGames.remove(id);
    }

    public void AddCommand(Username username, ClientCommandData data) {
        commandList.AddCommand(username, data);
        if (dataBaseActive) {
            ClientCommandList list = commandList.GetCommandsNoClear(username);
            String listData = DatabaseSerializer.toString(list);
            userDAO.updateCommandQueue(username.getUsername(), listData);
        }
    }

    public void addGame(Game game) {
        unstartedGames.addGame(game);

        String gameInfo = DatabaseSerializer.toString(game);
        String command = DatabaseSerializer.toString(new ServerCommandData(
                ServerCommandData.commandType.CREATEGAME, game.GetLeader().getPlayerName()));
        String id = game.getId().getId();
        if (dataBaseActive) {
            gameDAO.updateGame(command, gameInfo, id);
        }
    }

    private UserDAO userDAO;
    private GameDAO gameDAO;

    private MapGames unstartedGames = new MapGames();
    private MapGames startedGames = new MapGames();
    private MapUsers users = new MapUsers();
    private CommandList commandList = new CommandList();
    boolean dataBaseActive = false;
}
