package edu.byu.cs340.tickettoride.server;

import edu.byu.cs340.tickettoride.server.Model.CommandList;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.MapUsers;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerModel {
    public static final ServerModel SINGLETON = new ServerModel();
    private ServerModel(){}
    private MapGames mapGames = new MapGames();
    private MapGames startedGames = new MapGames();
    private MapUsers mapUsers = new MapUsers();

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

    //for testing purposes only
    public void Reset() {
        mapGames = new MapGames();
        mapUsers = new MapUsers();
    }

    public Game getStartedGame(ID id) {
        return startedGames.getGame(id);
    }

    public Player getPlayerInGame(ID game, Username username) {
        return startedGames.getPlayer(game, username);
    }

    public int getGameTurn(ID game) {
        return startedGames.getGame(game).getPlayerTurnIndex();
    }

//implement methods for login, register, joinGame, newGame here.
}
