package edu.byu.cs340.tickettoride.Client.ClientCommands;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class AddCardsCommand implements ICommand {

    public AddCardsCommand(Player player) {
        this.player = player;
    }

    private Player player;

    @Override
    public Object execute() {
        ClientFacade.instance().addCards(player);
        return null;
    }
}
