package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class LoginService {
    public static LoginResult login(Username username, Password password){
        User user = ServerModel.SINGLTON.getMapUsers().getUser(username);
        if(user != null && user.getPassword() == password){
            return new LoginResult(ServerModel.SINGLTON.getMapGames(), true);
        }
        return new LoginResult(null, false);
    }
}
