package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

/**
 * Created by Thomas Lewis on 2/11/19.
 */
public class GameStartedCommand implements ICommand {
    private ID mGameId;

    public GameStartedCommand(ID gameId) {
        mGameId = gameId;
    }

    @Override
    public Object execute() {
        ClientFacade.instance().startGame(mGameId);
        return null;
    }
}
