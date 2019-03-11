package edu.byu.cs340.tickettoride.shared.Game;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.shared.Game.EventEmitter;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;

/**
 * Created by Thomas Lewis on 3/10/19.
 */
abstract public class EventBubbler extends EventEmitter implements Observer {
    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Event)
            emitEvent((Event)o);
    }
}
