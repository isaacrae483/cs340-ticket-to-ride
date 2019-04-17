package com.example.allplugins.SQLPlugin;


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
