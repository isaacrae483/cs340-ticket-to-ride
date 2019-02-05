package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class CreateGameService {

    public static CreateGameResult createGame(Username username){
        Game newGame = new Game(ID.generate());
        newGame.addPlayer(username);
        ServerModel.SINGLTON.getMapGames().addGame(newGame);
        //When would a Create Game result not be true? can a single user not create more than one game?
        return new CreateGameResult(true, newGame);
    }
}
