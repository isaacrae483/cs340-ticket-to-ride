package edu.byu.cs340.tickettoride.shared.Interface.Plugin;


import java.util.List;

public interface GameDAO {
    void updateGame(String data, String game);
    void deleteGame();
    String getGame();
    List<String> getCommands();
}
