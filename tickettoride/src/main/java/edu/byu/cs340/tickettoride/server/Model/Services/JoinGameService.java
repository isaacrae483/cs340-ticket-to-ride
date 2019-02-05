package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class JoinGameService {

    public static JoinGameResult joinGame(Username username, ID id){
        Game game = ServerModel.SINGLTON.getMapGames().getGame(id);
        if(game != null && game.getPlayerCount() < 5){ //***Fix the player count.
            game.addPlayer(username);
            return new JoinGameResult(true);
        }
        return new JoinGameResult(false);
    }
}
