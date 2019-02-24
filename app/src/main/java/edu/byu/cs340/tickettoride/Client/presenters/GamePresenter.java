package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.views.IGameView;

/**
 * Created by Thomas Lewis on 2/6/19.
 */
public class GamePresenter extends Presenter implements IGamePresenter {

    IGameView mGameView;
    private ClientFacade mClientFacade;

    GamePresenter(IGameView view) {
        super();
        mGameView = view;
        mClientFacade = ClientFacade.instance();
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);
    }

    @Override
    public void viewChatPressed() {
        mGameView.moveToChat();
    }

    @Override
    public void viewPlayersPressed() {
        mGameView.moveToPlayerView();
    }

    @Override
    public void viewDestCardsPressed() {
        mGameView.moveToDestCards();
    }

    @Override
    public void viewDeckPressed() {
        mGameView.viewDeck();
    }
}
