package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;
import edu.byu.cs340.tickettoride.shared.Player.Player;

/**
 * Created by Thomas Lewis on 4/1/19.
 */
public class TCDeckSizeEvent extends GameEvent {
    public TCDeckSizeEvent(ID id, Integer size, Player player) {
        super(id);
        this.size = size;
        this.player = player;
    }

    public int getSize() {
        return size;
    }

    public Player getPlayer() {
        return player;
    }

    private Integer size;
    private Player player;
}
