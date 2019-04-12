package edu.byu.cs340.tickettoride.shared.User;

import java.util.HashMap;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;


public class MapUsers {
    private Map<Username, User> users = new HashMap<>();

     public void addUser(User user){
         users.put(user.getUserName(), user);
     }
     public User getUser(Username username){
         return users.get(username);
     }

     public void addUser(String username, String password) {
         Username username1 = null;
         Password password1 = null;
         try {
             username1 = new Username(username);
             password1 = new Password(password);
         }
         catch (Username.InvalidUserNameException|Password.InvalidPasswordException e) {
             e.printStackTrace();
         }
         addUser(new User(username1, password1));
     }
}
