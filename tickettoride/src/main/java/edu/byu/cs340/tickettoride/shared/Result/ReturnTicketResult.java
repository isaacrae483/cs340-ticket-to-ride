package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public class ReturnTicketResult {
    private boolean success;
    private DestCard card;
    public ReturnTicketResult(boolean success, DestCard returned) {
        this.success = success;
        this.card = returned;
    }

    public boolean getSuccess() {
        return success;
    }

    public DestCard getCard() {
        return card;
    }
}
