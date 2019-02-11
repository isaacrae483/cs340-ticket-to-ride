package edu.byu.cs340.tickettoride.server;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import edu.byu.cs340.tickettoride.server.Model.CommandList;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
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
        assertEquals(2, model.getMapGames().getGame(id).getPlayerCount());
        assertEquals(user2.getUsername(),
                model.getMapGames().getGame(id).getPlayers().get(1).getPlayerName().getUsername());
    }

    @Test
    public void createGame() {

        this.login();

        assertNull(model.getMapGames().getGame(id));
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
        assertNotNull(model.getMapGames().getGame(id));

        res = facade.createGame(null);

        assertFalse(res.getSuccess());
        assertEquals(0, model.getCommandList().GetCommands(user).size());
    }
}