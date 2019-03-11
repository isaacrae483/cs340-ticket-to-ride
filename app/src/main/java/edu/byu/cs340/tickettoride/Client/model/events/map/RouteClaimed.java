package edu.byu.cs340.tickettoride.Client.model.events.map;

import edu.byu.cs340.tickettoride.shared.Game.Board.IRoute;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;

/**
 * Created by Thomas Lewis on 3/11/19.
 */
public class RouteClaimed implements Event {
    private IRoute mRoute;
    public RouteClaimed(IRoute route) {
        mRoute = route;
    }

    public IRoute getRoute() {
        return mRoute;
    }
}
