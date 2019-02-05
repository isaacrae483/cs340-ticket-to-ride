package edu.byu.cs340.tickettoride.shared.Game;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Game{
    private ID id;
    private List<Username> users;
    private int numPlayers;

    public Game() {
        id = ID.generate();
    }

    public Game(ID id) {
        this.id = id;
    }

    public int getPlayerCount(){
        return users.size();
    }

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