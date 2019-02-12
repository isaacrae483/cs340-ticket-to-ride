package edu.byu.cs340.tickettoride.Client.model;

import java.util.List;
import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.model.events.ErrorEvent;
import edu.byu.cs340.tickettoride.Client.model.events.Event;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameAdded;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameListChanged;
import edu.byu.cs340.tickettoride.Client.model.events.game.PlayerCountChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelobby.GameStarted;
import edu.byu.cs340.tickettoride.Client.model.events.login.LoginSuccess;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.ActiveGameChanged;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Player.Player;
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
    private MapGames games = new MapGames();
    private ID activeGameID;

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
        setChanged();
        notifyObservers(new LoginSuccess());
    }

    public MapGames getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        for (Game g : games) {
            this.games.addGame(g);
        }
        emitEvent(new GameListChanged());
    }

    public Game getGame(ID id){
        return games.getGame(id);
    }

    public void addGame(Game game){
        games.addGame(game);
        emitEvent(new GameAdded(game));
    }

    public void incrementPlayers(ID id, Player newUser){
        Game game = this.getGame(id);
        game.addPlayer(newUser);
        emitEvent(new PlayerCountChanged(game));
    }

    public void startGame(ID gameId) {
        this.getGame(gameId).startGame();
        emitEvent(new GameStarted(this.getGame(gameId)));
    }

    public ID getActiveGameID() {
        return activeGameID;
    }

    public void setActiveGameID(ID activeGameID) {
        this.activeGameID = activeGameID;
        emitEvent(new ActiveGameChanged());
    }

    public Game getActiveGame() {
        return getGame(getActiveGameID());
    }

    public void passErrorEvent(ErrorEvent errorEvent) {
        emitEvent(errorEvent);
    }

    private void emitEvent(Event event) {
        setChanged();
        notifyObservers(event);
    }

}
