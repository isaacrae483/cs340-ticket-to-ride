package edu.byu.cs340.tickettoride.server.Observers.Event;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class EndGameEvent extends GameEvent {
    List<Player> players;
    public EndGameEvent(ID id, List<Player> players) {
        super(id);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
