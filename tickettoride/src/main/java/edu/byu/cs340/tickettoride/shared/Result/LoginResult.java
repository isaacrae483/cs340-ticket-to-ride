package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.MapGames;

public class LoginResult {
    private Boolean success;
    private MapGames games;

    public LoginResult(MapGames games, Boolean success) {
        this.success = success;
        this.games = games;
    }

    public Boolean getSuccess() {
        return success;
    }

    public MapGames getGames() {
        return games;
    }
}
