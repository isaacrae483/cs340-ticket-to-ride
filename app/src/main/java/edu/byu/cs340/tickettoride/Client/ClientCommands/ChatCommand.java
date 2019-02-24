package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public class ChatCommand implements ICommand {

    private Username mOriginUser;
    private String mMessage;
    private ID mGameId;

    public ChatCommand(Username originUser, ID gameId, String message) {
        mOriginUser = originUser;
        mMessage = message;
        mGameId = gameId;
    }

    @Override
    public Object execute() {
        ClientFacade.instance().receiveChat(mOriginUser, mGameId, mMessage);
        return null;
    }
}
