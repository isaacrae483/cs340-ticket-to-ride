package edu.byu.cs340.tickettoride.shared.Game.Cards;

import java.io.Serializable;

import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

public class TrainCard implements Serializable {
    private Colors color;

    public TrainCard(Colors color) {
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }
}
