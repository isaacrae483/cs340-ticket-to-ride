package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;


import java.util.List;

public interface GameDAO {
    void updateGame(String data, String game, String id);
    void deleteGame(String id);
    String getGame(String id);
    List<String> getCommands(String id);
    List<String> getIDs();
}
