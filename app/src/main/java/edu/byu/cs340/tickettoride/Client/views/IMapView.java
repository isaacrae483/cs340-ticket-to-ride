package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Board.IRoute;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public interface IMapView {
    void setRouteList(List<IRoute> routeList);
    void claimRoute(IRoute route);
}
