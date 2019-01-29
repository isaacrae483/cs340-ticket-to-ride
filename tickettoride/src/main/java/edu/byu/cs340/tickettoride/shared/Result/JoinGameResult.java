package edu.byu.cs340.tickettoride.shared.Result;

public class JoinGameResult {
    private Boolean success;

    public JoinGameResult(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }
}
