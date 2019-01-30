package edu.byu.cs340.tickettoride.Client;

import edu.byu.cs340.tickettoride.shared.Commands.CommandData;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IServer;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

import edu.byu.cs340.tickettoride.shared.Commands.CommandData.commandType;

public class ServerProxy implements IServer {

    private CommandData data;
    private ClientCommunicator communicator = new ClientCommunicator();

    @Override
    public LoginResult login(Username username, Password password) {
        data = new CommandData(commandType.LOGIN, username, password);
        return communicator.send(data, LoginResult.class);
    }

    @Override
    public LoginResult register(Username username, Password password) {
        data = new CommandData(commandType.REGISTER, username, password);
        return communicator.send(data, LoginResult.class);
    }

    @Override
    public JoinGameResult joinGame(Username username, ID id) {
        data = new CommandData(commandType.JOINGAME, username, id);
        return communicator.send(data, JoinGameResult.class);
    }

    @Override
    public CreateGameResult createGame(Username username) {
        data = new CommandData(commandType.CREATEGAME, username);
        return communicator.send(data, CreateGameResult.class);
    }
}
