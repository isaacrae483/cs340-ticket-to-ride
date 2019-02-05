package edu.byu.cs340.tickettoride.Client;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData.commandType;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IServer;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerProxy implements IServer {
    private static ServerProxy _instance;
    private ServerProxy(){

    }
    public static ServerProxy instance(){
        if (_instance == null){
            _instance = new ServerProxy();
        }
        return _instance;
    }

    private ServerCommandData data;
    private ClientCommunicator communicator = new ClientCommunicator();

    @Override
    public LoginResult login(Username username, Password password) {
        data = new ServerCommandData(commandType.LOGIN, username, password);
        return communicator.send(data, LoginResult.class);
    }

    @Override
    public LoginResult register(Username username, Password password) {
        data = new ServerCommandData(commandType.REGISTER, username, password);
        return communicator.send(data, LoginResult.class);
    }

    @Override
    public JoinGameResult joinGame(Username username, ID id) {
        data = new ServerCommandData(commandType.JOINGAME, username, id);
        return communicator.send(data, JoinGameResult.class);
    }

    @Override
    public CreateGameResult createGame(Username username) {
        data = new ServerCommandData(commandType.CREATEGAME, username);
        return communicator.send(data, CreateGameResult.class);
    }

    @Override
    public List<ClientCommandData> getCommands(Username username) {
        return null;
    }
}
