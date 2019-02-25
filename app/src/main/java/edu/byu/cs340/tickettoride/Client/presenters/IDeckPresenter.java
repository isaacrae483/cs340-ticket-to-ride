package edu.byu.cs340.tickettoride.Client.presenters;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public interface IDeckPresenter {
    void cardPressed(TrainCard card);
    void deckPressed();
}
