package edu.byu.cs340.tickettoride.shared.Commands.ClientCommands;

import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class NewGameCommand implements ICommand {
    //sent from the server to the client to update list
    public NewGameCommand(String username) {
        this.username = username;
    }

    private String username;

    @Override
    public Object execute() {
        return null;
    }
}
