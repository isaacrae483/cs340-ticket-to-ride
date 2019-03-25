package edu.byu.cs340.tickettoride.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.byu.cs340.tickettoride.server.Model.Services.ChatService;
import edu.byu.cs340.tickettoride.server.Model.Services.CreateGameService;
import edu.byu.cs340.tickettoride.server.Model.Services.DestCardService;
import edu.byu.cs340.tickettoride.server.Model.Services.JoinGameService;
import edu.byu.cs340.tickettoride.server.Model.Services.LoginService;
import edu.byu.cs340.tickettoride.server.Model.Services.RegisterService;
import edu.byu.cs340.tickettoride.server.Model.Services.RouteClaimedService;
import edu.byu.cs340.tickettoride.server.Model.Services.StartGameService;
import edu.byu.cs340.tickettoride.server.Observers.Event.AddCardsEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.AddGameEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.ChatEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.DestDeckSizeEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.FaceUpCardEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.LastTurnEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.PlayerJoinedGameEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.RouteClaimedEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.StartGameEvent;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.EventEmitter;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Interface.IServer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.ChatResult;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceDownCardResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceUpCardResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnTicketResult;
import edu.byu.cs340.tickettoride.shared.Result.RouteClaimedResult;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * The ServerFacade class holds all operations that the client needs to perform on the server.
 * It is a singleton with the instance stored as the static SINGLETON field.
 */
public class ServerFacade extends EventEmitter implements IServer {

    /**
     * This class is a singleton, so here is the instance
     */
    public static final ServerFacade SINGLETON = new ServerFacade();

    /**
     * Only to be called internally since this class is a singleton
     */
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
            this.emitEvent(new PlayerJoinedGameEvent(res.getPlayer(), id));
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
            this.emitEvent(new AddGameEvent(game));
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
            this.emitEvent(new StartGameEvent(id));
        }
        return res;
    }


    /**
     * pre: username is a valid user that is a part of game
     * post: the DrawTickets results ha success set to true and contains at most three cards from
     *  the deck; less if the deck is nearly empty or 0 if empty. Those cards are not longer stored
     *  on the server.
     * @param username the user drawing the tickets
     * @param game the game the user wishes to drawTickets tickets from
     * @return a DrawTicketsResult containing the success of the operation
     *  as well as the cards drawn if the operation was a success
     */
    @Override
    public DrawTicketsResult drawTickets(Username username, ID game) {
        DrawTicketsResult res = new DestCardService().drawTickets(username, game);
        if (res.getSuccess()) {
            int cardsDrawn = res.getCards().size();
            this.emitEvent(new DestDeckSizeEvent(game, cardsDrawn,
                    ServerModel.SINGLETON.getMapStartedGames().getGame(game).getPlayer(username)));
        }
        return res;
    }

    /**
     * pre: ChatMessage contains a valid user that is a part of the specified game (in the chat message).
     *  the message is not null
     * post: The chat is added to the game and a command saying so is given to each other user in the game.
     * @param message the ChatMessage sent to the server
     * @return a ChatResult with the success of the operation
     */
    @Override
    public ChatResult chat(ChatMessage message) {
        ChatResult res = new ChatService().chat(message.getUser(), message.getMessage(),
                message.getGame());

        if (res.getSuccess()) {
            this.emitEvent(new ChatEvent(message));
        }

        return res;
    }

    @Override
    public DrawFaceUpCardResult drawFaceUpCard(Integer index, Username player, ID game) {
        return null;
    }

    @Override
    public DrawFaceDownCardResult drawFaceDownCard(Username player, ID game) {
        return null;
    }

    public void playerDrew(Player p, List<TrainCard> cards, ID game) {
        this.emitEvent(new AddCardsEvent(cards, p, game));
    }

    public void SetFaceUpCard(Game game, TrainCard card, int pos) {
        this.emitEvent(new FaceUpCardEvent(card, pos, game.getId()));
    }

    /**
     * pre: Username is a valid user and a part of the game specified by the ID.
     * post: the card is added to the end of the destination card deck for the game.
     * @param username the user returning tickets
     * @param card the card that the user is returning
     * @param game the game the user is playing
     * @return a ReturnTicketResult conatining the success of the operation and the ticket returned.
     */
    @Override
    public ReturnTicketResult returnTickets(Username username, DestCard card, ID game) {
        ReturnTicketResult res = new DestCardService().returnTickets(username, card, game);
        if (res.getSuccess()) {
            this.emitEvent(new DestDeckSizeEvent(game, -1,
                    ServerModel.SINGLETON.getMapStartedGames().getGame(game).getPlayer(username)));
        }
        return res;
    }

    @Override
    public RouteClaimedResult routeClaimed(Route route, Username player, ID game) {
        RouteClaimedResult res = new RouteClaimedService().routeClaimed(route, player, game);
        if (res.getSuccess()) {
            this.emitEvent(new RouteClaimedEvent(route, res.getPlayer(), game));
        }
        return res;
    }

    public void LastTurn(ID game) {
        this.emitEvent(new LastTurnEvent(game));
    }


    /**
     *  FOR TESTING PURPOSES ONLY
     *  pre: none
     *  post: returns the number of observers
     * @return the number of observers.
     */
    public int NumObservers() {
        return this.countObservers();
    }

    /**
     *Resets the facade. FOR TESTING PURPOSES ONLY
     * pre: none
     * post: the Serverfacade and ServerModel are in the state as if their constructors were just
     *  called
     */
    public void Reset() {
        this.deleteObservers();
        ServerModel.SINGLETON.Reset();
    }
}
