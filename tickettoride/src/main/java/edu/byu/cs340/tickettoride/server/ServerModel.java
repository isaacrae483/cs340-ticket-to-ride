package edu.byu.cs340.tickettoride.server;

import edu.byu.cs340.tickettoride.server.Model.CommandList;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.User.MapUsers;

public class ServerModel {
    public static final ServerModel SINGLETON = new ServerModel();
    private ServerModel(){}
    private MapGames mapGames = new MapGames();
    private MapUsers mapUsers = new MapUsers();

    public CommandList getCommandList() { return commandList; }

    private CommandList commandList = new CommandList();

    public MapGames getMapGames() {
        return mapGames;
    }

    public MapUsers getMapUsers() {
        return mapUsers;
    }

    //for testing purposes only
    public void Reset() {
        mapGames = new MapGames();
        mapUsers = new MapUsers();
    }

//implement methods for login, register, joinGame, newGame here.
}
