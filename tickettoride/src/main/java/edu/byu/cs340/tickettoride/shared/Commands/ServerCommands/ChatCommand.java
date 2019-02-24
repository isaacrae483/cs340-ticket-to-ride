package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public class ChatCommand implements ICommand {

    private Username originUser;
    private String message;
    private ID gameId;

    public ChatCommand(String message, Username username, ID gameId) {
        this.originUser = username;
        this.message = message;
        this.gameId = gameId;
    }

    @Override
    public Object execute() {
        ServerFacade.SINGLETON.chat(originUser, message, gameId);
        return null;
    }
}
