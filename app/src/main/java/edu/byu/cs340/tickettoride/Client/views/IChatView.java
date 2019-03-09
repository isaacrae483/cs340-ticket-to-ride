package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IChatView {
    void setChatList(List<ChatMessage> messages);

    void displayNewMessage(Username username, String message);
}
