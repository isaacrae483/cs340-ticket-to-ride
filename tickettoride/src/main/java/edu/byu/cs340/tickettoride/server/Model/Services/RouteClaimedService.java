package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.Server;
import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
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
            Route toClaim = gameInfo.getRoute(route.getId());
            claimingPlayer = gameInfo.getPlayer(username);
            if (!toClaim.getClaimed() && claimingPlayer.hasTrainCars(route.getLength())) {
                claimingPlayer.claimRoute(gameInfo, toClaim);
                if(gameInfo.getRoute(toClaim.getId()).getClaimed())
                    success = true;
                    claimingPlayer.addTrainCarPoints(toClaim.getPoints());
                    claimingPlayer.playTrains(toClaim.getLength());
                CheckGameEnd(game, claimingPlayer);
                ServerModel.SINGLETON.updateGame(gameInfo,
                        new ServerCommandData(ServerCommandData.commandType.CLAIMROUTE, username, route, game));
            }
        }

        RouteClaimedResult result = new RouteClaimedResult(success, route, claimingPlayer);
        return result;
    }


    private void CheckGameEnd(ID game, Player claimingPlayer) {
        if (claimingPlayer.getTrainPieces() <= 2) {
            ServerFacade.SINGLETON.LastTurn(game);
            ServerModel.SINGLETON.getStartedGame(game).setGameEnder(claimingPlayer.getPlayerName());
        }
    }
}
