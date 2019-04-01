package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

/**
 * Created by Thomas Lewis on 4/1/19.
 */
public class TCDeckChangedCommand implements ICommand {
    private Integer size;
    private Player player;

    public TCDeckChangedCommand(Integer size, Player player) {
        this.size = size;
        this.player = player;
    }

    @Override
    public Object execute() {
        ClientFacade.instance().changeTCDeckSize(size, player);
        return null;
    }
}
