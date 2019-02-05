package edu.byu.cs340.tickettoride.server;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.server.Model.Services.CreateGameService;
import edu.byu.cs340.tickettoride.server.Model.Services.JoinGameService;
import edu.byu.cs340.tickettoride.server.Model.Services.LoginService;
import edu.byu.cs340.tickettoride.server.Model.Services.RegisterService;
import edu.byu.cs340.tickettoride.server.Observers.IClientObservable;
import edu.byu.cs340.tickettoride.server.Observers.IClientObserver;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IServer;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;
import edu.byu.cs340.tickettoride.server.Model.Services.LoginService;

public class ServerFacade implements IServer, IClientObservable{
    private List<IClientObserver> observers = new ArrayList<>();

    public static final ServerFacade SINGLETON = new ServerFacade();

    private ServerFacade () {};

    @Override
    public LoginResult login(Username username, Password password) {
        return LoginService.login(username, password);
    }

    @Override
    public LoginResult register(Username username, Password password) {
        return RegisterService.register(username, password);
    }

    @Override
    public JoinGameResult joinGame(Username username, ID id) {
        return JoinGameService.joinGame(username, id);
    }

    @Override
    public CreateGameResult createGame(Username username) {
        CreateGameResult result = CreateGameService.createGame(username);
        ID id = result.getGame().getId();
        for (IClientObserver observer : observers){
            //observer.OnNewGame(/*pull game ID from game map when new game is created.*/);
            observer.OnNewGame(id);
        }
        return result;
    }

    @Override
    public List<ClientCommandData> getCommands(Username username) {
        return null;
    }

    @Override
    public void AddObserver(IClientObserver observer) {
        observers.add(observer);
    }
}
