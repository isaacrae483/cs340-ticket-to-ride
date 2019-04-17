package com.example.allplugins.SQLPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SQLUserDAO extends SQLParentDAO implements UserDAO {
    Statement stmt;
    SQLUserDAO(){
        openConnection();
        createTables();
        closeConnection(true);
    }

    public void createTables(){
        stmt = null;
        try {
            createUserTable();
            createCommandsTable();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createUserTable() throws SQLException{
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `User` (\n" +
                    "\t`Username`\tTEXT NOT NULL UNIQUE,\n" +
                    "\t`Password`\tTEXT NOT NULL\n" +
                    ")");

        }finally {
            if(stmt != null){
                stmt.close();
                stmt = null;
            }
        }
    }
    private void createCommandsTable() throws SQLException{
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS 'Commands' (\n" +
                    "\t`Username`\tTEXT NOT NULL UNIQUE,\n" +
                    "\t`CommandList`\tTEXT NOT NULL\n"+
                    ")");

        }finally {
            if(stmt != null){
                stmt.close();
                stmt = null;
            }
        }
    }

    @Override
    public void register(String username, String password) {
        try{
            PreparedStatement stmt = null;
            try{
                openConnection();
                String sql = "insert into User values (?, ?)";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Could Not Register User");
                }
                closeConnection(true);
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
            closeConnection(false);
        }
    }

    @Override
    public Map<String, String> getUsers() {
        Map<String, String> map = new HashMap<>();
        try{
            openConnection();
            String query = "select * from User";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                map.put(results.getString(1), results.getString(2));
            }
            closeConnection(true);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            closeConnection(false);
            return null;
        }
    }

    @Override
    public void updateCommandQueue(String username, String commandList) {
        try{
            PreparedStatement stmt = null;
            try{
                openConnection();
                String sql = "insert or replace into Commands values (?, ?)";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, commandList);
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Could Not Add Command");
                }
                closeConnection(true);
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
            closeConnection(false);
        }
    }

    @Override
    public String getCommands(String username) {
        try{
            openConnection();
            String query = "select * from Commands where Username = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet results = stmt.executeQuery();
            String commands = null;
            while (results.next()) {
                commands = results.getString(2);
            }
            closeConnection(true);
            return commands;
        }catch(Exception e){
            e.printStackTrace();
            closeConnection(false);
            return null;
        }
    }
}
