package edu.byu.cs340.tickettoride.Client;

import edu.byu.cs340.tickettoride.shared.Commands.CommandData;
import edu.byu.cs340.tickettoride.shared.Commands.CommandData.commandType;
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

    private CommandData data;
    private ClientCommunicator communicator = new ClientCommunicator();

    @Override
    public LoginResult login(Username username, Password password) {
        data = new CommandData(commandType.LOGIN, username.getUsername(), password.getPassword());
        return communicator.send(data, LoginResult.class);
    }

    @Override
    public LoginResult register(Username username, Password password) {
        data = new CommandData(commandType.REGISTER, username.getUsername(), password.getPassword());
        return communicator.send(data, LoginResult.class);
    }

    @Override
    public JoinGameResult joinGame(Username username, ID id) {
        data = new CommandData(commandType.JOINGAME, username.getUsername(), id.getId());
        return communicator.send(data, JoinGameResult.class);
    }

    @Override
    public CreateGameResult createGame(Username username) {
        data = new CommandData(commandType.CREATEGAME, username.getUsername(), null);
        return communicator.send(data, CreateGameResult.class);
    }
}
