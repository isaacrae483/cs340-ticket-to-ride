package edu.byu.cs340.tickettoride.shared.Game.Chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.EventBubbler;
import edu.byu.cs340.tickettoride.shared.Game.EventEmitter;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.events.chat.ChatAdded;

public class Chat extends EventBubbler implements Serializable {

    private List<ChatMessage> messages;
    private ID game;

    public class InvalidChatException extends Exception {
        public InvalidChatException(String message) {super(message);}
    }

    public Chat(ID game) {
        messages = new ArrayList<>();
        this.game = game;
    }

    public List<ChatMessage> getMessages(int index) {
        if (index >= messages.size()) {
            return new ArrayList<>();
        }
        return messages.subList(index, messages.size());
    }

    public void add(ChatMessage message) throws InvalidChatException {
        if (game.getId().equals(message.getGame().toString())) {
            messages.add(message);
/*            ChatAdded  chatEvent = new ChatAdded(message);
            update(chatEvent);*/
        }
        else {
            InvalidChatException ex =
                    new InvalidChatException("Game ID " + game.getId() + " is not correct");
            throw ex;
        }
    }
}
