package edu.byu.cs340.tickettoride.Client.presenters;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public interface IDestCardPresenter extends IPresenter {
    void drawPressed();
    void returnCard(DestCard card);
}
