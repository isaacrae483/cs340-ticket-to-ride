package edu.byu.cs340.tickettoride.Client.presenters;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.views.IPlayerListView;

public class PlayerListPresenter extends Presenter implements IPlayerListPresenter {
    private IPlayerListView mPlayerListView;
    private ClientModel model = ClientModel.instance();

    public PlayerListPresenter(IPlayerListView view) {
        super();
        this.mPlayerListView = mPlayerListView;
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
        mPlayerListView.updateData();
    }
}
