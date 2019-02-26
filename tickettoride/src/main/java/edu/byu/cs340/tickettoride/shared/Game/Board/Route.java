package edu.byu.cs340.tickettoride.shared.Game.Board;

import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Route {
    City startCity;
    City endCity;
    Length length;
    Colors color;
    Boolean claimed;
    Username claimedBy;
    Integer id;

    public Route(Integer id, City startCity, City endCity, Integer length, Colors color) {
        this.id = id;
        this.startCity = startCity;
        this.endCity = endCity;
        this.length = new Length(length);
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

    public void claimRoute () {
        //set claimed
        //set claimedBy
        // you can probably delete setClaimed and setClaimedBy
    }
}
