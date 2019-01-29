package edu.byu.cs340.tickettoride.shared;

public interface IClient {
    void incrementPlayers(ID id);
    void addGame(Game game);
}
