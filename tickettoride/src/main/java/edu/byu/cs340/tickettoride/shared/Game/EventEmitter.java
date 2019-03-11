package edu.byu.cs340.tickettoride.shared.Game;

import java.util.Observable;

import edu.byu.cs340.tickettoride.shared.Game.events.ErrorEvent;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;

/**
 * Created by Thomas Lewis on 3/10/19.
 */
abstract public class EventEmitter extends Observable {

    public void passErrorEvent(ErrorEvent errorEvent) {
        emitEvent(errorEvent);
    }

    public void emitEvent(Event event) {
        setChanged();
        notifyObservers(event);
    }
}
