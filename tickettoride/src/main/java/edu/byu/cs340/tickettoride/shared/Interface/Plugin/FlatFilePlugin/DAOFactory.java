package edu.byu.cs340.tickettoride.shared.Interface.Plugin.FlatFilePlugin;


public interface DAOFactory {
    UserDAO generateUserDAO();
    GameDAO generateGameDAO(int deltas);
}
