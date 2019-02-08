package edu.byu.cs340.tickettoride.shared.Commands;


import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.CreateGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.JoinGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.LoginCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.RegisterCommand;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class ServerCommandFactory {
    public static ICommand Generate(ServerCommandData info) {
        switch (info.type) {
            case LOGIN:
                return new LoginCommand(info.username, info.password);
            case JOINGAME:
                return new JoinGameCommand(info.username, info.gameID);
            case REGISTER:
                return new RegisterCommand(info.username, info.password);
            case CREATEGAME:
                return new CreateGameCommand(info.username);
        }
        //tried to create non-existent command
        assert (false);
        return null;
    }

}
