package edu.byu.cs340.tickettoride.shared.Game;

import edu.byu.cs340.tickettoride.shared.User.Username;

public class Route {
    City startCity;
    City endCity;
    int length;
    Colors color;
    Boolean claimed = false;
    Username claimedBy;

    public Colors getColor() { return color; }

    public City getStartCity() {
        return startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public int getLength() {
        return length;
    }

    public Boolean getClaimed() {
        return claimed;
    }

    public Username getClaimedBy() {
        return claimedBy;
    }
}
