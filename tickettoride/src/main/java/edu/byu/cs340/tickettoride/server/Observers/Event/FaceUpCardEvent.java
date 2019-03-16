package edu.byu.cs340.tickettoride.server.Observers.Event;


import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;

public class FaceUpCardEvent extends GameEvent {
    public FaceUpCardEvent(TrainCard card, int index, ID game) {
        super(game);
        this.card = card;
        this.index = index;
    }

    public TrainCard getCard() {
        return card;
    }

    public int getIndex() {
        return index;
    }

    private TrainCard card;
    private int index;
}
