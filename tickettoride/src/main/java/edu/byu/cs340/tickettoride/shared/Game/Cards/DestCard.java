package edu.byu.cs340.tickettoride.shared.Game.Cards;

import java.util.Objects;

import edu.byu.cs340.tickettoride.shared.Game.Enums.City;

public class DestCard {

    public DestCard(City city1, City city2, int points) {
        this.city1 = city1;
        this.city2 = city2;
        this.points = points;
    }

    private City city1;
    private City city2;
    private int points;

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestCard destCard = (DestCard) o;
        boolean pointsPatch = points == destCard.points;
        //if cities are same, order does not matter
        boolean citiesMatch =
                (city1 == destCard.city1 && city2 == destCard.city2)
                || (city1 == destCard.city2 && city2 == destCard.city1);
        return  pointsPatch && citiesMatch;

    }

    @Override
    public int hashCode() {
        //order of cities should not matter
        return Objects.hash(city1, city2, points) + Objects.hash(city2, city1, points);
    }
}
