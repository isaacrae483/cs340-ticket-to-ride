package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.Model.ClientProxy;
import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class LoginService {
    public static LoginResult login(Username username, Password password){
        User user = ServerModel.SINGLETON.getUser(username);

        if(user != null && user.getPassword().equals(password)) {
            ServerFacade.SINGLETON.addObserver(new ClientProxy(username));
            ServerModel.SINGLETON.getCommands(username);
            return new LoginResult(ServerModel.SINGLETON.getUnstartedGames(), true);
        }

        return new LoginResult(null, false);
    }
}
