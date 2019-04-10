package edu.byu.cs340.tickettoride.shared.Interface.Plugin;

import edu.byu.cs340.tickettoride.shared.Game.ID;

public interface DAOFactory {
    UserDAO generateUserDAO();
    GameDAO generateGameDAO(ID id);
    MapGameDAO getGameDAOs();
}
