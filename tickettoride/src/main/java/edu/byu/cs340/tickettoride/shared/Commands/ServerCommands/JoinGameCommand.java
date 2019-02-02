package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class JoinGameCommand implements ICommand {
    public JoinGameCommand(Username username) {
        this.username = username;
    }

    private Username username;

    @Override
    public JoinGameResult execute() {
        return null;
    }
}
