package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLParentDAO {
//starts the database and makes the file
    static{
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
            // ERROR! Could not load database driver
        }
    }
    protected Connection connection = null;
    protected Statement stmt = null;

    public void openConnection() {
        String connectionURL = "jdbc:sqlite:cs340-ticket-to-ride.sqlite";

        try {
            // Open a database connection
            connection = DriverManager.getConnection(connectionURL);

            // Start a transaction
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
            // ERROR
        }
    }

    public void closeConnection(boolean commit){
        try{
            //closes the connection and roles back if needed
            if(commit){
                connection.commit();
                connection.close();
                connection = null;
            }
            else{
                connection.rollback();
                connection.close();
                connection = null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

}