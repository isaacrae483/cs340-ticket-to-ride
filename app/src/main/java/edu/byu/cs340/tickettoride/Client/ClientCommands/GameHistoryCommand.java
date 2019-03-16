package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class GameHistoryCommand implements ICommand {
    private String message;

    public GameHistoryCommand(String message){
        this.message = message;
    }

    @Override
    public Object execute() {
        ClientFacade.instance().addGameHistory(message);
        return null;
    }
}
