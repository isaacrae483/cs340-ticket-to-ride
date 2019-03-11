package edu.byu.cs340.tickettoride.Client.presenters;

import edu.byu.cs340.tickettoride.shared.Game.Board.IRoute;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public interface IGameMapPresenter extends IPresenter {
    void routePressed(Integer routeId);
}
