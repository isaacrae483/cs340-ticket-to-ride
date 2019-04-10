package edu.byu.cs340.tickettoride.shared.Interface.Plugin;

import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Game;

public interface GameDAO {
    void updateGame(ServerCommandData data);
    void deleteGame();
    MapGameDAO getGames();
    Game getGame();
}
