package edu.byu.cs340.tickettoride.shared.Commands;

//import edu.byu.cs340.tickettoride.client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class IncrementPlayersCommand implements ICommand {
    ID gameID;
    Username newUser;
    @Override
    public Object execute() {
        //ClientFacade.instance().incrementPlayers(gameID, newUser);
        return null;
    }

    public Username getNewUser() {
        return newUser;
    }

    public ID getGameID() {
        return gameID;
    }
}
