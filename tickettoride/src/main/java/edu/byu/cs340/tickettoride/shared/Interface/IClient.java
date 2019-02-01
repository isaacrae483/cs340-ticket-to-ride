package edu.byu.cs340.tickettoride.shared.Interface;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IClient {
    void incrementPlayers(ID id, Username newUser);
    void addGame(Game game);
}
