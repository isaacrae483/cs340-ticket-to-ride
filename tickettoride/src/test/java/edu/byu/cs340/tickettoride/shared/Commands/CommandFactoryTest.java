package edu.byu.cs340.tickettoride.shared.Commands;

import static org.junit.Assert.*;

import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.CreateGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.JoinGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.LoginCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.RegisterCommand;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

import static edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData.commandType.*;


public class CommandFactoryTest {

    @org.junit.Test
    public void generate() {

        Username user;
        Password password;
        try {
            user = new Username("1");
            password = new Password("***");
        }
        catch(Password.InvalidPasswordException e) {
            assertTrue(false);
            return;
        }
        catch(Username.InvalidUserNameException e) {
                assertTrue(false);
                return;
        }
        ServerCommandData create = new ServerCommandData(CREATEGAME, user, password);
        ServerCommandData join = new ServerCommandData(JOINGAME, user);
        ServerCommandData login = new ServerCommandData(LOGIN, user);
        ServerCommandData register = new ServerCommandData(REGISTER, user, password);

        ICommand created = ServerCommandFactory.Generate(create);
        ICommand joined = ServerCommandFactory.Generate(join);
        ICommand logined = ServerCommandFactory.Generate(login);
        ICommand registered = ServerCommandFactory.Generate(register);

        assertTrue(created instanceof CreateGameCommand);
        assertTrue(joined instanceof JoinGameCommand);
        assertTrue(logined instanceof LoginCommand);
        assertTrue(registered instanceof RegisterCommand);
    }

}