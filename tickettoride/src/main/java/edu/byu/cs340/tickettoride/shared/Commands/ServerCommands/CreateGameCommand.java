package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class CreateGameCommand implements ICommand {
    //sent from client to the server to tell the server to create a game and add to the list
    public CreateGameCommand(Username username) {
        this.username = username;
    }

    private Username username;


    @Override
    public CreateGameResult execute() {
        return ServerFacade.SINGLETON.createGame(username);
    }
}
