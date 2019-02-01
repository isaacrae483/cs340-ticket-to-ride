package edu.byu.cs340.tickettoride.shared.Game;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Game{
    private ID id;
    private List<Username> users;

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
        users.add(newUser);
    }
}