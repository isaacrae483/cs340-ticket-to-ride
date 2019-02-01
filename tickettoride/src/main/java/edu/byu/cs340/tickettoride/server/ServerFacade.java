package edu.byu.cs340.tickettoride.server;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IServer;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerFacade implements IServer{

    public static final ServerFacade SINGLETON = new ServerFacade();

    private ServerFacade () {};

    @Override
    public LoginResult login(Username username, Password password) {
        return null;
    }

    @Override
    public LoginResult register(Username username, Password password) {
        return null;
    }

    @Override
    public JoinGameResult joinGame(Username username, ID id) {
        return null;
    }

    @Override
    public CreateGameResult createGame(Username username) {
        return null;
    }
}
