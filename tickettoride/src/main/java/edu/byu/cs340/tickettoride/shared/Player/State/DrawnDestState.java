package edu.byu.cs340.tickettoride.shared.Player.State;

import java.io.Serializable;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class DrawnDestState extends TurnState implements Serializable {
    @Override
    public TurnState returnDestCard(Player player, Game game, DestCard card) throws DestCardDeck.AlreadyInDeckException {

        game.returnTickets(card);
        player.ReturnTicket(card);

        return this;
    }

    @Override
    public TurnState finishDrawingDestCards(Player player, Game game){
        game.nextPlayerTurn();
        return new OtherTurnState();
    }
}
