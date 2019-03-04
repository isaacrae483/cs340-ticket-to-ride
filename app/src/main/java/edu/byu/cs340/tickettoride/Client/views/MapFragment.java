package edu.byu.cs340.tickettoride.Client.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import edu.byu.cs340.tickettoride.R;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public class MapFragment extends PresenterViewFragment {

    private View mFragmentView;
    private ImageView testView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_game_map, container, false);
        testView = mFragmentView.findViewById(R.id.imageView6);
        testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeToast("View pressed");
                //testView.setBackgroundColor();
                testView.setColorFilter(getResources().getColor(R.color.greenPlayer));
            }
        });



        return mFragmentView;
        //return super.onCreateView(inflater, container, savedInstanceState);
        //return null;
    }


}
