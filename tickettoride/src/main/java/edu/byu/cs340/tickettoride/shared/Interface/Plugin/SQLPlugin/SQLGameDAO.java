package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.GameDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.MapGameDAO;

public class SQLGameDAO implements GameDAO {
    // ----- DataBase Initialization ------
    Connection conn;
    SQLGameDAO(Connection connection){
        createTable(connection);
        conn = connection;
    }

    public void createTable(Connection connection){
        Statement stmt = null;
        try {
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate("DROP TABLE IF EXISTS Game");
                stmt.executeUpdate("DROP TABLE IF EXISTS Delta");
                stmt.executeUpdate("CREATE TABLE 'Delta' (" +
                        //(note: there should be multiple for each game at a time)
                        "ID TEXT NOT NULL," +
                        "Data TEXT NOT NULL,"+
                        ")");
                stmt.executeUpdate("CREATE TABLE 'Game' (" +
                        "ID TEXT NOT NULL UNIQUE," +
                        "Game TEXT NOT NULL,"+
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

    // ----- GameDAO Operations -----
    @Override

    public void updateGame(String data, String game, String id) {
        //if not at a checkpoint (numTillCheckpoint > 0)
        if(/*numTillCheckpoint*/ 1 > 0){
            //save data as the delta ?(it will be of type serverCommandData)? *
            try{
                PreparedStatement stmt = null;
                //in the table, the first column will be ID and the second column will be the data
                try{
                    String sql = "insert into Delta values (?,?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, id);
                    stmt.setString(2, data);
                    if(stmt.executeUpdate() != 1){
                        throw new Exception ("Could Not Save Delta");
                    }
                }finally {
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //subtract one from numTillCheckpoint
            //numTillCheckpoint--;
        } else {
            //save game
            try{
                PreparedStatement stmt = null;
                //in the game table, save the game with the first column as ID and the second column as game
                try{
                    String sql = "insert int Game values (?,?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, id);
                    stmt.setString(2, game);
                    if(stmt.executeUpdate() != 1){
                        throw new Exception ("Could Not Save Game");
                    } else {
                        //delete all commands from the delta table for that game
                        sql = "delete from Delta where ID = ?";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, game);
                        if(stmt.executeUpdate() != 1){
                            throw new Exception ("Could Not Delete Deltas");
                        }
                    }
                }finally {
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //set numTillCheckpoint = 0 (do you mean back to this.deltas?)
            //numTillCheckpoint = this.deltas;
        }
    }


    @Override
    public void deleteGame(String id) {
        //delete rows from delta table and from game table where ID = id
        try{
            PreparedStatement stmt = null;
            try{
                String sql = "delete from Game where ID = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                if(stmt.executeUpdate() != 1){
                    throw new Exception ("Could Not Delete Game");
                }
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getGame(String id) {
        //return game where ID = id
        String game = null; // what type should this have?
        try{
            String sql = "select from Game where ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                game = results.getString(2);
            }
            return game;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getCommands(String id) {
        //return commands in delta table where ID = id
        List<String> commands = new ArrayList<>();
        try{
            String query = "select * from Delta where ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                commands.add(results.getString(2));
            }
            return commands;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    // This cod needs help. How and why do we create a new GameDAO here?

    public Map<String, String> getGames(String id) {
        Map<String, String> games = new HashMap<>();
        try{
            //for each ID in game or deltas,
            String query = "select * from Delta UNION ALL " +
                    "select * from Game";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // create a new GameDAO with that ID
                // add to games
                games.put(results.getString(1), results.getString(2));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return games;
    }

    public SQLGameDAO(int deltas) {
        this.deltas = deltas;
        // numTillCheckpoint = deltas;
    }

    private int deltas;
    // private int numTillCheckpoint; // This needs to be changed to check how many deltas are in the table.
}
