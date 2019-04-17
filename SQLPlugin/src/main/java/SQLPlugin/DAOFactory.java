package com.example.allplugins.SQLPlugin;


public interface DAOFactory {
    UserDAO generateUserDAO();
    GameDAO generateGameDAO(int deltas);
}
