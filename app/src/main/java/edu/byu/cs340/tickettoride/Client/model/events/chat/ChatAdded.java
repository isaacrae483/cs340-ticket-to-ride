package edu.byu.cs340.tickettoride.Client.model.events.chat;

import edu.byu.cs340.tickettoride.Client.model.events.Event;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;

public class ChatAdded implements Event {
    private ChatMessage mMessage;

    public ChatAdded(ChatMessage message) {
        mMessage = message;
    }

    public ChatMessage getMessage() {
        return mMessage;
    }
}
