package edu.byu.cs340.tickettoride.shared.Interface.Plugin;

import java.util.HashMap;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.Game.ID;

public class MapGameDAO {
    private Map<ID, GameDAO> daos = new HashMap<ID, GameDAO>();
    public void add(ID id, GameDAO dao) {
        daos.put(id, dao);
    }

    public GameDAO getGameDAO(ID id) {
        return daos.get(id);
    }
}
