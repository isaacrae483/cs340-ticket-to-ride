package edu.byu.cs340.tickettoride.shared.Player;

import java.io.Serializable;

public class Points implements Serializable {
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
