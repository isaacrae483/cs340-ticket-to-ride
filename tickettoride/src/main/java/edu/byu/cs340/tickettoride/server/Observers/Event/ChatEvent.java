package edu.byu.cs340.tickettoride.server.Observers.Event;

import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.ID;

public class ChatEvent extends GameEvent {
    public ChatEvent(ChatMessage message) {
        super(message.getGame());
        this.message = message;
    }

    public ChatMessage getMessage() {
        return message;
    }

    private ChatMessage message;

}
