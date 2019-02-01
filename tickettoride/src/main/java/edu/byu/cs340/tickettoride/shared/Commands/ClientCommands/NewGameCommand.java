package edu.byu.cs340.tickettoride.shared.Commands.ClientCommands;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class NewGameCommand implements ICommand {
    //sent from the server to the client to update list
    public NewGameCommand(String username) {
        this.username = username;
    }

    private String username;

    private Game game;
    @Override
    public Object execute() {
        //ClientModel.instance().addGame(game);
        return null;
    }
}
