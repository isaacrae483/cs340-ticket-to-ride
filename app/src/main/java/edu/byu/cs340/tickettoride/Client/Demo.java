package edu.byu.cs340.tickettoride.Client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.views.ChatActivity;
import edu.byu.cs340.tickettoride.Client.views.GameActivity;
import edu.byu.cs340.tickettoride.Client.views.PlayerListActivity;

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

    private Context context;
    private Activity parent;

    public Demo(Context context, Activity parent) {
        this.context = context;
        this.parent = parent;
    }

    private void run(Handler handler, Runnable func) {
        final int delay = 3000;
        handler.postDelayed(func, delay);
    }

    public void execute(){
        final ModelFacade modelFacade = ModelFacade.instance();
        final ClientModel model = ClientModel.instance();

        //the handler should run on the main thread since it is updating the UI
        Handler handler = new Handler(context.getMainLooper());

        //Isaac will create the first half here... right?



        /* AVERY's section (the second half)*/
        secondHalf(handler);

    }



    private void secondHalf(final Handler handler) {
        parent.startActivity(new Intent(context, GameActivity.class));
        run(handler, new Runnable() {
            @Override
            public void run() {
                updateFaceUp(handler);
            }
        });
    }

    //Update the visible (face up) cards in the train card deck
    private void updateFaceUp(final Handler handler) {
        Toast.makeText(context, "UPDATING FACE UP CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                updateFaceDown(handler);
            }
        });
    }

    //Update the number of invisible (face down) cards in train card deck
    private void updateFaceDown(final Handler handler) {
        Toast.makeText(context, "UPDATING FACE DOWN CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                updateDestCardDeck(handler);
            }
        });
    }

    //Update the number of cards in destination card deck
    private void updateDestCardDeck(final Handler handler) {
        Toast.makeText(context, "UPDATING DEST CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                addClaimedRoute(handler);
            }
        });
    }

    //Add claimed route (for any player). Show this on the map.
    private void addClaimedRoute(final Handler handler) {
        Toast.makeText(context, "CLAIMING ROUTE", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                addChat(handler);
            }
        });
    }

    //Add chat message from any player
    private void addChat(final Handler handler) {
        parent.startActivity(new Intent(context, ChatActivity.class));
        Toast.makeText(context, "ADDING CHAT", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                advanceTurn(handler);
            }
        });
    }

    //Advance player turn (change the turn indicator so it indicates another player)
    private void advanceTurn(final Handler handler) {
        parent.startActivity(new Intent(context, PlayerListActivity.class));
        Toast.makeText(context, "ADVANCING PLAYER TURN", Toast.LENGTH_LONG).show();
    }
}
