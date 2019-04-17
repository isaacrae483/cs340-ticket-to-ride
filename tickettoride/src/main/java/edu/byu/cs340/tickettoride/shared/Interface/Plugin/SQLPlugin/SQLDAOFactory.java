package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;

import java.sql.Connection;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.DAOFactory;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.GameDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin.SQLGameDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin.SQLUserDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.UserDAO;

public class SQLDAOFactory implements DAOFactory {
    @Override
    public UserDAO generateUserDAO() {
        return new SQLUserDAO();
    }

    @Override
    public GameDAO generateGameDAO(int deltas) {
        return new SQLGameDAO(deltas);
    }

}
