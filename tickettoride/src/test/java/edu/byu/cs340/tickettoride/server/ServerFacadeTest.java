package edu.byu.cs340.tickettoride.server;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Board.Routes;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.ChatResult;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnTicketResult;
import edu.byu.cs340.tickettoride.shared.Result.RouteClaimedResult;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

import static org.junit.Assert.*;

public class ServerFacadeTest {

    private Username user;
    private Username fakeUser;
    private Username user2;
    private Password password;
    private Password fakePassword;
    private Password password2;
    private ServerModel model;
    private ServerFacade facade;
    private Game game;
    private ID id;

    @Before
    public void setup() {
        try {
            user = new Username("UserTEST");
            fakeUser = new Username("FAKE");
            user2 = new Username("UserTEST2");

            password = new Password("****");
            fakePassword = new Password("password");
            password2 = new Password("DISBEPASSWURD");
        }
        catch (Username.InvalidUserNameException e) {
            System.out.println(e.getMessage());
        }
        catch (Password.InvalidPasswordException e) {
            System.out.println(e.getMessage());
        }

        model = ServerModel.SINGLETON;
        facade = ServerFacade.SINGLETON;
        facade.Reset();
    }

    @Test
    public void login() {
        this.register();

        LoginResult res = facade.login(user, password);

        assertTrue(res.getSuccess());
        assertEquals(2, facade.NumObservers());

        LoginResult fail = facade.login(user, fakePassword);

        assertFalse(fail.getSuccess());
        assertEquals(2, facade.NumObservers());

        fail = facade.login(fakeUser, fakePassword);

        assertFalse(fail.getSuccess());
        assertEquals(2, facade.NumObservers());
        assertNull(model.getMapUsers().getUser(fakeUser));

        fail = facade.login(null, null);

        assertFalse(fail.getSuccess());
    }

    @Test
    public void register() {
        assertEquals(0, facade.NumObservers());
        assertNull(model.getMapUsers().getUser(user));

        LoginResult res = facade.register(user, password);

        assertTrue(res.getSuccess());
        assertNotNull(model.getMapUsers().getUser(user));
        assertEquals(1, facade.NumObservers());

        res = facade.register(null, null);
        assertFalse(res.getSuccess());

        res = facade.register(user2, password2);
        assertTrue(res.getSuccess());
        assertNotNull(model.getMapUsers().getUser(user));
        assertEquals(2, facade.NumObservers());
    }

    @Test
    public void joinGame() {
        this.createGame();

        facade.joinGame(user2, id);

        ClientCommandList commands = model.getCommandList().GetCommands(user);
        assertEquals(1, commands.size());
        ClientCommandData command = commands.get(0);

        assertEquals(ClientCommandData.CommandType.INCREMENTPLAYER, command.type);
        assertNull(command.game);
        assertEquals(id, command.id);
        assertEquals(user2, command.player.getPlayerName());
        assertEquals(2, model.getMapNewGames().getGame(id).getPlayerCount());
        assertEquals(user2.getUsername(),
                model.getMapNewGames().getGame(id).getPlayers().get(1).getPlayerName().getUsername());
    }

    @Test
    public void createGame() {

        this.login();

        assertNull(model.getMapNewGames().getGame(id));
        assertEquals(0, facade.getCommands(user).size());

        CreateGameResult res = facade.createGame(user);
        game = res.getGame();
        id = res.getGame().getId();

        assertEquals(1, game.getPlayerCount());
        assertEquals(user.getUsername(), game.getPlayers().get(0).getPlayerName().getUsername());

        ClientCommandList commands = model.getCommandList().GetCommands(user);
        assertEquals(1, commands.size());
        ClientCommandData command = commands.get(0);
        //getting the command should clear it
        assertEquals(0, model.getCommandList().GetCommands(user).size());
        assertEquals(ClientCommandData.CommandType.NEWGAME, command.type);
        assertNull(command.id);
        assertEquals(1, command.game.getPlayers().size());
        assertEquals(user, command.game.getPlayers().get(0).getPlayerName());
        assertNull(command.player);
        assertEquals(id, command.game.getId());
        assertNotNull(model.getMapNewGames().getGame(id));

        res = facade.createGame(null);

        assertFalse(res.getSuccess());
        assertEquals(0, model.getCommandList().GetCommands(user).size());
    }

    @Test
    public void drawTickets() {
        this.createGame();

        ID gameID = game.getId();
        StartGameResult start = facade.startGame(user, gameID);
        assertTrue(start.getSuccess());

        DrawTicketsResult res = facade.drawTickets(user, gameID);
        assertTrue(res.getSuccess());
        assertNotEquals(0, res.getCards().size());

        res = facade.drawTickets(null, null);
        assertFalse(res.getSuccess());
        assertNull(res.getCards());

        res = facade.drawTickets(user2, gameID);
        assertFalse(res.getSuccess());

        res = facade.drawTickets(user, gameID);
        assertTrue(res.getSuccess());

        res = facade.drawTickets(user, ID.generate());
        assertFalse(res.getSuccess());
    }

    @Test
    public void chat() {
        LoginResult login = facade.register(user, password);
        assertTrue(login.getSuccess());
        CreateGameResult create = facade.createGame(user);
        assertTrue(create.getSuccess());
        ID gameID = create.getGame().getId();

        login = facade.register(user2, password);
        assertTrue(login.getSuccess());
        JoinGameResult join = facade.joinGame(user2, gameID);
        assertTrue(join.getSuccess());

        StartGameResult start = facade.startGame(user, gameID);
        assertTrue(start.getSuccess());

        facade.getCommands(user);
        facade.getCommands(user2);
//TEST NORMAL CHATS
        ChatResult chat = facade.chat(new ChatMessage("TEST", user, gameID));
        assertTrue(chat.getSuccess());

        assertEquals(1, facade.getCommands(user).size());
        assertEquals(1, facade.getCommands(user2).size());

        chat = facade.chat(new ChatMessage("TEST", user2, gameID));
        assertTrue(chat.getSuccess());

        assertEquals(1, facade.getCommands(user).size());
        assertEquals(1, facade.getCommands(user2).size());
//TEST INVALID USER
        chat = facade.chat(new ChatMessage("TEST", fakeUser, gameID));
        assertFalse(chat.getSuccess());
        assertEquals(0, facade.getCommands(user).size());
//TEST INVALID GAME
        chat = facade.chat(new ChatMessage("TEST", user, ID.generate()));
        assertFalse(chat.getSuccess());
        assertEquals(0, facade.getCommands(user).size());
//MAKE SURE NOT EVERYONE GETS CHAT
        facade.register(fakeUser, password);
        chat = facade.chat(new ChatMessage("TEST", user, gameID));
        assertTrue(chat.getSuccess());
        assertEquals(0, facade.getCommands(fakeUser).size());
    }

    @Test
    public void returnCards() {
        this.createGame();
        facade.startGame(user, id);

        DrawTicketsResult draw = facade.drawTickets(user, id);
        assertTrue(draw.getSuccess());
        Set<DestCard> cards = draw.getCards();
        assertNotEquals(0, cards.size());

        Iterator<DestCard> it = cards.iterator();
        DestCard first = it.next();
        ReturnTicketResult res = facade.returnTickets(user, first, id);
        assertTrue(res.getSuccess());

        res = facade.returnTickets(null, null, null);
        assertFalse(res.getSuccess());

        res = facade.returnTickets(user2, it.next(), id);
        assertFalse(res.getSuccess());

        res = facade.returnTickets(user, it.next(), ID.generate());
        assertFalse(res.getSuccess());

        res = facade.returnTickets(user, first, id);
        assertFalse(res.getSuccess());
    }

    private ClientCommandList AssertCommands(int size, Username user) {
        ClientCommandList commands = facade.getCommands(user);
        assertEquals(size, commands.size());
        return commands;
    }

    private void AssertCommand(Username user, ClientCommandData.CommandType type) {
            ClientCommandList commands = facade.getCommands(user);
            assertEquals(1, commands.size());
            assertEquals(type, commands.get(0).type);
    }

    @Test
    public void routeClaimed() {
        this.createGame();

        ID gameID = game.getId();

        JoinGameResult join = facade.joinGame(user2, gameID);
        assertTrue(join.getSuccess());

        StartGameResult start = facade.startGame(user, gameID);
        assertTrue(start.getSuccess());

        RouteClaimedResult res = facade.routeClaimed(null, null, gameID);
        assertFalse(res.getSuccess());

        Routes routes = new Routes();
        Route route1 = routes.getRoute(0);
        Route route2 = routes.getRoute(1);

        facade.getCommands(user);
        res = facade.routeClaimed(route1, user, gameID);
        assertTrue(res.getSuccess());
        AssertCommand(user, ClientCommandData.CommandType.CLAIM_ROUTE);

        res = facade.routeClaimed(route1, user2, gameID);
        assertFalse(res.getSuccess());
        AssertCommands( 0, user);

        res = facade.routeClaimed(route2, user2, ID.generate());
        assertFalse(res.getSuccess());
        AssertCommands( 0, user);

        res = facade.routeClaimed(route2, user2, gameID);
        assertTrue(res.getSuccess());
        AssertCommand(user, ClientCommandData.CommandType.CLAIM_ROUTE);

        //TEST points updated

        int points = game.getPlayer(user2).getTrainCarPoints();
        Route route3 = routes.getRoute(2);
        res = facade.routeClaimed(route3, user2, gameID);
        assertTrue(res.getSuccess());
        int pointsAfter = game.getPlayer(user2).getTrainCarPoints();
        assertEquals(points + route3.getPoints(), pointsAfter);
        AssertCommand(user, ClientCommandData.CommandType.CLAIM_ROUTE);

        points = game.getPlayer(user2).getTrainCarPoints();
        res = facade.routeClaimed(route3, user2, gameID);
        assertFalse(res.getSuccess());
        pointsAfter = game.getPlayer(user2).getTrainCarPoints();
        assertEquals(points , pointsAfter);
        AssertCommands( 0, user);

        //TEST peices
        Player player = game.getPlayer(user2);
        int piecesBefore = player.getTrainPieces();
        Route route4 = routes.getRoute(3);
        res = facade.routeClaimed(route4, user2, gameID);
        assertTrue(res.getSuccess());
        AssertCommand(user, ClientCommandData.CommandType.CLAIM_ROUTE);
        assertEquals(piecesBefore - route4.getLength(), player.getTrainPieces());

        piecesBefore = player.getTrainPieces();
        res = facade.routeClaimed(route4, user2, gameID);
        assertFalse(res.getSuccess());
        AssertCommands(0, user);
        assertEquals(piecesBefore, player.getTrainPieces());

        //TEST end game
        Route route5 = routes.getRoute(4);
        int toRemove = player.getTrainPieces() - route5.getLength() - 2;
        player.playTrains(toRemove);
        res = facade.routeClaimed(route5, user2, gameID);
        assertTrue(res.getSuccess());
        ClientCommandList commandList = AssertCommands(2, user);
        assertEquals(ClientCommandData.CommandType.LAST_TURN, commandList.get(0).type);

    }
}