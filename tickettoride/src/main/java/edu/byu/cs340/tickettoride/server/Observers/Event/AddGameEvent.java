package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;

public class AddGameEvent implements Event {
    public AddGameEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    private Game game;
}
