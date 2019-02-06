package edu.byu.cs340.tickettoride.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.byu.cs340.tickettoride.server.Model.Services.CreateGameService;
import edu.byu.cs340.tickettoride.server.Model.Services.JoinGameService;
import edu.byu.cs340.tickettoride.server.Model.Services.LoginService;
import edu.byu.cs340.tickettoride.server.Model.Services.RegisterService;
import edu.byu.cs340.tickettoride.server.Observers.IClientObservable;
import edu.byu.cs340.tickettoride.server.Observers.IClientObserver;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.JoinGameCommand;
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
    private Set<IClientObserver> observers = new HashSet<>();

    public static final ServerFacade SINGLETON = new ServerFacade();

    private ServerFacade () {}

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
        JoinGameResult res =  JoinGameService.joinGame(username, id);
        for (IClientObserver observer : observers){
            observer.OnPlayerJoin(username, id);
        }
        return res;
    }

    @Override
    public CreateGameResult createGame(Username username) {
        CreateGameResult result = CreateGameService.createGame(username);
        if (result.getSuccess()) {
            Game game = result.getGame();
            for (IClientObserver observer : observers) {
                observer.OnNewGame(game);
            }
        }
        return result;
    }

    @Override
    public List<ClientCommandData> getCommands(Username username) {
        return ServerModel.SINGLTON.getCommandList().GetCommands(username);
    }

    @Override
    public void AddObserver(IClientObserver observer) {
        observers.add(observer);
    }

    //FOR TESTING PURPOSES ONLY
    public int NumObservers() {
        return observers.size();
    }
}
