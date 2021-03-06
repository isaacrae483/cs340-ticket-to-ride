package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class RouteClaimedResult {
    private boolean success;
    private Route route;
    private Player player;

    public RouteClaimedResult(boolean success, Route route, Player player) {
        this.success = success;
        this.route = route;
        this.player = player;
    }

    public boolean getSuccess() {
        return success;
    }

    public Route getRoute() {
        return route;
    }

    public Player getPlayer() {
        return player;
    }
}
