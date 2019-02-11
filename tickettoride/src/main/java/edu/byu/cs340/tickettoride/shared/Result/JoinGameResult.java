package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class JoinGameResult {
    private Boolean success;
    private ID id;


    public JoinGameResult(Boolean success, ID id) {
        this.success = success;
        this.id = id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ID getId() {
        return id;
    }
}
