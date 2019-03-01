package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public class ReturnCardCommand implements ICommand {

    private Username mUsername;
    private DestCard mDestCard;
    private ID mGameId;

    public ReturnCardCommand(Username username, DestCard destCard, ID gameId) {
        mUsername = username;
        mDestCard = destCard;
        mGameId = gameId;
    }

    @Override
    public Object execute() {
        ServerFacade.SINGLETON.returnTickets(mUsername, mDestCard, mGameId);
        return null;
    }
}
