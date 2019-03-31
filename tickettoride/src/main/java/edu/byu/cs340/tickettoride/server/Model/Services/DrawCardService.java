package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Player.State.TurnState;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceDownCardResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceUpCardResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 3/26/19.
 */
public class DrawCardService {

    static public DrawFaceUpCardResult drawFaceUpCard(Integer index, Username playerName, ID gameId) {
        // Get everything we need
        boolean success = false;
        TrainCard drawnCard = null;
        ServerModel serverModel = ServerModel.SINGLETON;
        Game relevantGame = serverModel.getStartedGame(gameId);
        drawnCard = relevantGame.peekFaceUp(index);
        Player player = relevantGame.getPlayer(playerName);

        //Attempt to have the player draw
        TurnState oldState  = player.getState();
        relevantGame.getPlayer(playerName).drawFaceUpCard(relevantGame, index);
        TurnState newState = player.getState();

        // If the state changed then we drew a card
        // Make sure the state changed and the new card is not the old card
        if (!oldState.getClass().getName().equals(newState.getClass().getName()) &&
            !(drawnCard == relevantGame.peekFaceUp(index))) {
            success = true;
        }

        return new DrawFaceUpCardResult(success, drawnCard);
    }

    static public DrawFaceDownCardResult drawFaceDownCard(Username playerName, ID gameId) {



        return null;// new DrawFaceDownCardResult(success, drawnCard);
    }
}
