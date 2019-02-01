package edu.byu.cs340.tickettoride.shared.Commands.ClientCommands;

import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class IncrementPlayersCommand implements ICommand {
    public IncrementPlayersCommand(String username, String gameID) {
        this.username = username;
        this.gameID = gameID;
    }

    private String username;
    private String gameID;

    @Override
    public Object execute() {
        return null;
    }
}
