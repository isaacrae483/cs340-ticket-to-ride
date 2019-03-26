package edu.byu.cs340.tickettoride.shared.Result;

import java.util.Set;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public class DrawTicketsResult {
    private boolean success;
    private int numCards;

    public DrawTicketsResult(boolean success, int numCards) {
        this.success = success;
        this.numCards = numCards;
    }

    public int getNumCards() {
        return numCards;
    }

    public boolean getSuccess() {
        return success;
    }

}
