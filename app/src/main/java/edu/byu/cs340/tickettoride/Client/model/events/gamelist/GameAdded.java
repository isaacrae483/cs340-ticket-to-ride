package edu.byu.cs340.tickettoride.Client.model.events.gamelist;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;

/**
 * Created by Thomas Lewis on 2/10/19.
 */
public class GameAdded implements Event {
    private Game mGame;

    public GameAdded(Game game) {
        mGame = game;
    }

    public Game getGame() {
        return mGame;
    }
}
