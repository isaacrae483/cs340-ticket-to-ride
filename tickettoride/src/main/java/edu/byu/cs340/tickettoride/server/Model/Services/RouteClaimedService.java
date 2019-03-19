package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.RouteClaimedResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class RouteClaimedService {
    public RouteClaimedResult routeClaimed(Route route, Username username, ID game){
        boolean success = false;
        Player claimingPlayer = null;

        ServerModel model = ServerModel.SINGLETON;
        Game gameInfo = model.getStartedGame(game);
        if (gameInfo != null && gameInfo.contains(username)) {
            claimingPlayer = gameInfo.getPlayer(username);
            Route toClaim = gameInfo.getRoute(route.getId());
            if (!toClaim.getClaimed()) {
                toClaim.claimRoute(claimingPlayer);
                success = true;
            }
        }

        RouteClaimedResult result = new RouteClaimedResult(success, route, claimingPlayer);
        return result;
    }
}
