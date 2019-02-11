package edu.byu.cs340.tickettoride.shared.Result;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;

public class LoginResult {
    private Boolean success;
    private List<Game> games;

    public LoginResult(List<Game> games, Boolean success) {
        this.success = success;
        this.games = games;
    }

    public Boolean getSuccess() {
        return success;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "success=" + success +
                ", games=" + games +
                '}';
    }
}
