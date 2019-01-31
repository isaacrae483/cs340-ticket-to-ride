package edu.byu.cs340.tickettoride.shared.Commands;

//import edu.byu.cs340.tickettoride.client.ClientModel;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class NewGameCommand implements ICommand {
    //sent from the server to the client to update list
    private Game game;
    @Override
    public Object execute() {
        //ClientModel.instance().addGame(game);
        return null;
    }


}
