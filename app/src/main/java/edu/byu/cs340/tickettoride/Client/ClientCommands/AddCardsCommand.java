package edu.byu.cs340.tickettoride.Client.ClientCommands;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class AddCardsCommand implements ICommand {

    public AddCardsCommand(List<TrainCard> cards, Player player) {
        this.cards = cards;
        this.player = player;
    }

    private List<TrainCard> cards;
    private Player player;

    @Override
    public Object execute() {
        ClientFacade.instance().addCards(cards, player);
        return null;
    }
}
