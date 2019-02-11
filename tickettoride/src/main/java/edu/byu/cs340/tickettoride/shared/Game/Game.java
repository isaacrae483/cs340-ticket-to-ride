package edu.byu.cs340.tickettoride.shared.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Game implements IGameListEntry {
    private ID id;
    private List<Player> players;
    private int numPlayers;
    private final int MAX_PLAYERS = 5;
    private final int MIN_PLAYERS = 2;
    boolean gameStarted = false;

    public Game() {
        id = ID.generate();
    }

    public Game(ID id) {
        this.id = id;
    }

    @Override
    public int getPlayerCount(){
        return players.size();
    }

    @Override
    public boolean isGameFull() {
        return (getPlayerCount() >= MAX_PLAYERS);
    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public boolean canGameBeStarted() {
        return (getPlayerCount() >= MIN_PLAYERS && getPlayerCount() <= MAX_PLAYERS);
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public Player GetLeader() {
        if (players != null && !players.isEmpty()) {
            return players.get(0);
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(player);
    }
}