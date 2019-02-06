package edu.byu.cs340.tickettoride.shared.Interface;

import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 2/5/19.
 */
public interface IPlayer {
    enum Color {
        BLUE,
        RED,
        GREEN,
        YELLOW,
        BLACK
    }

    Color getColor();
    Username getPlayerName();
}
