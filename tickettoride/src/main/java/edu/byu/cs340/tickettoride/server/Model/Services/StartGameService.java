package edu.byu.cs340.tickettoride.server.Model.Services;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.server.Server;
import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class StartGameService  {
    public StartGameResult startGame(Username username, ID id) {
        ServerModel model = ServerModel.SINGLETON;
        Game game = model.getGame(id);
        StartGameResult res = null;

        if (game != null && game.GetLeader().getPlayerName().equals(username)) {
            game.startGame();
            model.startGame(id);
            res = new StartGameResult(true);
            for (Player p : game.getPlayers()) {
                this.PlayerDrawCards(p, game);
            }
            InitializeFaceUp(game);
            ServerFacade.SINGLETON.setTCDeckSize(game, game.getTrainCardDeckSize());
            model.updateGame(game,
                    new ServerCommandData(ServerCommandData.commandType.STARTGAME, username, id));
        }
        else {
            res = new StartGameResult(false);
        }

        return res;
    }

    private void PlayerDrawCards(Player p, Game game) {
        final int HAND_SIZE = 4;
        Player player = game.getPlayer(p.getPlayerName());
        for (int i = 0; i < HAND_SIZE; ++i) {
            player.DrawCard(game.drawCard());
        }
        ServerFacade.SINGLETON.playerDrew(p, game.getId());
        ServerFacade.SINGLETON.drawTickets(p.getPlayerName(), game.getId());
    }

    private void InitializeFaceUp(Game game) {
        final int NUM_FACE_UP = 5;
        for (int i = 0; i < NUM_FACE_UP; ++i) {
            ServerFacade.SINGLETON.SetFaceUpCard(game, game.peekFaceUp(i), i);
        }
    }

    private void DrawDestCards(Game game, Player player) {

    }
}
