package edu.byu.cs340.tickettoride.shared.Interface.Plugin;


public interface DAOFactory {
    UserDAO generateUserDAO();
    GameDAO generateGameDAO();
    MapGameDAO getGameDAOs();
}
