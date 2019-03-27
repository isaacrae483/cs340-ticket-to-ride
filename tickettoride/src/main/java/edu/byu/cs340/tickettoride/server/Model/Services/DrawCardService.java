package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceDownCardResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceUpCardResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 3/26/19.
 */
public class DrawCardService {

    public DrawFaceUpCardResult drawFaceUpCard(Integer index, Username playerName, ID gameId) {
        boolean success = false;
        TrainCard drawnCard = null;
        ServerModel serverModel = ServerModel.SINGLETON;
        Game relevantGame = serverModel.getStartedGame(gameId);
        //relevantGame.getPlayer(playerName).drawFaceUpCard(relevantGame, index);

        return new DrawFaceUpCardResult(success, drawnCard);
    }

    public DrawFaceDownCardResult drawFaceDownCard(Username playerName, ID gameId) {



        return null;// new DrawFaceDownCardResult(success, drawnCard);
    }
}
