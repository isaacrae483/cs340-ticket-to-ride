package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;

import java.sql.Connection;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.DAOFactory;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.GameDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.MapGameDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.UserDAO;

public class SQLDAOFactory implements DAOFactory {
    @Override
    public UserDAO generateUserDAO() {
        return new SQLUserDAO();
    }

    @Override
    public GameDAO generateGameDAO(String id, int deltas) {
        return new SQLGameDAO(id, deltas);
    }

    @Override
    public MapGameDAO getGameDAOs(int deltas) {
        return SQLGameDAO.getGames();
    }
}
