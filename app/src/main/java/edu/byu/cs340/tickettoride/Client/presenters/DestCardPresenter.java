package edu.byu.cs340.tickettoride.Client.presenters;


import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.Poller;
import edu.byu.cs340.tickettoride.Client.ServerProxy;
import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.views.DestCardActivity;
import edu.byu.cs340.tickettoride.Client.views.IDestCardActivity;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.RegisterCommand;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestCardDraw;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestCardReturned;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestDeckSizeChanged;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestDrawFailed;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.ReturnDestCardFailed;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class DestCardPresenter extends Presenter implements IDestCardPresenter {

    private DestCardActivity view;
    private ClientModel model;
    private ModelFacade modelFacade;

    private IDestCardActivity.ReturnCardLimit limit = IDestCardActivity.ReturnCardLimit.One();

   // private boolean done = false;

    public DestCardPresenter(DestCardActivity view) {
        this.view = view;
        this.model = ClientModel.instance();
        this.modelFacade = ModelFacade.instance();
        //DEBUG SECTION
        //new DebugSetup().execute();
        //END DEBUG SECTION
    }

    @Override
    public void syncWithModel() {
//        if (!done) {
///           return;
// /      }
//        if (!mClientModel.drawnYet()) {
//            ModelFacade.instance().drawTickets();
//        }
        view.SetDeckSize(model.getDestCardDeckSize());
        view.setCards(model.getDestCards());
    }

    @Override
    public void drawPressed() {
        modelFacade.drawTickets();
    }

    @Override
    public void returnCard(DestCard card) {
        modelFacade.returnTicket(card);
    }


    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof DestCardDraw) {
            DestCardDraw draw = (DestCardDraw) o;
            model.getDestCards();
            view.onCardDraw(draw.getCard1(), draw.getCard2(), draw.getCard3());
            syncWithModel();
        }
        else if (o instanceof DestCardReturned) {
            DestCardReturned returned = (DestCardReturned) o;
            view.onCardReturn(returned.getReturned(), limit);
            syncWithModel();
            if (limit.value() == 1) {
                limit = IDestCardActivity.ReturnCardLimit.Two();
            }
        }
        else if (o instanceof DestDrawFailed) {
            view.makeToast("DRAWING A DEST CARD FAILED");
        }
        else if (o instanceof ReturnDestCardFailed) {
            view.makeToast("RETURNING A DEST CARD FAILED");
        }
        else if (o instanceof DestDeckSizeChanged) {
            view.SetDeckSize(model.getDestCardDeckSize());
        }
    }

    private class DebugSetup extends AsyncTask<Void, Void, Void> {

        private Username username;

        @Override
        protected void onPreExecute() {
            try {
                username = new Username("joe");
            }

            catch (Username.InvalidUserNameException e) {
                e.printStackTrace();
            }
            new Poller().startPolling(username);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            URL url = null;
            Password password = null;
            ServerProxy proxy = ServerProxy.instance();
            try {
                url =  new URL("http://10.0.2.2:8080");
                proxy.setHost(url);

                password = new Password("joe");
                LoginResult login = proxy.register(username, password);
                model.setUsername(username);

                CreateGameResult gameResult = proxy.createGame(username);
                ID game = gameResult.getGame().getId();
                while (model.getActiveGame() == null) {
                    model.setActiveGameID(game);
                }
                model.incrementPlayers(game, new Player(username, Player.Color.BLACK));

                Username user2 = new Username("joe2");
                proxy.register(user2, password);
                proxy.joinGame(user2, game);

                proxy.startGame(username, game);

                model.setUsername(user2);
            }
            catch (Password.InvalidPasswordException e) {
                e.printStackTrace();
            }
            catch (Username.InvalidUserNameException e) {
                e.printStackTrace();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
          //  DestCardPresenter.this.done = true;
            DestCardPresenter.this.syncWithModel();
        }
    }
}
