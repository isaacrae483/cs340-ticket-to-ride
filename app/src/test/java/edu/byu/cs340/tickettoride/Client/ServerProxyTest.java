package edu.byu.cs340.tickettoride.Client;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.server.Model.CommandList;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

import static org.junit.Assert.*;

public class ServerProxyTest {

    private Password password;
    private Password fakePassword;
    private ServerProxy server;
    private ClientModel model;

    private static boolean started = false;


    static int num = 0;
    private Username NextUser() {
        Username toReturn = null;
        try {
            ++num;
            toReturn = new Username("USERTEST" + num);
        }
        catch (Username.InvalidUserNameException e) {
            System.out.println(e.getMessage());
        }
        return toReturn;
    }

    @Test
    public void login() {
        Username user = NextUser();
        Username fakeUser = NextUser();

        server.register(user, password);

        LoginResult res = server.login(user, password);
        assertTrue(res.getSuccess());

        LoginResult fail = server.login(user, fakePassword);
        assertFalse(fail.getSuccess());

        fail = server.login(fakeUser, fakePassword);
        assertFalse(fail.getSuccess());

        fail = server.login(null, null);
        assertFalse(fail.getSuccess());
    }

    @Test
    public void register() {
        Username user = NextUser();
        Username user2 = NextUser();

        assertNull(model.getUsername());

        LoginResult res = server.register(user, password);
        assertTrue(res.getSuccess());

        res = server.register(null, null);
        assertFalse(res.getSuccess());

        res = server.register(user2, password);
        assertTrue(res.getSuccess());
    }

    @Test
    public void joinGame() {
        Username user = NextUser();
        Username user2 = NextUser();
        Username fake = NextUser();

        LoginResult res = server.register(user, password);
        assertTrue(res.getSuccess());
        res = server.register(user2, password);
        assertTrue(res.getSuccess());

        CreateGameResult create = server.createGame(user);
        assertTrue(create.getSuccess());
        ID id = create.getGame().getId();
        assertNotNull(id);

        JoinGameResult join = server.joinGame(user2, id);
        assertTrue(join.getSuccess());

        join= server.joinGame(user2, id);
        assertFalse(join.getSuccess());

        join = server.joinGame(user, null);
        assertFalse(join.getSuccess());

        join = server.joinGame(fake, null);
        assertFalse(join.getSuccess());

        join = server.joinGame(user2, ID.generate());
        assertFalse(join.getSuccess());

        join = server.joinGame(null, id);
        assertFalse(join.getSuccess());
    }

    @Test
    public void createGame() {
        Username user = NextUser();
        Username fakeUser = NextUser();

        server.register(user, password);

        assertNull(model.getGames());

        CreateGameResult res = server.createGame(user);
        Game game = res.getGame();
        ID id = res.getGame().getId();

        assertEquals(1, game.getPlayerCount());
        assertEquals(user.getUsername(), game.getPlayers().get(0).getPlayerName().getUsername());

        ClientCommandList commands = server.getCommands(user);
        assertEquals(1, commands.size());
        ClientCommandData commandData = commands.get(0);
        assertEquals(ClientCommandData.CommandType.NEWGAME, commandData.type);
        assertEquals(id, commandData.game.getId());

        res = server.createGame(fakeUser);
        assertFalse(res.getSuccess());
        assertEquals(0, server.getCommands(user).size());
    }

    @Test
    public void getCommands() {
    }

    @Test
    public void startGame() {
        Username user = this.NextUser();
        Username user2 = this.NextUser();

        LoginResult register = server.register(user, password);
        assertTrue(register.getSuccess());
        register = server.register(user2, password);
        assertTrue(register.getSuccess());

        CreateGameResult create = server.createGame(user);
        assertTrue(create.getSuccess());
        ClientCommandList commandList = server.getCommands(user2);

        ID id = create.getGame().getId();

        StartGameResult start = server.startGame(user2, id);
        assertFalse(start.getSuccess());

        start = server.startGame(user, id);
        assertTrue(start.getSuccess());

        commandList = server.getCommands(user2);
        assertEquals(1, commandList.size());
        ClientCommandData createCommand = commandList.get(0);
        assertEquals(ClientCommandData.CommandType.STARTGAME, createCommand.type);
        assertEquals(id, createCommand.id);
    }

    @Before
    public void setUp() throws Exception {
        try {
            password = new Password("****");
            fakePassword = new Password("password");
        }
        catch (Password.InvalidPasswordException e) {
            System.out.println(e.getMessage());
        }

        server = ServerProxy.instance();
        model = ClientModel.instance();

        if (!started) {
            edu.byu.cs340.tickettoride.server.Server.main();
            URL url = new URL("http://localhost:8080");
            server.setHost(url);
            started = true;
        }

    }
}