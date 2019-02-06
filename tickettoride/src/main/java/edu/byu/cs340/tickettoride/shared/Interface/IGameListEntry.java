package edu.byu.cs340.tickettoride.shared.Interface;

import edu.byu.cs340.tickettoride.shared.Game.ID;

/**
 * Created by Thomas Lewis on 2/5/19.
 */
public interface IGameListEntry {
    ID getId();
    int getPlayerCount();
    boolean isGameFull();
}
