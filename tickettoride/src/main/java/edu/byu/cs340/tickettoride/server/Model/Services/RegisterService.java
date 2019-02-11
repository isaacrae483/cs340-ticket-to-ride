package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.Model.ClientProxy;
import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class RegisterService {
    public static LoginResult register(Username username, Password password) {
        if (ServerModel.SINGLETON.getMapUsers().getUser(username) == null
            && username != null && password != null) {
            ServerModel.SINGLETON.getMapUsers().addUser(new User(username, password));
            ServerFacade.SINGLETON.AddObserver(new ClientProxy(username));
            return new LoginResult(ServerModel.SINGLETON.getMapGames().getAsListGames(), true);
        }
        return new LoginResult(null, false);
    }
}
