package edu.byu.cs340.tickettoride.shared.Commands;


import edu.byu.cs340.tickettoride.shared.Commands.ClientCommands.IncrementPlayersCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommands.NewGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.CreateGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.JoinGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.LoginCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.RegisterCommand;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class CommandFactory {
    static ICommand Generate(CommandData info) {
        switch (info.type) {
            case LOGIN:
                return new LoginCommand(info.arg1, info.arg2);
            case JOINGAME:
                return new JoinGameCommand(info.arg1);
            case REGISTER:
                return new RegisterCommand(info.arg1, info.arg2);
            case CREATEGAME:
                return new CreateGameCommand(info.arg1);
            case NEWGAME:
                return new NewGameCommand(info.arg1);
            case INCREMENTPLAYER:
                return new IncrementPlayersCommand(info.arg1, info.arg2);
        }
        //tried to create non-existent command
        assert (false);
        return null;
    }

}
