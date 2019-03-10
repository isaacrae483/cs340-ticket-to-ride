package edu.byu.cs340.tickettoride.shared.Game.Board;

import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Route implements IRoute {
    City startCity;
    City endCity;
    Length length;
    Colors color;
    Boolean claimed;
    Player claimedBy;
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

    public Player getClaimedBy() {
        return claimedBy;
    }

    @Override
    public IPlayer.Color getClaimColor() {
        if (!claimed || claimedBy == null)
            return IPlayer.Color.NEUTRAL;
        return claimedBy.getColor();
    }

    public Integer getId() {
        return id;
    }

    public void claimRoute (Player claimedBy) {
        //set claimed
        this.claimed = true;
        //set claimedBy
        this.claimedBy = claimedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null)
            return false;
        if (!(o.getClass().equals(getClass())))
            return false;
        Route otherRoute = (Route) o;
        if (!((startCity.equals(otherRoute.startCity) && endCity.equals(otherRoute.getEndCity()))
                || (startCity.equals(otherRoute.getEndCity()) && endCity.equals(otherRoute.getStartCity())))) {
            return false;
        }
        if (length.equals(otherRoute.getLength()))
            return true;
        return false;
    }
}
