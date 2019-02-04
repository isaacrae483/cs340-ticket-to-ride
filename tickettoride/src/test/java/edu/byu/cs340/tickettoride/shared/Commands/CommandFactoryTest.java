package edu.byu.cs340.tickettoride.shared.Commands;

import static org.junit.Assert.*;

import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.CreateGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.JoinGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.LoginCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.RegisterCommand;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

import static edu.byu.cs340.tickettoride.shared.Commands.CommandData.commandType.*;


public class CommandFactoryTest {

    @org.junit.Test
    public void generate() {
        Username user = new Username("1");
        Password password = new Password("***");

        CommandData create = new CommandData(CREATEGAME, user, password);
        CommandData join = new CommandData(JOINGAME, user);
        CommandData login = new CommandData(LOGIN, user);
        CommandData register = new CommandData(REGISTER, user, password);

        ICommand created = CommandFactory.Generate(create);
        ICommand joined = CommandFactory.Generate(join);
        ICommand logined = CommandFactory.Generate(login);
        ICommand registered = CommandFactory.Generate(register);

        assertTrue(created instanceof CreateGameCommand);
        assertTrue(joined instanceof JoinGameCommand);
        assertTrue(logined instanceof LoginCommand);
        assertTrue(registered instanceof RegisterCommand);
    }

}