package edu.byu.cs340.tickettoride.server.Observers.Event;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class AddCardsEvent extends GameEvent {
    public AddCardsEvent(Player player, ID game) {
        super(game);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    private Player player;
}
