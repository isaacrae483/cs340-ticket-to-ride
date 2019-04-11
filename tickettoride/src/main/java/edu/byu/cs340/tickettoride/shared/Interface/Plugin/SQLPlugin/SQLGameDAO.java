package edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.GameDAO;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.MapGameDAO;

public class SQLGameDAO implements GameDAO {
    @Override
    public void updateGame(String data, String game) {
        //if not at a checkpoint (numTillCheckpoint > 0)
            //save data as the delta (it will be of type serverCommandData)
            //in the table, the first column will be ID and the second column will be the data
            //(note: there should be multiple for each game at a time)
            //subtract one from numTillCheckpoint
        //else
            //save game
            //in the game table, save the game with the first column as ID and the second column as game
            //delete all commands from the delta table for that game
            //set numTillCheckpoint = 0
    }


    @Override
    public void deleteGame() {
        //delete rows from delta table and from game table where ID = id
    }

    @Override
    public String getGame() {
        //return game where ID = id
        return null;
    }

    @Override
    public String getCommands() {
        //return commands in delta table where ID = id
        return null;
    }

    public static MapGameDAO getGames() {
        MapGameDAO games = new MapGameDAO();
        //for each ID in game or deltas,
            //create a new GameDAO with that ID
            //add to games
        return games;
    }

    public SQLGameDAO(String id, int deltas) {
        this.id = id;
        this.deltas = deltas;
        numTillCheckpoint = deltas;
    }

    private String id;
    private int deltas;
    private int numTillCheckpoint;
}
