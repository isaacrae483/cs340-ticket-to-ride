package edu.byu.cs340.tickettoride.shared.Result;

public class FinishDrawingDestCardsResult {
    private boolean success;

    public FinishDrawingDestCardsResult(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }
}
