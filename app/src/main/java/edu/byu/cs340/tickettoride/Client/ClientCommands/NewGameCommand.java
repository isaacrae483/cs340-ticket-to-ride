package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class NewGameCommand implements ICommand {
    //sent from the server to the client to update list
    public NewGameCommand(Game game) {
        this.game = game;
    }

    private Game game;
    @Override
    public Object execute() {
        ClientFacade.instance().addGame(game);
        return null;
    }
}
