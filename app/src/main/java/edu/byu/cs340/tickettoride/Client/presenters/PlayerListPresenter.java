package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.views.IPlayerListView;
import edu.byu.cs340.tickettoride.shared.Game.Game;

public class PlayerListPresenter extends Presenter implements IPlayerListPresenter, Observer {
    private IPlayerListView mPlayerListView;
    private Game mGame = ClientModel.instance().getActiveGame();

    public PlayerListPresenter(IPlayerListView view) {
        super();
        this.mPlayerListView = view;
        mPlayerListView.updateData(mGame.getPlayers(), ClientModel.instance().getPlayerTurnIndex());
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
        mPlayerListView.updateData(mGame.getPlayers(), ClientModel.instance().getPlayerTurnIndex());
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);
        syncWithModel();
    }
}
