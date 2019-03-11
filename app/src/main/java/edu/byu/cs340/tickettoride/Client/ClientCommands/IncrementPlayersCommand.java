package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class IncrementPlayersCommand implements ICommand {
    public IncrementPlayersCommand(Player player, ID gameID) {
        this.player = player;
        this.gameID = gameID;
    }

    private Player player;
    private ID gameID;

    @Override
    public Object execute() {
        ClientFacade.instance().incrementPlayers(gameID, player);
        return null;
    }
}
