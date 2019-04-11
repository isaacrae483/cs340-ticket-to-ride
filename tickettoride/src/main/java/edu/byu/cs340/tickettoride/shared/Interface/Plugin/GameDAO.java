package edu.byu.cs340.tickettoride.shared.Interface.Plugin;


public interface GameDAO {
    void updateGame(String data, String game);
    void deleteGame();
    String getGame();
    String getCommands();
}
