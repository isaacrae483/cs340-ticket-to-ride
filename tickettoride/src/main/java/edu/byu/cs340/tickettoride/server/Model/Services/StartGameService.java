package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class StartGameService  {
    public StartGameResult startGame(Username username, ID id) {
        Game game = ServerModel.SINGLETON.getMapGames().getGame(id);
        StartGameResult res = null;

        if (game != null && game.GetLeader().getPlayerName().equals(username)) {
            game.startGame();
            ServerModel.SINGLETON.getMapGames().remove(id);
            //TODO add the game to a list of started games
            res = new StartGameResult(true);
        }
        else {
            res = new StartGameResult(false);
        }

        return res;
    }
}
