package edu.byu.cs340.tickettoride.shared.Result;

public class ReturnTicketResult {
    boolean success;
    public ReturnTicketResult(boolean success){
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }
}
