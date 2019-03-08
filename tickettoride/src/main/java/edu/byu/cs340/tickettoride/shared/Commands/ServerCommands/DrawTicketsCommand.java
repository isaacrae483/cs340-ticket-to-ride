package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public class DrawTicketsCommand implements ICommand {

    private Username originUser;
    private ID gameId;

    public DrawTicketsCommand(Username username, ID gameId) {
        this.originUser = username;
        this.gameId = gameId;
    }

    @Override
    public DrawTicketsResult execute() {
        DrawTicketsResult res = ServerFacade.SINGLETON.drawTickets(originUser, gameId);
        return res;
    }
}
