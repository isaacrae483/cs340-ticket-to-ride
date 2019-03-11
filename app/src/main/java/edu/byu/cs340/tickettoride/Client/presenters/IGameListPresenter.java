package edu.byu.cs340.tickettoride.Client.presenters;

import edu.byu.cs340.tickettoride.shared.Game.ID;

/**
 * Created by Thomas Lewis on 2/3/19.
 */
public interface IGameListPresenter extends IPresenter {
    void addGamePressed();
    void joinGamePressed(ID gameID);
}
