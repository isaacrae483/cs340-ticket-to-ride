package edu.byu.cs340.tickettoride.shared.User;

import java.util.HashMap;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;


public class MapUsers {
    Map<Username, User> users = new HashMap<>();

     public void addUser(User user){
         users.put(user.getUserName(), user);
     }
     public User getUser(Username username){
         return users.get(username);
     }
}
