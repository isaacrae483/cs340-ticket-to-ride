package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.presenters.GameMapPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.IGameMapPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Game.Board.IRoute;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;

/**
 * Created by Thomas Lewis on 2/24/19.
 *
 * // Constant dimensions of the Map Layout
 * 573 dp
 * 342 dp
 */


/**
 * A fragment that displays a ticket to ride map with views that represent routes rendered on the map. Those views can be clicked and the route that was clicked can be
 * determined. The views that represent the routes can also change color based on who owns them.
 *
 */
public class MapFragment extends PresenterViewFragment implements IMapView{

    /**
     * The view for the fragment
     */
    private View mFragmentView;
    /**
     * The layout for the fragment that displays the map
     */
    private ViewGroup mMapLayout;
    /**
     * Created on a successful creation of the fragment. Will never be null while the fragment exists.
     */
    private IGameMapPresenter mGameMapPresenter;


    /** Called when the fragment is created. Initializes the class members, assigns a new presenter to the view, and hooks up listeners on views that need them
     *
     * pre: - Android layout file R.layout.fragment_game_map exists
     *      - Valid inflater given
     *      - ViewGroup R.id.mapLayout exists
     * post:
     *      - Each view that is direct child of the ViewGroup R.id.mapLayout with a tag that can be parsed as an int will have a RouteOnClickListener set as its
     *      onClickListener with that parsed int passed in as the routeId
     *
     * @param inflater The LayoutInflater that will be used to define the format for the views to be inflated from the resource file
     * @param container The ViewGroup that the fragment will be inflated in
     * @param savedInstanceState The saved state of the fragment from a previous instance of it being drawn
     * @return The view for the fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_game_map, container, false);
        mMapLayout = mFragmentView.findViewById(R.id.mapLayout);

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

    /**
     * Sets the color of each route view to reflect the routes given as a parameter
     *
     * pre: none
     * post: Each view associated with a route will be updated to the color of the passed in route(s) with the same route ID as the view.
     * @param routeList A list of valid routes with a size between 0-100. Each route in the list should have a route ID between 0-100.
     */
    @Override
    public void setRouteList(List<IRoute> routeList) {
        for (IRoute route : routeList) {
            claimRoute(route);
        }
    }

//
//            assert(routeList.size() < 100);
//        assert(listHasValidRoutes(routeList));

//    private boolean listHasValidRoutes(List<IRoute> routeList) {
//        for (IRoute route : routeList) {
//            if (!(route.getId() < 100 && route.getId() >= 0))
//                return false;
//        }/
//        return true;
//    }


    /**
     * Sets a view on the map with a tag that when parsed as an int matches the route id of the passed in IRoute as the same color as that IRoute.
     *
     * pre: route is a valid ticket to ride route
     * post: A view on the screen whose associated tag when parsed as a string matches the routes routeId will change color to the color of the given IRoute
     *
     * @param route A valid ticket to ride route
     */
    @Override
    public void claimRoute(IRoute route) {
        Integer routeId = route.getId();
        IPlayer.Color color = route.getClaimColor();
        ImageView routeView = mFragmentView.findViewWithTag(routeId.toString());
        if (routeView == null)
            return;
        setColor(routeView, color);
    }

    /**
     * Sets the color filter of the given ImageView to the Player color given
     *
     * pre: View and Color are not null
     * post: The passed in View's color filter will be set to the player color given.
     * @param view An ImageView
     * @param playerColor An enum that is one of the IPlayer.Color enums
     */
    private void setColor(ImageView view, IPlayer.Color playerColor) {
        view.setColorFilter(getColorFromEnum(playerColor));
    }

    /**
     * Returns an android color that matches the Player Color enum given
     *
     * pre: - color is a valid IPlayer.Color enum
     *      - getActivity().getApplicationContext() returns the Application Context
     * post: An associated android color resource id is returned, based on the given IPlayer.Color enum
     *
     * @param color A valid enum as defined in IPlayer.Color
     * @return A resource ID for an android color that matches the given IPlayer.Color enum
     */
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

    /**
     *
     * The OnClickListener that each view with an integer set as its tag will receive
     *
     */
    private class RouteOnClickListener implements View.OnClickListener {
        // Never null, assigned in the constructor of the listener
        Integer routeId;

        /**Creates the RouteOnClickListener and assigning it an associated integer as the class member routeId
         * pre:
         * post: New RouteOnClickListener created with class member routeId assigned to the passed in Integer routeId
         * @param routeId A valid integer
         */
        RouteOnClickListener(Integer routeId) {
            this.routeId = routeId;
        }

        /**
         * Communicates to the presenter that a route has been pressed with the id that matches class member routeId
         *
         * pre: IPlayer.Color.RED exists, mGameMapPresenter is non-null
         * post: Outer class member mGameMapPresenter has its routePressed(Integer routeId) function called with the routeId associated with the listener
         *
         * @param view The view that has been pressed
         */
        @Override
        public void onClick(View view) {
            mGameMapPresenter.routePressed(routeId);
            makeToast("Route pressed with ID: " + routeId);
            setColor((ImageView)view, IPlayer.Color.RED);
        }
    }

    /**
     * Displays a claim success toast
     * pre:
     * post: Toast displayed to screen with a claim success message
     */
    @Override
    public void displayClaimSuccess() {
        makeToast(getResources().getString(R.string.claim_success));
    }

    /**
     * Displays a failed to claim toast
     * pre:
     * post: Toast displayed to screen with a failed to claim toast
     */
    @Override
    public void displayClaimFail() {
        makeToast(getResources().getString(R.string.failed_to_claim_route));
    }

    /**
     * Displays a not enough cards toast
     * pre:
     * post: Toast displayed to screen with a not enough cards message
     */
    @Override
    public void displayNotEnoughCards() {
        makeToast(getResources().getString(R.string.not_enough_cards_to_claim));
    }

    /**
     * Displays an owned by other player toast
     * pre:
     * post: Toast displayed to screen with a route owned by other player message
     */
    @Override
    public void displayOwnedByOtherPlayer() {
        makeToast(getResources().getString(R.string.route_owned_by_another_player));
    }
}
