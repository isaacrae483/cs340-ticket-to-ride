package edu.byu.cs340.tickettoride.shared.Player;

public class TrainPieces {
    private int pieces;
    private int END_NUM_PIECES = 3;

    public TrainPieces(){
        pieces = 45;
    }

    public void playTrains(int amount) {
        pieces -= amount;
    }

    public boolean canBuild(int amount){
        if(pieces >= amount)
            return true;
        else
            return false;
    }

    public boolean lastTurn(){
        if(pieces <= END_NUM_PIECES)
            return true;
        else
            return false;
    }

    public int getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return Integer.toString(pieces);
    }
}
