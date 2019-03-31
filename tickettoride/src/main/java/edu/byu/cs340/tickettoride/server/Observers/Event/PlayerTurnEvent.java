package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.ID;

public class PlayerTurnEvent extends GameEvent {
    public PlayerTurnEvent(ID game) {
        super(game);
    }
}
