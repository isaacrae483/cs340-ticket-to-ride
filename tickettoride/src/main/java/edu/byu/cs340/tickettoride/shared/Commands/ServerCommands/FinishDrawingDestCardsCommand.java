package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.FinishDrawingDestCardsResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class FinishDrawingDestCardsCommand implements ICommand {
    public FinishDrawingDestCardsCommand(Username username, ID game) {
        this.username = username;
        this.game = game;
    }

    public ID getGame() {
        return game;
    }

    public Username getUsername() {
        return username;
    }

    public FinishDrawingDestCardsResult execute() {
        return ServerFacade.SINGLETON.finishDrawingDestCards(username, game);
    }

    private Username username;
    private  ID game;
}
