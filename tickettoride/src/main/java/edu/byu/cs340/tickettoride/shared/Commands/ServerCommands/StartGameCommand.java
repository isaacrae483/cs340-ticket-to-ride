package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class StartGameCommand implements ICommand {

    private Username username;
    private ID game;

    public StartGameCommand(Username user, ID game) {
        username = user;
        this.game = game;
    }

    @Override
    public Object execute() {
        return ServerFacade.SINGLETON.startGame(username, game);
    }
}
