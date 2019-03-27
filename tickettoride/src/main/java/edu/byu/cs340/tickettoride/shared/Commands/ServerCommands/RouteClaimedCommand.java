package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class RouteClaimedCommand implements ICommand {

    private Route route;
    private Username username;
    private ID game;

    public RouteClaimedCommand(Route route, Username username, ID game){
        this.username = username;
        this.route = route;
        this.game = game;
    }

    @Override
    public Object execute() {
        return ServerFacade.SINGLETON.routeClaimed(route, username, game);
    }
}
