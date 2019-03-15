package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class ClaimRouteCommand implements ICommand {
    private Route route;
    private Player player;

    public ClaimRouteCommand(Route route, Player player){
        this.player = player;
        this.route = route;
    }

    @Override
    public Object execute() {
        ClientFacade.instance().claimRoute(route, player);
        return null;
    }
}
