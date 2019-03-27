package edu.byu.cs340.tickettoride.shared.Game.Board;

import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Board {
    Routes routes;

    public Board() {
        this.routes = new Routes();
    }

    public void claimRoute(Route route, Player player){
        //routes.getRoute(route.getId()).claimRoute(player);
        route.claimRoute(player);
    }


    public Routes getRoutes() {
        return routes;
    }

    public Routes getPlayerRoutes(Username username) {
        //return Routes.getRoutes(username);
        return null;
    }

    //routes class member can be seen as adjacency matrix for an undirected graph

    public Boolean areTwoCitiesConnected(City a, City b, ID gameId) {
        if (a.equals(b)) {
            return true;
        }
        //for (int i = 0; i < )

        return false;
    }

    public Username longestRoute(ID gameId) {
        return null;
    }
}
