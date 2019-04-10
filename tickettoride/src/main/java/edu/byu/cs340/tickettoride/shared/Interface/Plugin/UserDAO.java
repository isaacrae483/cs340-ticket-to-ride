package edu.byu.cs340.tickettoride.shared.Interface.Plugin;

import java.util.Map;

import edu.byu.cs340.tickettoride.server.Model.CommandList;
import edu.byu.cs340.tickettoride.shared.User.MapUsers;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface UserDAO {
    void register(Username username, Password password);
    MapUsers get();
    void updateCommandQueue(CommandList commandList);
}
