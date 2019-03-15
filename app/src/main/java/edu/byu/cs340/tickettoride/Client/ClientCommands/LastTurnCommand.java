package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class LastTurnCommand implements ICommand {

    public LastTurnCommand(){}

    @Override
    public Object execute() {
        ClientFacade.instance().lastTurn();
        return null;
    }
}
