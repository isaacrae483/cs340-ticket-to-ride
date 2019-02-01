package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;

public class JoinGameCommand implements ICommand {
    public JoinGameCommand(String username) {
        this.username = username;
    }

    private String username;

    @Override
    public JoinGameResult execute() {
        return null;
    }
}
