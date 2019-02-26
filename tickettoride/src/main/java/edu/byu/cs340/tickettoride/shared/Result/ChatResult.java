package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;

public class ChatResult {
    private boolean success;
    private ChatMessage message;

    public ChatResult(boolean success, ChatMessage message) {
        this.message = message;
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }
    public ChatMessage getMessage() {return  message;}
}
