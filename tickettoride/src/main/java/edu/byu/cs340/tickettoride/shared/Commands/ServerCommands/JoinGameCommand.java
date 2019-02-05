package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class JoinGameCommand implements ICommand {
    public JoinGameCommand(Username username, ID game) {
        this.username = username;
        this.game = game;
    }

    private Username username;
    private ID game;

    @Override
    public JoinGameResult execute() {

        return ServerFacade.SINGLETON.joinGame(username, game);
    }
}
