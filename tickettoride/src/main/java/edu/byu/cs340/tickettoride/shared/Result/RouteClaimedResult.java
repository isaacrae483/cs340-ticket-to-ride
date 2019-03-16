package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;

public class RouteClaimedResult {
    private boolean success;
    private Route route;
    public RouteClaimedResult(boolean success, Route route) {
        this.success = success;
        this.route = route;
    }

    public boolean getSuccess() {
        return success;
    }

    public Route getRoute() {
        return route;
    }
}
