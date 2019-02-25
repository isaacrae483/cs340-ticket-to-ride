package edu.byu.cs340.tickettoride.Client.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.byu.cs340.tickettoride.R;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public class MapFragment extends PresenterViewFragment {

    private View mFragmentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_game_map, container, false);

        return mFragmentView;
        //return super.onCreateView(inflater, container, savedInstanceState);
        //return null;
    }


}
