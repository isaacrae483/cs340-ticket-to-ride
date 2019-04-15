package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class CreateGameService {

    public static CreateGameResult createGame(Username username){
        if (username == null) {
            return new CreateGameResult(false, null);
        }

        if (ServerModel.SINGLETON.getUser(username) == null) {
            return new CreateGameResult(false, null);
        }

        Game newGame = new Game(ID.generate());
        Player first = new Player(username, IPlayer.Color.values()[0]);
        newGame.addPlayer(first);
        ServerModel.SINGLETON.addGame(newGame);

        return new CreateGameResult(true, newGame);
    }
}
