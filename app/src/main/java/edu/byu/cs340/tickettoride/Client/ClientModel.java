package edu.byu.cs340.tickettoride.Client;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientModel {
    private ClientModel _instance;

    private ClientModel(){

    }
    public ClientModel instance(){
        if (_instance == null){
            _instance = new ClientModel();
        }
        return _instance;
    }

    private Username username;
    private MapGames games;
    private ID gameID;

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

    public ID getGameID() {
        return gameID;
    }

    public void setGameID(ID gameID) {
        this.gameID = gameID;
    }
}
