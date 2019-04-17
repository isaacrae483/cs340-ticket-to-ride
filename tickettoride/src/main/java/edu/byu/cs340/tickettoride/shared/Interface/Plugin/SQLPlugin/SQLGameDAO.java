package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.GameDAO;

public class SQLGameDAO extends SQLParentDAO implements GameDAO {
    // ----- DataBase Initialization ------
    private int deltas;
    public SQLGameDAO(int deltas) {
        this.deltas = deltas;
        openConnection();
        createTables();
        closeConnection(true);

    }



    public void createTables(){
        Statement stmt = null;
        try {
            try {
                stmt = connection.createStatement();
                //(note: there should be multiple for each game at a time)
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS 'Delta' (\n" +
                        "\t`ID`\tTEXT NOT NULL,\n" +
                        "\t`Data`\tTEXT NOT NULL\n" +
                        ")");
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS 'Game' (\n" +
                        "\t`ID`\tTEXT NOT NULL UNIQUE,\n" +
                        "\t`Game`\tTEXT NOT NULL\n" +
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
        if(getCount(id) < deltas){
            //save data as the delta ?(it will be of type serverCommandData)? *
            try{
                PreparedStatement stmt = null;
                //in the table, the first column will be ID and the second column will be the data
                try{
                    openConnection();
                    String sql = "insert into Delta values (?,?)";
                    stmt = connection.prepareStatement(sql);
                    stmt.setString(1, id);
                    stmt.setString(2, data);
                    if(stmt.executeUpdate() != 1){
                        throw new Exception ("Could Not Save Delta");
                    }
                    closeConnection(true);
                }finally {
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                closeConnection(false);
            }
            //subtract one from numTillCheckpoint
            //numTillCheckpoint--;
        } else {
            //save game
            try{
                PreparedStatement stmt = null;
                //in the game table, save the game with the first column as ID and the second column as game
                try{
                    openConnection();
                    String sql = "insert into Game values (?,?)";
                    stmt = connection.prepareStatement(sql);
                    stmt.setString(1, id);
                    stmt.setString(2, game);
                    if(stmt.executeUpdate() != 1){
                        throw new Exception ("Could Not Save Game");
                    } else {
                        //delete all commands from the delta table for that game
                        sql = "delete from Delta where ID = ?";
                        stmt = connection.prepareStatement(sql);
                        stmt.setString(1, id);
                        stmt.executeUpdate();
                    }
                    closeConnection(true);
                }finally {
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                        //set numDeltas = 0
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                closeConnection(false);
            }

        }
    }


    @Override
    public void deleteGame(String id) {
        //delete rows from delta table and from game table where ID = id
        try{
            PreparedStatement stmt = null;
            try{
                openConnection();
                String sql = "delete from Game where ID = ?";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, id);
                if(stmt.executeUpdate() != 1){
                    throw new Exception ("Could Not Delete Game");
                }
                closeConnection(true);
            }finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection(false);
        }
    }

    @Override
    public String getGame(String id) {
        //return game where ID = id
        String game = null; // what type should this have?
        try{
            openConnection();
            String sql = "select * from Game where ID = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                game = results.getString(2);
            }
            closeConnection(true);
            return game;
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection(false);
            return null;
        }
    }

    @Override
    public List<String> getCommands(String id) {
        //return commands in delta table where ID = id
        List<String> commands = new ArrayList<>();
        try{
            openConnection();
            String query = "select * from Delta where ID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                commands.add(results.getString(2));
            }
            closeConnection(true);
            return commands;
        }catch(Exception e){
            e.printStackTrace();
            closeConnection(false);
            return null;
        }
    }

    @Override
    public List<String> getIDs() {
        List<String> games = new ArrayList<>();
        try{
            //for each ID in game or deltas,
            openConnection();
            String query = "select * from Game union all select * from Delta";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // add game id to games
                games.add(results.getString(1));
            }
        }catch(Exception e){
            e.printStackTrace();
            closeConnection(false);
            return null;
        }
        closeConnection(true);
        return games;
    }

    private int getCount (String id){
        int count = 0;
        try{
            openConnection();
            //for each ID in game or deltas,
            String query = "select count(?) as total from Delta";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, id);

            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                count = results.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
            closeConnection(false);
            return -1;
        }

        closeConnection(true);
        return count;
    }

}
