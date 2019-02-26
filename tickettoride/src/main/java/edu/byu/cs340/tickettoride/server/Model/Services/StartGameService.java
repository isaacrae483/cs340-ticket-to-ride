package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class StartGameService  {
    public StartGameResult startGame(Username username, ID id) {
        ServerModel model = ServerModel.SINGLETON;
        Game game = model.getMapNewGames().getGame(id);
        StartGameResult res = null;

        if (game != null && game.GetLeader().getPlayerName().equals(username)) {
            game.startGame();
            model.getMapNewGames().remove(id);
            model.getMapStartedGames().addGame(game);
            res = new StartGameResult(true);
        }
        else {
            res = new StartGameResult(false);
        }

        return res;
    }
}
