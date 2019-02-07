package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Player.Player;

public class JoinGameResult {
    private Boolean success;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    public JoinGameResult(Boolean success, Player player) {
        this.player = player;
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }
}
