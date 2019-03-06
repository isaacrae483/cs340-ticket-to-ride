package edu.byu.cs340.tickettoride.Client.views;

import edu.byu.cs340.tickettoride.shared.Player.Player;

public interface IChatView {
    void displayNewMessage(Player player, String message);
}
