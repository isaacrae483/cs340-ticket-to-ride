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
        final ClientFacade clientFacade = ClientFacade.instance();
        final ClientModel model = ClientModel.instance();

        //the handler should run on the main thread since it is updating the UI
        Handler handler = new Handler(context.getMainLooper());

        //Isaac will create the first half here... right?
        updatePlayerPoints(handler);


        /* AVERY's section (the second half)*/
        //secondHalf(handler);

    }


    //first half of the demo, written by Isaac
    //Update player points
    private void updatePlayerPoints(final Handler handler) {
        parent.startActivity(new Intent(context, GameActivity.class));
        Toast.makeText(context, "UPDATING PLAYER POINTS CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                addTrainCards(handler);
            }
        });
    }

    //Add/remove train cards for this player
    private void addTrainCards(final Handler handler) {
        Toast.makeText(context, "ADDING TRAIN CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                removeTrainCards(handler);
            }
        });
    }

    //Add/remove train cards for this player
    private void removeTrainCards(final Handler handler) {
        Toast.makeText(context, "REMOVING TRAIN CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                addDestinationCards(handler);
            }
        });
    }

    //Add player destination cards for this player
    private void addDestinationCards(final Handler handler) {
        Toast.makeText(context, "ADDING DESTINATION CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                updateOpponentTrainCards(handler);
            }
        });
    }

    //Update the number of train cards for opponent players
    private void updateOpponentTrainCards(final Handler handler) {
        //NEED TO SWITCH VIEWS
        Toast.makeText(context, "UPDATING OPPONENT TRAIN CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                updateOpponentTrainCars(handler);
            }
        });
    }

    //Update the number of train cars for opponent players
    private void updateOpponentTrainCars(final Handler handler) {
        Toast.makeText(context, "UPDATING OPPONENT TRAIN CARS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                updateOpponentDestCards(handler);
            }
        });
    }

    //Update the number of destination cards for opponent players
    private void updateOpponentDestCards(final  Handler handler) {
        Toast.makeText(context, "UPDATING OPPONENT DESTINATION CARDS", Toast.LENGTH_LONG).show();
        run(handler, new Runnable() {
            @Override
            public void run() {
                secondHalf(handler);
            }
        });
    }

//second half of the demo, Avery is writing
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
