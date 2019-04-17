package edu.byu.cs340.tickettoride.shared.Interface.Plugin;


import java.util.List;

public interface DAOFactory {
    UserDAO generateUserDAO();
    GameDAO generateGameDAO(int deltas);
}
