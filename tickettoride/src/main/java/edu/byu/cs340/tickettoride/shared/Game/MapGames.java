package edu.byu.cs340.tickettoride.shared.Game;

import java.util.HashMap;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;

public class MapGames {
    private Map<ID, Game> games = new HashMap<>();

    public void addGame(Game game){
        games.put(game.getId(), game);
    }

    public Game getGame(ID id){
        return games.get(id);
    }
}
