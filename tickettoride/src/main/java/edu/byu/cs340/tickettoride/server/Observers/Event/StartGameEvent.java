package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;

public class StartGameEvent implements Event {
    public StartGameEvent(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    private ID id;
}
