package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceDownCardResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 3/25/19.
 */
public class DrawFaceDownCardCommand implements ICommand {
    private Username player;
    private ID gameId;

    public DrawFaceDownCardCommand(Username player, ID gameId) {
        this.player = player;
        this.gameId = gameId;
    }

    @Override
    public DrawFaceDownCardResult execute() {
        return ServerFacade.SINGLETON.drawFaceDownCard(player, gameId);
    }
}
