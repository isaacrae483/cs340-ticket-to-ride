package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.UserDAO;

public class SQLUserDAO implements UserDAO {
    Connection conn;
    Statement stmt;
    SQLUserDAO(Connection connection){
        createTable(connection);
        conn = connection;
    }

    public void createTable(Connection connection){
        Statement stmt = null;
        try {
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate("DROP TABLE IF EXISTS User");
                stmt.executeUpdate("CREATE TABLE 'User' (\n" +
                        "\t`Username`\tTEXT NOT NULL UNIQUE,\n" +
                        "\t`Password`\tTEXT NOT NULL,\n"+
                        ")");

            }finally {
                if(stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void register(String username, String password) {
        try{
            PreparedStatement stmt = null;
            try{
                String sql = "insert into User values (?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Could Not Register User");
                }
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getUsers() {
        Map<String, String> map = new HashMap<>();
        try{
            String query = "select * from User";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                map.put(results.getString(1), results.getString(2));
            }
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCommandQueue(String username, String commandList) {
        try{
            PreparedStatement stmt = null;
            try{
                String sql = "insert into Commands values (?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, commandList);
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Could Not Add Command");
                }
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
