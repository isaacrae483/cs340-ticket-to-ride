package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class NextTurnCommand implements ICommand {

    public NextTurnCommand(ID game) {
        this.game = game;
    }

    private ID game;

    @Override
    public Object execute() {
        ClientFacade.instance().updateTurn(game);
        return null;
    }
}
