package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;

public interface IChatView {
    void setChatList(List<ChatMessage> messages);

    void displayNewMessage(ChatMessage message);
}
