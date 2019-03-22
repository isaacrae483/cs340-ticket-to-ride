package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class DestDeckChangedCommand implements ICommand {
    public DestDeckChangedCommand(int offset) {
        this.offset = offset;
    }

    private int offset;

    @Override
    public Object execute() {
        ClientFacade.instance().changeDestDeckSize(offset);
        return null;
    }
}
