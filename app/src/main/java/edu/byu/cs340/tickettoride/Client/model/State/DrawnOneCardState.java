package edu.byu.cs340.tickettoride.Client.model.State;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class DrawnOneCardState extends TurnState {
    private ClientModel model = ClientModel.instance();

    @Override
    public TurnState drawFaceUpCard(int index){
        Username currentPlayer = model.getPlayerTurn();
        TrainCard card = model.getFaceUpCard(index);
        if (card.getColor().equals(Colors.RAINBOW)) {
            //do NOT draw the card here
            // -> If a player wants to draw a rainbow card, they have to *only* draw that card.
            // -> Therefore if we've reached this state, they did not choose a rainbow card, and they cannot choose one now
            return this;
        }
        else {
            ModelFacade.instance().drawFaceUpCard(index, currentPlayer);
            return new OtherTurnState();
        }
    }

    @Override
    public TurnState drawFaceDownCard(){
        Username currentPlayer = model.getPlayerTurn();
        ModelFacade.instance().drawFaceDownCard(currentPlayer);
        return new OtherTurnState();
    }
}
