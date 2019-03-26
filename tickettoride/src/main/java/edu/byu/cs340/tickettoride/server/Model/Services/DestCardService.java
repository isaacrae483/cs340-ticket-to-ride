package edu.byu.cs340.tickettoride.server.Model.Services;

import java.util.Set;

import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnTicketResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class DestCardService {

    public ReturnTicketResult returnTickets(Username username, DestCard card, ID game) {
        boolean success = false;
        ServerModel model = ServerModel.SINGLETON;

        Game gameInfo = model.getMapStartedGames().getGame(game);
        if (gameInfo != null && gameInfo.contains(username)) {
            Player player = gameInfo.getPlayer(username);
            try {
                player.returnDestCard(gameInfo, card);
                success = true;
            }
            catch (DestCardDeck.AlreadyInDeckException ex) {
                success = false;
            }
        }

        return new ReturnTicketResult(success, card);
    }


    public DrawTicketsResult drawTickets(Username username, ID game) {
        boolean success = false;
        ServerModel model = ServerModel.SINGLETON;
        int numCards = 0;

        Game gameInfo = model.getMapStartedGames().getGame(game);
        if (gameInfo != null && gameInfo.contains(username)) {
            Player player = gameInfo.getPlayer(username);
            int before = player.getNumDestCards();
            player.drawDestCard(gameInfo);
            numCards = player.getNumDestCards() - before;
            success = true;
        }

        return new DrawTicketsResult(success, numCards);
    }
}
