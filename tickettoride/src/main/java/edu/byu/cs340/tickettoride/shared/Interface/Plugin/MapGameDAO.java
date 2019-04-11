package edu.byu.cs340.tickettoride.shared.Interface.Plugin;

import java.util.HashMap;
import java.util.Map;


public class MapGameDAO {
    private Map<String, GameDAO> daos = new HashMap<>();
    public void add(String id, GameDAO dao) {
        daos.put(id, dao);
    }

    public GameDAO getGameDAO(String id) {
        return daos.get(id);
    }
}
