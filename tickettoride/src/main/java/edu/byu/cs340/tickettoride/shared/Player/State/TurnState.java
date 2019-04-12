package edu.byu.cs340.tickettoride.shared.Player.State;

import java.io.Serializable;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class TurnState implements Serializable {
    public TurnState returnDestCard(Player player, Game game, DestCard card) throws DestCardDeck.AlreadyInDeckException {return this;}
    public TurnState drawDestCard(Player player, Game game){return this;}
    public TurnState finishDrawingDestCards(Player player, Game game){return this;}
    public TurnState drawFaceUpCard(Player player, Game game, int index){return this;}
    public TurnState drawFaceDownCard(Player player, Game game){return this;}
    public TurnState claimRoute(Player player, Game game, Route route){return this;}
    public TurnState nextTurn(Player player, Game game){return this;}

    public TurnState() {
        //CUZ JSON BE A JERKZ
    }

}
