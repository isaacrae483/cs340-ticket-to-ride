package edu.byu.cs340.tickettoride.shared.Player.State;


import java.io.Serializable;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class DrawnOneCardState extends TurnState implements Serializable {

    @Override
    public TurnState drawFaceUpCard(Player player, Game game, int index){
        TrainCard card = game.peekFaceUp(index);
        if (card == null)
            return this;
        if (card.getColor().equals(Colors.RAINBOW)) {
            return this;
        }

        player.DrawCard(game.drawFaceUpCard(index));
        game.nextPlayerTurn();
        return new OtherTurnState();
    }

    @Override
    public TurnState drawFaceDownCard(Player player, Game game){
        TrainCard drawnCard = game.drawCard();
        if (drawnCard == null)
            return this;
        player.DrawCard(drawnCard);
        game.nextPlayerTurn();
        return new OtherTurnState();
    }
}
