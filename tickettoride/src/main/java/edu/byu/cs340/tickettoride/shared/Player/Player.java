package edu.byu.cs340.tickettoride.shared.Player;

import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Player implements IPlayer {

    private Username username;
    private Color color;

    public Player(Username username, Color color) {
        this.username = username;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Username getPlayerName() {
        return username;
    }
}
