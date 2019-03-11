package edu.byu.cs340.tickettoride.Client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.views.ChatActivity;
import edu.byu.cs340.tickettoride.Client.views.DestCardActivity;
import edu.byu.cs340.tickettoride.Client.views.GameActivity;
import edu.byu.cs340.tickettoride.Client.views.PlayerListActivity;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Demo {

    private static final int DELAY_SPACING = 7000;
    private Context context;

    final ModelFacade modelFacade = ModelFacade.instance(); //I don't think we will need this
    final ClientFacade clientFacade = ClientFacade.instance();
    final ClientModel model = ClientModel.instance();

    private int delay = 0;


    public Demo(Context context) {
        this.context = context;
    }

    private void run(Handler handler, Runnable func) {
        final int delay = 7000;
        handler.postDelayed(func, delay);
    }

    private int getDelay() {
        int delay = this.delay;
        this.delay += DELAY_SPACING;
        return delay;
    }

    public void execute(){
        //the handler should run on the main thread since it is updating the UI
        Handler handler = new Handler(context.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updatePlayerPoints();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addTrainCards();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                removeTrainCards();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addDestinationCards();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateOpponentTrainCards();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateOpponentTrainCars();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateOpponentDestCards();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateFaceUp();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateFaceDown();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateDestCardDeck();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addClaimedRoute();
            }
        }, getDelay());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addChat();
            }
        }, getDelay());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                advanceTurn();
            }
        }, getDelay());

    }


    //first half of the demo, written by Isaac
    //Update player points
    private void updatePlayerPoints() {
        context.startActivity(new Intent(context, PlayerListActivity.class));
        Toast.makeText(context, "UPDATING PLAYER POINTS", Toast.LENGTH_LONG).show();
        model.updatePoints(15);
    }

    //Add/remove train cards for this player
    private void addTrainCards() {
        context.startActivity(new Intent(context, GameActivity.class));
        Toast.makeText(context, "ADDING TRAIN CARD", Toast.LENGTH_LONG).show();
        model.addTrainCard(new TrainCard(Colors.GREEN));
    }

    //Add/remove train cards for this player
    private void removeTrainCards() {
        Toast.makeText(context, "REMOVING TRAIN CARD", Toast.LENGTH_LONG).show();
        model.removeTrainCard(new TrainCard(Colors.GREEN));
    }

    //Add player destination cards for this player
    private void addDestinationCards() {
        model.drawDestCards(new DestCard(City.WASHINGTON, City.MIAMI, 15), null, null);
        context.startActivity(new Intent(context, DestCardActivity.class));
        Toast.makeText(context, "ADDING DESTINATION CARDS", Toast.LENGTH_LONG).show();
    }

    //Update the number of train cards for opponent players
    private void updateOpponentTrainCards() {
        context.startActivity(new Intent(context, PlayerListActivity.class));
        Toast.makeText(context, "UPDATING OPPONENT TRAIN CARDS", Toast.LENGTH_LONG).show();
        model.updateOppTrainCard(new TrainCard(Colors.GREEN));
    }

    //Update the number of train cars for opponent players
    private void updateOpponentTrainCars() {
        Toast.makeText(context, "UPDATING OPPONENT TRAIN CARS", Toast.LENGTH_LONG).show();
        model.updateOppTrainCars(5);
    }

    //Update the number of destination cards for opponent players
    private void updateOpponentDestCards() {
        Toast.makeText(context, "UPDATING OPPONENT DESTINATION CARDS", Toast.LENGTH_LONG).show();
        model.updateOppDestCard(new DestCard(City.WASHINGTON, City.MIAMI, 15));
    }

    //Update the visible (face up) cards in the train card deck
    private void updateFaceUp() {
        context.startActivity(new Intent(context, GameActivity.class));
        Toast.makeText(context, "UPDATING FACE UP CARDS", Toast.LENGTH_LONG).show();
        model.replaceFaceUpTrainCard(new TrainCard(Colors.RAINBOW), 0);
        model.replaceFaceUpTrainCard(new TrainCard(Colors.RAINBOW), 1);
        model.replaceFaceUpTrainCard(new TrainCard(Colors.RAINBOW), 2);
        model.replaceFaceUpTrainCard(new TrainCard(Colors.RAINBOW), 3);
        model.replaceFaceUpTrainCard(new TrainCard(Colors.RAINBOW), 4);
    }

    //Update the number of invisible (face down) cards in train card deck
    private void updateFaceDown() {
        Toast.makeText(context, "UPDATING FACE DOWN CARDS", Toast.LENGTH_LONG).show();
        model.modifyTrainCardDeckSize(0);
        model.modifyTrainCardDeckSize(0);
        model.replaceFaceUpTrainCard(new TrainCard(Colors.ORANGE), 0);
        model.replaceFaceUpTrainCard(new TrainCard(Colors.GREEN), 1);
    }

    //Update the number of cards in destination card deck
    private void updateDestCardDeck() {
        context.startActivity(new Intent(context, DestCardActivity.class));
        Toast.makeText(context, "UPDATING DEST CARDS", Toast.LENGTH_LONG).show();
        model.drewDestCards(8);
    }

    //Add claimed route (for any player). Show this on the map.
    private void addClaimedRoute() {
        context.startActivity(new Intent(context, GameActivity.class));
        Toast.makeText(context, "CLAIMING ROUTE", Toast.LENGTH_LONG).show();
        Route routeToClaim = model.getRoutes().getRoute(0);
        routeToClaim.claimRoute(ClientModel.instance().getActiveGame().getPlayers().get(0));
        model.claimRoute(routeToClaim);
    }

    //Add chat message from any player
    private void addChat() {
        context .startActivity(new Intent(context, ChatActivity.class));
        Toast.makeText(context, "ADDING CHAT", Toast.LENGTH_LONG).show();
        try {
            model.addChatMessage(
                    new ChatMessage("DIS BE A TEST MESSAGE",
                            new Username("JOE"),
                            model.getActiveGameID()));
        }
        catch (Username.InvalidUserNameException e){
            e.printStackTrace();
        }
    }

    //Advance player turn (change the turn indicator so it indicates another player)
    private void advanceTurn() {
        context.startActivity(new Intent(context, PlayerListActivity.class));
        model.updatePlayerTurn();
        Toast.makeText(context, "ADVANCING PLAYER TURN", Toast.LENGTH_LONG).show();
    }
}
