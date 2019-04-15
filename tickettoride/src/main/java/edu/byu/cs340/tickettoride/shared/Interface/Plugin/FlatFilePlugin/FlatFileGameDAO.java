package edu.byu.cs340.tickettoride.shared.Interface.Plugin.FlatFilePlugin;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.GameDAO;

public class FlatFileGameDAO extends FlatFileParentDAO implements GameDAO{
    @Override
    public void updateGame(String data, String game, String id) {

    }

    @Override
    public void deleteGame(String id) {

    }

    @Override
    public String getGame(String id) {
        return null;
    }

    @Override
    public List<String> getCommands(String id) {
        return null;
    }

    @Override
    public List<String> getIDs() {
        return null;
    }
}
