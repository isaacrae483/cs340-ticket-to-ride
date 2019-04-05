package edu.byu.cs340.tickettoride.shared.Game.Board;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Board {
    Routes routes;
    private final int NUM_ROUTES = 100;
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

    public Boolean areTwoCitiesConnectedForUser(City a, City destination, Username player, Set<Route> visitedRoutes) {
        if (a.equals(destination))
            return true;

        if (visitedRoutes == null)
            visitedRoutes = new TreeSet<>();

        for (Route route : getRoutesOwnedByPlayerToCity(a, player)) {
            if (!visitedRoutes.contains(route)) {
                visitedRoutes.add(route);
                City otherCity = route.getStartCity().equals(a) ?
                        route.getEndCity() : route.getStartCity();
                if (areTwoCitiesConnectedForUser(otherCity, destination, player, visitedRoutes))
                    return true;
            }
        }
        return false;
    }

    List<Route> getRoutesOwnedByPlayerToCity(City city, Username player) {
        List<Route> adjacentOwnedRoutes = new ArrayList<>();
        for (int i = 0; i < NUM_ROUTES; i++) {
            Route route = routes.getRoute(i);
            if (route.getStartCity().equals(city) || route.getEndCity().equals(city)) {
                if (route.getClaimedBy() != null && route.getClaimedBy().getPlayerName().equals(player)) {
                    adjacentOwnedRoutes.add(route);
                }
            }
        }
        return adjacentOwnedRoutes;
    }

    public Player longestRoute(List<Player> players)
    {

        int[] routeCounts = new int[5];
        for(int a = 0; a < routeCounts.length; a++){
            routeCounts[a] = 0;
        }
        for(int i = 0; i < routes.getSize(); i++){
            Route route = routes.getRoute(i);
            if(route.getClaimed()){
                Username claimedBy = route.getClaimedBy().getPlayerName();
                for(int j = 0; j < players.size(); j++){
                    if(players.get(j).getPlayerName().equals(claimedBy)){
                        routeCounts[j]++;
                        break;
                    }
                }
            }

        }

        int winningIndex = 0;
        for ( int k = 1; k < routeCounts.length; k++ )
        {
            if ( routeCounts[k] > routeCounts[winningIndex] )
                winningIndex = k;
        }
        return players.get(winningIndex);
    }
}
