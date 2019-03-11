package edu.byu.cs340.tickettoride.server.Model.Services;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.server.Server;
import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class StartGameService  {
    public StartGameResult startGame(Username username, ID id) {
        ServerModel model = ServerModel.SINGLETON;
        Game game = model.getMapNewGames().getGame(id);
        StartGameResult res = null;

        if (game != null && game.GetLeader().getPlayerName().equals(username)) {
            game.startGame();
            model.getMapNewGames().remove(id);
            model.getMapStartedGames().addGame(game);
            res = new StartGameResult(true);
            for (Player p : game.getPlayers()) {
                this.PlayerDrawCards(p, game);
            }
        }
        else {
            res = new StartGameResult(false);
        }

        return res;
    }

    private void PlayerDrawCards(Player p, Game game) {
        List<TrainCard> hand = new ArrayList<>();
        final int HAND_SIZE = 4;
        for (int i = 0; i < HAND_SIZE; ++i) {
            hand.add(game.drawCard());
        }
        ServerFacade.SINGLETON.playerDrew(p, hand);
    }
}
