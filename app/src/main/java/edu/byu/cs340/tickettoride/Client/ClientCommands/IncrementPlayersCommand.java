package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class IncrementPlayersCommand implements ICommand {
    public IncrementPlayersCommand(Username username, ID gameID) {
        this.username = username;
        this.gameID = gameID;
    }

    private Username username;
    private ID gameID;

    @Override
    public Object execute() {
        ClientFacade.instance().incrementPlayers(gameID, username);
        return null;
    }
}
