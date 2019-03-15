package edu.byu.cs340.tickettoride.Client.ClientCommands;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class EndGameCommand implements ICommand {
    private List<Player> players;

    public EndGameCommand(List<Player> players){
        this.players = players;
    }


    @Override
    public Object execute() {
        ClientFacade.instance().endGame(players);
        return null;
    }
}
