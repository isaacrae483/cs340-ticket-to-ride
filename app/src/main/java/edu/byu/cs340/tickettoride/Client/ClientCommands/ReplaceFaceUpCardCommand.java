package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

/**
 * Created by Thomas Lewis on 3/11/19.
 */
public class ReplaceFaceUpCardCommand implements ICommand {
    TrainCard mTrainCard;
    int pos;
    public ReplaceFaceUpCardCommand(TrainCard card, int pos) {
        this.mTrainCard = card;
        this.pos = pos;
    }

    @Override
    public Object execute() {
        ClientModel.instance().replaceFaceUpTrainCard(mTrainCard, pos);
        return null;
    }
}
