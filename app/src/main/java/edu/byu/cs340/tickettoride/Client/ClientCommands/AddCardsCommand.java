package edu.byu.cs340.tickettoride.Client.ClientCommands;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class AddCardsCommand implements ICommand {

    public AddCardsCommand(List<TrainCard> cards) {
        this.cards = cards;
    }

    private List<TrainCard> cards;

    @Override
    public Object execute() {
        ClientFacade.instance().addCards(cards);
        return null;
    }
}
