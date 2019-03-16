package edu.byu.cs340.tickettoride.Client.model.State;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

public class DrawnOneCardState extends TurnState {
    private ClientModel model = ClientModel.instance();

    @Override
    public TurnState drawFaceUpCard(int index){
        TrainCard card = model.getFaceUpCard(index);
        if (card.getColor().equals(Colors.RAINBOW)) {
            //do NOT draw the card here
            return this;
        }
        else {
            //TODO actually draw card
            return new OtherTurnState();
        }
    }

    @Override
    public TurnState drawFaceDownCard(){
        //TODO actually draw card
        return new OtherTurnState();
    }
}
