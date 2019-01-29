package edu.byu.cs340.tickettoride.shared.Interface;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;

public interface IClient {
    void incrementPlayers(ID id);
    void addGame(Game game);
}
