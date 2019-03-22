package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class DestDeckSizeEvent extends GameEvent {
    public DestDeckSizeEvent(ID id, int offset, Player player) {
        super(id);
        this.offset = offset;
        this.player = player;
    }

    public int getOffset() {
        return offset;
    }

    public Player getPlayer() {
        return player;
    }


    private Player player;
    private int offset;
}
