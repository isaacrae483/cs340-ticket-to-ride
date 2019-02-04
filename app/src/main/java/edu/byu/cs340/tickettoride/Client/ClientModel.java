package edu.byu.cs340.tickettoride.Client;

import java.util.Observable;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientModel extends Observable {
    private static ClientModel _instance;
    private ClientModel(){

    }
    public static ClientModel instance(){
        if (_instance == null){
            _instance = new ClientModel();
        }
        return _instance;
    }

    private Username username;
    private MapGames games;
    private ID activeGameID;

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public MapGames getGames() {
        return games;
    }

    public void setGames(MapGames games) {
        this.games = games;
    }

    public Game getGame(ID id){
        return games.getGame(id);
    }

    public void addGame(Game game){
        games.addGame(game);
    }

    public void incrementPlayers(ID id, Username newUser){
        games.getGame(id).addPlayer(newUser);
    }

    public ID getActiveGameID() {
        return activeGameID;
    }

    public void setActiveGameID(ID activeGameID) {
        this.activeGameID = activeGameID;
    }
}
