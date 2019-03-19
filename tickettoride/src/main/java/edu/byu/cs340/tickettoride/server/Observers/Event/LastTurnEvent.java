package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.ID;

public class LastTurnEvent extends GameEvent {
    public LastTurnEvent(ID id) {
        super(id);
    }
}
