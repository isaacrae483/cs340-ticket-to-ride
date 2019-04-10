package edu.byu.cs340.tickettoride.shared.DAOs.Relational;

import edu.byu.cs340.tickettoride.server.Model.CommandList;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.UserDAO;
import edu.byu.cs340.tickettoride.shared.User.MapUsers;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class RelationalUserDAO implements UserDAO {
    @Override
    public void register(Username username, Password password) {

    }

    @Override
    public MapUsers get() {
        return null;
    }

    @Override
    public void updateCommandQueue(CommandList commandList) {

    }
}
