package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class CreateGameService {

    public static CreateGameResult createGame(Username username){
        if (username == null) {
            return new CreateGameResult(false, null);
        }
        Game newGame = new Game(ID.generate());
        newGame.addPlayer(username);
        ServerModel.SINGLTON.getMapGames().addGame(newGame);

        return new CreateGameResult(true, newGame);
    }
}
