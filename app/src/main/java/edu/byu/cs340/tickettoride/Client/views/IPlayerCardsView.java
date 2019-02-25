package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public interface IPlayerCardsView {
    void setPlayerCards(List<TrainCard> playerCards);
}
