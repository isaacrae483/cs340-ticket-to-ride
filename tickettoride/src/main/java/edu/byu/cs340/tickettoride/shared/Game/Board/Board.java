package edu.byu.cs340.tickettoride.shared.Game.Board;

import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Board {
    Routes routes;

    public Board() {
        this.routes = new Routes();
    }

    public void claimRoute(Route route, Player player){
        route.claimRoute(player);
    }


    public Routes getRoutes() {
        return routes;
    }

    public Routes getPlayerRoutes(Username username) {
        //return Routes.getRoutes(username);
        return null;
    }
}
