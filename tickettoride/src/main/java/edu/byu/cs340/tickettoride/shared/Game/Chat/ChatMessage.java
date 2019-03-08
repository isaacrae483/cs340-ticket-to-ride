package edu.byu.cs340.tickettoride.shared.Game.Chat;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ChatMessage {
    private String message;
    private Username user;
    private ID game;

    public ChatMessage(String message, Username user, ID game) {
        this.message = message;
        if(this.message == null){
            this.message = "";
        }
        this.user = user;
        this.game = game;
    }

    public String getMessage() {
        return message;
    }

    public Username getUser() {
        return user;
    }

    public ID getGame() {
        return game;
    }
}
