package edu.byu.cs340.tickettoride.Client;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

import static org.junit.Assert.*;

public class ServerProxyTest {
    private Username user;
    private Username fakeUser;
    private Username user2;
    private Password password;
    private Password fakePassword;
    private Password password2;
    private ServerProxy server;
    private ClientModel model;

    private static boolean started = false;

    @Test
    public void login() {
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
        assertNull(model.getUsername());

        LoginResult res = server.register(user, password);
        assertTrue(res.getSuccess());

        res = server.register(null, null);
        assertFalse(res.getSuccess());

        res = server.register(user2, password2);
        assertTrue(res.getSuccess());
    }

    @Test
    public void joinGame() {
    }

    @Test
    public void createGame() {
        this.login();

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

    @Before
    public void setUp() throws Exception {
        try {
            user = new Username("UserTEST");
            fakeUser = new Username("FAKE");
            user2 = new Username("UserTEST2");

            password = new Password("****");
            fakePassword = new Password("password");
            password2 = new Password("DISBEPASSWURD");
        } catch (Username.InvalidUserNameException e) {
            System.out.println(e.getMessage());
        } catch (Password.InvalidPasswordException e) {
            System.out.println(e.getMessage());
        }

        server = ServerProxy.instance();
        model = ClientModel.instance();

        if (!started) {
            edu.byu.cs340.tickettoride.server.Server.main();
            started = true;
        }

        URL url = new URL("http://localhost:8080");
        server.setURL(url);
    }
}