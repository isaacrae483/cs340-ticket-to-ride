package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class DestDeckChangedCommand implements ICommand {
    public DestDeckChangedCommand(int offset, Player player) {
        this.player = player;
        this.offset = offset;
    }

    private int offset;
    private Player player;

    @Override
    public Object execute() {
        ClientFacade.instance().changeDestDeckSize(offset, player);
        return null;
    }
}
