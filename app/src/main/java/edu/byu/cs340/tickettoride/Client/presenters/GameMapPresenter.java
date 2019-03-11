package edu.byu.cs340.tickettoride.Client.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.events.map.RouteClaimed;
import edu.byu.cs340.tickettoride.Client.views.IMapView;
import edu.byu.cs340.tickettoride.shared.Game.Board.IRoute;
import edu.byu.cs340.tickettoride.shared.Game.Board.Routes;

/**
 * Created by Thomas Lewis on 3/9/19.
 */


public class GameMapPresenter extends Presenter implements IGameMapPresenter {
    final int NUM_ROUTES = 100;

    private IMapView mMapView;

    public GameMapPresenter(IMapView mapView) {
        this.mMapView = mapView;
    }

    @Override
    public void routePressed(Integer routeId) {

    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
        Routes clientRoutes = mClientModel.getRoutes();
        ArrayList<IRoute> routeArrayList = new ArrayList<>();
        for (int i = 0; i < NUM_ROUTES; i++) {
            routeArrayList.add(clientRoutes.getRoute(i));
        }
        mMapView.setRouteList(routeArrayList);
    }



    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);
        if (o instanceof RouteClaimed) {
            RouteClaimed e = (RouteClaimed) o;
            mMapView.claimRoute(e.getRoute());
        }
    }
}
