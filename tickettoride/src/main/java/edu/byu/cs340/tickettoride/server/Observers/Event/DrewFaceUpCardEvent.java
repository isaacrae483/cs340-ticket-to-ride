package edu.byu.cs340.tickettoride.server.Observers.Event;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;

/**
 * Created by Thomas Lewis on 4/1/19.
 */
public class DrewFaceUpCardEvent extends GameEvent {

    public DrewFaceUpCardEvent(int trainCardDeckSize, Player player, List<TrainCard> faceUpCards, ID gameId) {
        super(gameId);
        this.player = player;
        this.trainCardDeckSize = trainCardDeckSize;
        this.newFaceUpCards = faceUpCards;
    }

    public Player getPlayer() {
        return player;
    }

    public int getTrainCardDeckSize() {
        return trainCardDeckSize;
    }

    public List<TrainCard> getNewFaceUpCards() {
        return newFaceUpCards;
    }

    private Player player;
    private int trainCardDeckSize;
    private List<TrainCard> newFaceUpCards;
}