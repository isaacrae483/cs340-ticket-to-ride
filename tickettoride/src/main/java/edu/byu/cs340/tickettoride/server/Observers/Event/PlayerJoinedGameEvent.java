package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class PlayerJoinedGameEvent implements Event {
    public PlayerJoinedGameEvent(Player player, ID game) {
        this.player = player;
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public ID getGame() {
        return game;
    }

    private Player player;
    private ID game;
}
