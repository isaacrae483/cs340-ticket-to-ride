package edu.byu.cs340.tickettoride.shared.Player.State;


import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class BeginTurnState extends TurnState {

    @Override
    public TurnState drawDestCard(Player player, Game game){
        player.DrawDestCards(game.drawTickets());
        return new DrawnDestState();
    }

    @Override
    public TurnState drawFaceDownCard(Player player, Game game){
        TrainCard drawnCard = game.drawCard();
        if (drawnCard == null)
            return this;
        player.DrawCard(drawnCard);
        return new DrawnOneCardState();
    }

    @Override
    public TurnState drawFaceUpCard(Player player, Game game, int index){
        TrainCard card = game.drawFaceUpCard(index);
        if (card == null)
            return this;
        player.DrawCard(card);
        if (card.getColor().equals(Colors.RAINBOW)) {
            game.nextPlayerTurn();
            return new OtherTurnState();
        }
        return new DrawnOneCardState();
    }

    @Override
    public TurnState claimRoute(Player player, Game game, Route route){
        if(game.ClaimRoute(route, player)) {
            game.nextPlayerTurn();
            return new OtherTurnState();
        }
        else
            return this;
    }
}
