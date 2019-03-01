package edu.byu.cs340.tickettoride.server.Model.Services;

import edu.byu.cs340.tickettoride.server.Server;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnTicketResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class DestCardService {

    public ReturnTicketResult returnTickets(Username username, DestCard card, ID game) {
        boolean success = false;
        ServerModel model = ServerModel.SINGLETON;

        Game gameInfo = model.getMapStartedGames().getGame(game);
        if (gameInfo != null && gameInfo.contains(username)) {
            try {
                gameInfo.returnCard(card);
                success = true;
            }
            catch (DestCardDeck.AlreadyInDeckException ex) {
                success = false;
            }
        }

        return new ReturnTicketResult(success);
    }


    public DrawTicketsResult drawTickets(Username username, ID game) {
        return null;
    }
}
