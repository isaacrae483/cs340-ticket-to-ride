package edu.byu.cs340.tickettoride.Client.model.events.destCard;


import edu.byu.cs340.tickettoride.Client.model.events.Event;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public class DestCardDraw implements Event {
    public DestCard getCard1() {
        return card1;
    }

    public DestCard getCard2() {
        return card2;
    }

    public DestCard getCard3() {
        return card3;
    }


    private DestCard card1;
    private DestCard card2;
    private DestCard card3;

    public DestCardDraw(DestCard card1, DestCard card2, DestCard card3) {
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
    }
}
