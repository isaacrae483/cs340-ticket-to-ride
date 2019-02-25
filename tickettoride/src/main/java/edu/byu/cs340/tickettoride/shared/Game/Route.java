package edu.byu.cs340.tickettoride.shared.Game;

import edu.byu.cs340.tickettoride.shared.User.Username;

public class Route {
    City startCity;
    City endCity;
    Length length;
    Colors color;
    Boolean claimed;
    Username claimedBy;

    public Route(City startCity, City endCity, Length length, Colors color) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.length = length;
        this.color = color;
        this.claimed = false;
    }

    public Colors getColor() { return color; }

    public City getStartCity() {
        return startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public Length getLength() {
        return length;
    }

    public Boolean getClaimed() {
        return claimed;
    }

    public void setClaimed(Boolean claimed) {
        this.claimed = claimed;
    }

    public void setClaimedBy(Username claimedBy) {
        this.claimedBy = claimedBy;
    }

    public Username getClaimedBy() {
        return claimedBy;
    }
}
