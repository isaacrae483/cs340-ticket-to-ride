package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;

/**
 * The GameEvent is for any event that only affects a certain game.
 * This will not be sent to players who are not a part of the game with
 * ID id.
 */
public abstract class GameEvent implements Event {
    public GameEvent(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    private ID id;
}
