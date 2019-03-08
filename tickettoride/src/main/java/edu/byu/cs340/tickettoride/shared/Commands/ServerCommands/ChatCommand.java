package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public class ChatCommand implements ICommand {

    private ChatMessage message;

    public ChatCommand(ChatMessage message) {
        this.message = message;
    }

    @Override
    public Object execute() {
        ServerFacade.SINGLETON.chat(message);
        return null;
    }
}
