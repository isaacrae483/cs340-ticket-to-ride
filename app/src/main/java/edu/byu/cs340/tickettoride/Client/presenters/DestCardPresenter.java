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
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestCardFinishEvent;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestCardFinishFailEvent;
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

    private IDestCardActivity view;
    private ClientModel model;
    private ModelFacade modelFacade;

    private IDestCardActivity.ReturnCardLimit limit = IDestCardActivity.ReturnCardLimit.One();

    public DestCardPresenter(DestCardActivity view) {
        this.view = view;
        this.model = ClientModel.instance();
        this.modelFacade = ModelFacade.instance();
    }

    @Override
    public void syncWithModel() {
        view.SetDeckSize(model.getDestCardDeckSize());
        view.setCards(model.getDestCards());
        if (!model.doneReturningCards()) {
            view.onCardDraw(model.getLastDraw1(), model.getLastDraw2(), model.getLastDraw3());
        }
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
    public void finishDrawing() {
        modelFacade.finishDrawingDestCards();
        model.finishDrawingDestCards();
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
        else if (o instanceof DestCardFinishEvent) {
            view.FinishedDrawing();
            syncWithModel();
        }
        else if (o instanceof DestCardFinishFailEvent) {
            view.makeToast("COULD NOT FINISH DRAWING DEST CARDS");
        }
    }

}
