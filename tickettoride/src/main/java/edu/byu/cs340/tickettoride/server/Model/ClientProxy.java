package edu.byu.cs340.tickettoride.server.Model;

import edu.byu.cs340.tickettoride.server.Observers.IClientObserver;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientProxy implements IClientObserver, IClient {
    @Override
    public void OnPlayerJoin(Username user, ID game) {
        incrementPlayers(game, user);
    }

    @Override
    public void OnNewGame(ID game) {
        addGame(new Game(game));
    }

    @Override
    public void incrementPlayers(ID id, Username newUser) {
        //add command to model
    }

    @Override
    public void addGame(Game game) {
        //add command to model
    }
}
