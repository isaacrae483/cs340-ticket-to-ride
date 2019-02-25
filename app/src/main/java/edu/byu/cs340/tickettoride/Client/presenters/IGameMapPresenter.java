package edu.byu.cs340.tickettoride.Client.presenters;

import edu.byu.cs340.tickettoride.shared.Game.IRoute;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public interface IGameMapPresenter {
    void routePressed(IRoute route);
}
