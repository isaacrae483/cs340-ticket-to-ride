package edu.byu.cs340.tickettoride.shared.Interface;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public interface IClient {
    void incrementPlayers(ID id, Player newUser);
    void addGame(Game game);
    void startGame(ID gameId);
}
