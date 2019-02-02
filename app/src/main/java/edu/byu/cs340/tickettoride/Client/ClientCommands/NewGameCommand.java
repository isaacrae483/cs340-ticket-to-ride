package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class NewGameCommand implements ICommand {
    //sent from the server to the client to update list
    public NewGameCommand(Username username) {
        this.username = username;
    }

    private Username username;

    private Game game;
    @Override
    public Object execute() {
        ClientFacade.instance().addGame(game);
        return null;
    }
}
