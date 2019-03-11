package edu.byu.cs340.tickettoride.shared.Game.events.chat;

import edu.byu.cs340.tickettoride.shared.Game.events.Event;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;

public class ChatAdded implements Event {
    private ChatMessage mMessage;

    public ChatAdded(ChatMessage message) {
        mMessage = message;
    }

    public ChatMessage getMessage() {
        return mMessage;
    }
}
