package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;

public class CreateGameCommand implements ICommand {
    //sent from client to the server to tell the server to create a game and add to the list
    public CreateGameCommand(String username) {
        this.username = username;
    }

    private String username;


    @Override
    public CreateGameResult execute() {
        return null;
    }
}
