package edu.byu.cs340.tickettoride.Client.model.events.gamelobby;

import edu.byu.cs340.tickettoride.shared.Game.events.Event;
import edu.byu.cs340.tickettoride.shared.Game.Game;

/**
 * Created by Thomas Lewis on 2/10/19.
 */
public class GameStarted implements Event {
    private Game mGame;

    public GameStarted(Game game) {
        mGame = game;
    }

    public Game getGame() {
        return mGame;
    }
}
