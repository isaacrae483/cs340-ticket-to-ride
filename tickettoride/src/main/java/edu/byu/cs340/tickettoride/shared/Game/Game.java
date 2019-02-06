package edu.byu.cs340.tickettoride.shared.Game;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Game implements IGameListEntry {
    private ID id;
    private List<Username> users;
    private int numPlayers;
    private final int MAX_PLAYERS = 5;

    public Game() {
        id = ID.generate();
    }

    public Game(ID id) {
        this.id = id;
    }

    @Override
    public int getPlayerCount(){
        return users.size();
    }

    @Override
    public boolean isGameFull() {
        return (getPlayerCount() >= MAX_PLAYERS);
    }

    @Override
    public ID getId() {
        return id;
    }

    public List<Username> getUsers() {
        return users;
    }

    public void addPlayer(Username newUser){
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(newUser);
    }
}