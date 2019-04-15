package edu.byu.cs340.tickettoride.shared.Interface.Plugin.FlatFilePlugin;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.DAOFactory;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.GameDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.UserDAO;

public class FlatFileFactory implements DAOFactory {
    @Override
    public UserDAO generateUserDAO() {
        return new FlatFileUserDAO();
    }

    @Override
    public GameDAO generateGameDAO(int deltas) {
        return new FlatFileGameDAO(deltas);
    }
}
