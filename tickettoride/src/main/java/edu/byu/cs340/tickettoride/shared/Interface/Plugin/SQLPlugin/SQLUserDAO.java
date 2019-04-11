package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;

import java.util.Map;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.UserDAO;

public class SQLUserDAO implements UserDAO {
    @Override
    public void register(String username, String password) {

    }

    @Override
    public Map<String, String> getUsers() {
        return null;
    }

    @Override
    public void updateCommandQueue(String username, String commandList) {

    }
}
