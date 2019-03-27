package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Board.Board;
import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 3/26/19.
 */
public class BoardService {

    public Boolean areTwoCitiesConnected(City a, City b, ID gameId) {
        if (a.equals(b)) {
            return true;
        }
        ServerModel model = ServerModel.SINGLETON;
        Game relevantGame = model.getStartedGame(gameId);



        return false;
    }

    public Username longestRoute(ID gameId) {
        return null;
    }


}
