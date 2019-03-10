package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.presenters.GameMapPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.IGameMapPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Game.Board.IRoute;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public class MapFragment extends PresenterViewFragment implements IMapView{

    private View mFragmentView;
    private ImageView testView;
    private ViewGroup mMapLayout;
    private IGameMapPresenter mGameMapPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_game_map, container, false);
        mMapLayout = mFragmentView.findViewById(R.id.mapLayout);
        testView = mFragmentView.findViewById(R.id.imageView6);
        testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeToast("View pressed");
                //testView.setBackgroundColor();
                testView.setColorFilter(getResources().getColor(R.color.greenPlayer));
            }
        });

        mGameMapPresenter = new GameMapPresenter(this);
        setPresenter(mGameMapPresenter);

        // Set listeners for routes
        for (int i = 0; i < mMapLayout.getChildCount(); i++) {
            View view = mMapLayout.getChildAt(i);
            String viewTag = (String) view.getTag();
            if (viewTag == null || viewTag.equals(""))
                continue;
            try {
                Integer routeId = Integer.parseInt(viewTag);
                ImageView routeView = (ImageView) view;
                routeView.setOnClickListener(new RouteOnClickListener(routeId));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }


        return mFragmentView;
    }

    @Override
    public void setRouteList(List<IRoute> routeList) {
        for (IRoute route : routeList) {
            claimRoute(route);
        }
    }

    @Override
    public void claimRoute(IRoute route) {
        Integer routeId = route.getId();
        IPlayer.Color color = route.getClaimColor();
        ImageView routeView = mFragmentView.findViewWithTag(routeId.toString());
        //Log.d("whatever", routeId.toString());
        if (routeView == null)
            return;
        setColor(routeView, color);
    }

    private void setColor(ImageView view, IPlayer.Color playerColor) {
        view.setColorFilter(getColorFromEnum(playerColor));
    }

    public int getColorFromEnum(IPlayer.Color color) {
        Context applicationContext = getActivity().getApplicationContext();
        switch(color) {
            case GREEN:
                return ContextCompat.getColor(getActivity().getApplicationContext(), R.color.greenPlayer);
            case RED:
                return ContextCompat.getColor(applicationContext, R.color.redPlayer);
            case BLUE:
                return ContextCompat.getColor(applicationContext, R.color.bluePlayer);
            case BLACK:
                return ContextCompat.getColor(applicationContext, R.color.blackPlayer);
            case YELLOW:
                return ContextCompat.getColor(applicationContext, R.color.yellowPlayer);
        }
        return ContextCompat.getColor(applicationContext, R.color.neutral);
    }

    private class RouteOnClickListener implements View.OnClickListener {
        Integer routeId;
        RouteOnClickListener(Integer routeId) {
            this.routeId = routeId;
        }

        @Override
        public void onClick(View view) {
            mGameMapPresenter.routePressed(routeId);
            makeToast("Route presesd with ID: " + routeId);
            setColor((ImageView)view, IPlayer.Color.RED);
        }
    }
 }
