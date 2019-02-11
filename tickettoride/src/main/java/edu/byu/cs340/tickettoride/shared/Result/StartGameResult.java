package edu.byu.cs340.tickettoride.shared.Result;

public class StartGameResult {
    private boolean success;

    public boolean getSuccess() {
        return success;
    }

    public StartGameResult(boolean success) {
        this.success = success;
    }
}
