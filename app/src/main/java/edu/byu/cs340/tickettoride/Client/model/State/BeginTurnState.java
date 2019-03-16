package edu.byu.cs340.tickettoride.Client.model.State;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

public class BeginTurnState extends TurnState {
    private ClientModel model = ClientModel.instance();

    @Override
    public TurnState drawDestCard(){
        //TODO actually draw destination cards
        return new DrawnDestState();
    }

    @Override
    public TurnState drawFaceDownCard(){
        //TODO actually draw card
        return new DrawnOneCardState();
    }

    @Override
    public TurnState drawFaceUpCard(int index){
        TrainCard card = model.getFaceUpCard(index);
        //TODO actually draw card
        if (card.getColor().equals(Colors.RAINBOW)) {
            return new OtherTurnState();
        }
        else {
            return new DrawnOneCardState();
        }
    }

    @Override
    public TurnState claimRoute(Route route){
        //TODO actually claim route
        return new OtherTurnState();
    }
}
