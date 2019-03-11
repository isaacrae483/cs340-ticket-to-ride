package edu.byu.cs340.tickettoride.shared.Player;

public class Points {
    private int points;
    public Points(){
        points = 0;
    }

    public void addPoints(int add){
        points += add;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return Integer.toString(points);
    }
}
