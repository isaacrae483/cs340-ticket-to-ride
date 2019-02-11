package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.Game;

public class CreateGameResult {
    private Boolean success;
    private Game game;

    public CreateGameResult(Boolean success, Game game) {
        this.success = success;
        this.game = game;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "CreateGameResult{" +
                "success=" + success +
                ", game=" + game +
                '}';
    }
}
