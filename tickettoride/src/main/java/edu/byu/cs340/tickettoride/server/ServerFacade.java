package edu.byu.cs340.tickettoride.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.byu.cs340.tickettoride.server.Model.Services.CreateGameService;
import edu.byu.cs340.tickettoride.server.Model.Services.JoinGameService;
import edu.byu.cs340.tickettoride.server.Model.Services.LoginService;
import edu.byu.cs340.tickettoride.server.Model.Services.RegisterService;
import edu.byu.cs340.tickettoride.server.Model.Services.StartGameService;
import edu.byu.cs340.tickettoride.server.Observers.IClientObservable;
import edu.byu.cs340.tickettoride.server.Observers.IClientObserver;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IServer;
import edu.byu.cs340.tickettoride.shared.Result.BeginPlayingResult;
import edu.byu.cs340.tickettoride.shared.Result.ChatResult;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnCardResult;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerFacade implements IServer, IClientObservable{
    private Set<IClientObserver> observers = new HashSet<>();

    public static final ServerFacade SINGLETON = new ServerFacade();

    private ServerFacade () {}

    /**
     * pre: given username and password have been registered with the server
     * post: the LoginResult contains a 'true' result
     * If the given parameters represent a given user in the server,
     * log them in.
     * @param username the username of the user
     * @param password the user's password
     * @return the result of the login (contains success of result)
     */
    @Override
    public LoginResult login(Username username, Password password) {
        return LoginService.login(username, password);
    }

    /**
     * pre: the username does not exist within the server already
     * post: the LoginResult contains a 'true' result
     * given that the user has not already been registered, logs them in and adds them to the
     * server.
     * @param username the new user's username
     * @param password the new user's password
     * @return the result of the register (contains success of result)
     */
    @Override
    public LoginResult register(Username username, Password password) {
        return RegisterService.register(username, password);
    }

    /**
     * pre: the username exists in the database AND the id is of a valid game AND the game is not started
     *      AND the game is not full AND the user has not already joined the game
     * post: the JoinGameResult contains a true result
     * Lets a player join a game that they are not already in and is not full
     * @param username the username of the player joining
     * @param id the ID of the game
     * @return a JoinGameResult containing information about the game as well as the success of the operation
     */
    @Override
    public JoinGameResult joinGame(Username username, ID id) {
        JoinGameResult res =  JoinGameService.joinGame(username, id);
        if (res.getSuccess()) {
            for (IClientObserver observer : observers) {
                observer.OnPlayerJoin(res.getPlayer(), id);
            }
        }
        return res;
    }

    /**
     * pre: the username exists in the database
     * post: a game is created and the ID is in the CreateGameResult
     * Creates a game
     * @param username the user creating the game
     * @return a CreateGameResult containing information about the game and whether it succeeded
     */
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

    /**
     * pre: username exists on the server
     * post: the ClientCommandList contains all of the commands that the player has not gotten
     * Gets the commands waiting for a user
     * @param username the user requesting commands
     * @return a list of all the commands a user needs to execute
     */
    @Override
    public ClientCommandList getCommands(Username username) {
        return ServerModel.SINGLETON.getCommandList().GetCommands(username);
    }

    /**
     * pre: username is a valid username AND username is the leader of game with ID id AND game
     *      with ID id is a valid game
     * post: The game on the server is started and the StartGameResult returns true
     * starts a game that the player started
     * @param username the player who is trying to start the game
     * @param id the id of the game to start
     * @return a StartGameResult detailing whether the operation worked
     */
    @Override
    public StartGameResult startGame(Username username, ID id) {
        StartGameResult res = new StartGameService().startGame(username, id);
        if (res.getSuccess()) {
            for(IClientObserver o : observers) {
                o.OnGameStart(id);
            }
        }
        return res;
    }

    @Override
    public BeginPlayingResult beginPlaying(Username username, ID game) {
        return null;
    }

    @Override
    public DrawTicketsResult drawTickets(Username username, ID game) {
        return null;
    }

    @Override
    public ChatResult chat(Username username, String message, ID game) {
        return null;
    }

    @Override
    public ReturnCardResult returnCards(Username username, DestCard card, ID game) {
        return null;
    }

    /**
     * pre: observer is not null
     * post: observer is added to the facade
     * adds a client observer to notify when the facade changes
     * @param observer the observer to add
     */
    @Override
    public void AddObserver(IClientObserver observer) {
        observers.add(observer);
    }

    /**
     *  FOR TESTING PURPOSES ONLY
     * @return the number of observers.
     */
    public int NumObservers() {
        return observers.size();
    }

    /**
     *REsets the facade. FOR TESTING PURPOSES ONLY
     */
    public void Reset() {
        observers = new HashSet<>();
        ServerModel.SINGLETON.Reset();
    }
}
