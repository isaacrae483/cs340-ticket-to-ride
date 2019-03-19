package edu.byu.cs340.tickettoride.shared.Game.Board;

public class Length {
    int length;

    public Length(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getPoints() {
        switch (length) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
        }
        return 0;
    }
}
