package edu.byu.cs340.tickettoride.Client.views;

/**
 * Created by Thomas Lewis on 2/6/19.
 */
public interface IGameView extends IPlayerCardsView, IDeckView {
    void moveToChat();
    void moveToPlayerView();
    void moveToDestCards();
    void moveToResults();
    void makeToast(String toastText);
}
