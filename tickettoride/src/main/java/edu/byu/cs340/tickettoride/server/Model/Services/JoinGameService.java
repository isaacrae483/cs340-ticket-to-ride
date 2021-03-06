package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class JoinGameService {

    public static JoinGameResult joinGame(Username username, ID id){
        Game game = ServerModel.SINGLETON.getGame(id);

        if (game == null || username == null) {
            return new JoinGameResult(false, null, null);
        }

        boolean alreadyJoined = false; {
            for (Player p : game.getPlayers()) {
                if (p.getPlayerName().equals(username)) {
                    alreadyJoined = true;
                }
            }
        }

        if(game.getPlayerCount() < 5 && !alreadyJoined){ //***Fix the player count.
            Player player = new Player(username, IPlayer.Color.values()[game.getPlayerCount()]);
            game.addPlayer(player);
            ServerModel.SINGLETON.updateGame(game, new ServerCommandData(ServerCommandData.commandType.JOINGAME, username, id));
            return new JoinGameResult(true, player, id);
        }
        return new JoinGameResult(false, null, null);
    }
}
