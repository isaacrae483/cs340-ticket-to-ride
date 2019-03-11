package edu.byu.cs340.tickettoride.shared.Game.events.destCard;

import edu.byu.cs340.tickettoride.shared.Game.events.Event;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public class DestCardReturned implements Event {
    private DestCard returned;
    public DestCardReturned(DestCard returned) {
        this.returned = returned;
    }

    public DestCard getReturned() {
        return returned;
    }
}
