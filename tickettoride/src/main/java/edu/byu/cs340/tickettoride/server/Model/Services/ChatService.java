package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Chat.Chat;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.ChatResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ChatService {
    public ChatResult chat(Username username, String message, ID game) {
        ServerModel model = ServerModel.SINGLETON;
        Game chatGame = model.getGame(game);
        ChatMessage chat = null;
        boolean success = true;

        if (chatGame == null || !chatGame.contains(username)) {
            success = false;
        }
        else {
            try {
                chat = new ChatMessage(message, username, game);
                chatGame.getChat().add(chat);
                model.updateGame(chatGame,
                        new ServerCommandData(ServerCommandData.commandType.CHAT, chat));
            } catch (Chat.InvalidChatException ex) {
                success = false;
            }
        }
        return new ChatResult(success, chat);
    }
}
