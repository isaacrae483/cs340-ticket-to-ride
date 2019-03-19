package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class RouteClaimedCommand implements ICommand {

    private Route route;
    private Player player;
    private ID game;

    public RouteClaimedCommand(Route route, Player player, ID game){
        this.player = player;
        this.route = route;
        this.game = game;
    }

    @Override
    public Object execute() {
        ServerFacade.SINGLETON.routeClaimed(route, player.getPlayerName(), game);
        return null;
    }
}
