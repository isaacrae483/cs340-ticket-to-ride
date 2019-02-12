package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class JoinGameResult {
    private Boolean success;
    private ID id;
    private Player player;

    public JoinGameResult(Boolean success, Player player, ID id) {
        this.success = success;
        this.id = id;
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getSuccess() {
        return success;
    }
    public Player getPlayer() {
        return player;
    }

    public ID getId() {
        return id;
    }
}
