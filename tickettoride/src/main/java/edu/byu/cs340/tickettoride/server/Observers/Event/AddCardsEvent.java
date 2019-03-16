package edu.byu.cs340.tickettoride.server.Observers.Event;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class AddCardsEvent extends GameEvent {
    public AddCardsEvent(List<TrainCard> cards, Player player, ID game) {
        super(game);
        this.cards = cards;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public List<TrainCard> getCards() {
        return cards;
    }

    private Player player;
    private List<TrainCard> cards;
}
