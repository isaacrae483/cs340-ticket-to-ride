package edu.byu.cs340.tickettoride.Client;

import android.content.Context;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;

public class Demo {
    /*
    Update player points
    Add/remove train cards for this player
    Add/remove player destination cards for this player
    Update the number of train cards for opponent players
    Update the number of train cars for opponent players
    Update the number of destination cards for opponent players
    Update the visible (face up) cards in the train card deck
    Update the number of invisible (face down) cards in train card deck
    Update the number of cards in destination card deck
    Add claimed route (for any player). Show this on the map.
    Add chat message from any player
    Advance player turn (change the turn indicator so it indicates another player)
     */

    private  Context context;
    public Demo(Context context) {
        this.context = context;
    }

    public void execute(){
        final ClientModel model = ClientModel.instance();

        Handler handler = new Handler(context.getMainLooper());
        //Isaac will create the first half... right?

        /* AVERY's section (the second half)*/
        //Update the visible (face up) cards in the train card deck
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3000);
        //Update the number of invisible (face down) cards in train card deck
        //Update the number of cards in destination card deck
        //Add claimed route (for any player). Show this on the map.
        //Add chat message from any player
        //Advance player turn (change the turn indicator so it indicates another player)

    }

}
