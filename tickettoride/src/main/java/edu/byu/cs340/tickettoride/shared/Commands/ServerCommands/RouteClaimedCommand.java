package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class RouteClaimedCommand implements ICommand {

    private Route route;
    private Player player;

    public RouteClaimedCommand(Route route, Player player){
        this.player = player;
        this.route = route;
    }

    @Override
    public Object execute() {
        ServerFacade.SINGLETON.routeClaimed(route, player.getPlayerName());
        return null;
    }
}
