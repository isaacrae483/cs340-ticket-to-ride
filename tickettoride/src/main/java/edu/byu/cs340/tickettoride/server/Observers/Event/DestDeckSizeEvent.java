package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.ID;

public class DestDeckSizeEvent extends GameEvent {
    public DestDeckSizeEvent(ID id, int offset) {
        super(id);
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    private int offset;
}
