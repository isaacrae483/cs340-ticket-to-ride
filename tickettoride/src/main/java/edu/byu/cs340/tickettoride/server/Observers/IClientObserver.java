package edu.byu.cs340.tickettoride.server.Observers;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IClientObserver {
    void OnPlayerJoin(Player player, ID game);
    void OnNewGame(Game game);
}

