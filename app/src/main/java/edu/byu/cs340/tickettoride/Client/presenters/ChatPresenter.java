package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.views.IChatView;

public class ChatPresenter extends Presenter implements IChatPresenter {
    private IChatView view;
    public ChatPresenter(IChatView view) {
        this.view = view;
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
    public void sendPressed() {

    }

    @Override
    public void messageTextChanged(String toString) {

    }
}
