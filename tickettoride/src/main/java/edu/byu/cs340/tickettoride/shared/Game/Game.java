package edu.byu.cs340.tickettoride.shared.Game;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.User.User;

public class Game{
    private ID id;
    private List<User> users;

    public int getPlayerCount(){
        return users.size();
    }

    public ID getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }
}