package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import edu.byu.cs340.tickettoride.R;

/**
 * Created by Thomas Lewis on 2/6/19.
 */
public class GameActivity extends AppCompatActivity implements IGameView {


    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, GameActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
