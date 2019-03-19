package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class RouteClaimedEvent extends GameEvent {
    private Route route;
    private Player player;

    public RouteClaimedEvent(Route route, Player player, ID id) {
        super(id);
        this.route = route;
        this.player = player;
    }

    public Route getRoute() {
        return route;
    }


    public Player getPlayer() {
        return player;
    }
}
