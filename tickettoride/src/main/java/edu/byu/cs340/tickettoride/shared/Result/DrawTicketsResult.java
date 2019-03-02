package edu.byu.cs340.tickettoride.shared.Result;

import java.util.Set;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public class DrawTicketsResult {
    private boolean success;
    private Set<DestCard> cards;

    public DrawTicketsResult(boolean success, Set<DestCard> cards) {
        this.success = success;
        this.cards = cards;
    }

    public boolean getSuccess() {
        return success;
    }

    public Set<DestCard> getCards() {
        return cards;
    }
}
