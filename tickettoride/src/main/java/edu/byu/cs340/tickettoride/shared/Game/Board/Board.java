package edu.byu.cs340.tickettoride.shared.Game.Board;

import edu.byu.cs340.tickettoride.shared.User.Username;

public class Board {
    Routes routes;

    public Board(Routes routes) {
        this.routes = routes;
    }

    public void claimRoute(Route route, Username username){
        route.claimRoute(username);
    }


    public Routes getRoutes() {
        return routes;
    }

    public Routes getPlayerRoutes(Username username) {
        //return Routes.getRoutes(username);
        return null;
    }
}
