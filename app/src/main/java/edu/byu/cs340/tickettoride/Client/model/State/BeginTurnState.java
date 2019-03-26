package edu.byu.cs340.tickettoride.Client.model.State;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class BeginTurnState extends TurnState {
    private ClientModel model = ClientModel.instance();

    @Override
    public TurnState drawDestCard(){
        //TODO actually draw destination cards make sure this move updates server
        model.getActiveGame().drawTickets();
        return new DrawnDestState();
    }

    @Override
    public TurnState drawFaceDownCard(){
        //TODO make sure this move updates the server
        TrainCard card = model.getActiveGame().drawCard();
        Username user = model.getActiveGame().getPlayerTurn();
        Player player = model.getActiveGame().getPlayer(user);
        player.getHand().addCard(card);
        return new DrawnOneCardState();
    }

    @Override
    public TurnState drawFaceUpCard(int index){
        //TODO makes sure this move actually updates the server
        TrainCard card = model.getFaceUpCard(index);
        Username user = model.getActiveGame().getPlayerTurn();
        Player player = model.getActiveGame().getPlayer(user);
        player.getHand().addCard(card);
        if (card.getColor().equals(Colors.RAINBOW)) {
            return new OtherTurnState();
        }
        else {
            return new DrawnOneCardState();
        }
    }

    @Override
    public TurnState claimRoute(Route route){
        //TODO actually claim route and update server...should call ModelFacade.
        return new OtherTurnState();
    }
}
