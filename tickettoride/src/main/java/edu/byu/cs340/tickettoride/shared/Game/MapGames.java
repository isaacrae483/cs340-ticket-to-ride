package edu.byu.cs340.tickettoride.shared.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class MapGames {
    private Map<ID, Game> games = new HashMap<>();

    public void addGame(Game game){
        games.put(game.getId(), game);
    }

    public Game getGame(ID id){
        return games.get(id);
    }

    public int size() { return games.size(); }

    public List<IGameListEntry> getAsList() {
        ArrayList<IGameListEntry> list = new ArrayList<>();
        for (Map.Entry<ID, Game> entry : games.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public List<Game> getAsListGames() {
        ArrayList<Game> list = new ArrayList<>();
        for (Map.Entry<ID, Game> entry : games.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public void remove(ID id) {
        games.remove(id);
    }

    public void replace(Game game) {
        if (contains(game.getId())) {
            addGame(game);
        }
    }

    public boolean contains(ID id) {
        return games.containsKey(id);
    }

    public Player getPlayer(ID game, Username user) {
        return games.get(game).getPlayer(user);
    }
}
