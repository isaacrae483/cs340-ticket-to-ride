package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;

/**
 * Created by Thomas Lewis on 2/5/19.
 */
abstract public class Presenter implements Observer, IPresenter {

    ClientModel mClientModel;

    Presenter() {
        mClientModel = ClientModel.instance();
    }

    /**
     * Grabs all necessary information from the data model to display the view accurately, then updates the view accordingly
     */
    public void syncWithModel() {

    }

    /**
     * When the view is destroyed, remove all external references to the presenter so it will be GCed
     * And so a NullPointerException is not thrown if the presenter tries to modify the view
     */
    @Override
    public void viewDestroyed() {
        stopObserving();
    }

    /**
     * When the view is back in the foreground, sync with the model then resume observing
     */

    @Override
    public void viewResumed() {
        syncWithModel();
        mClientModel.addObserver(this);
    }

    /**
     * When the view is paused, stop observing to prevent unnecessary updates
     */
    @Override
    public void viewPaused() {
        stopObserving();
    }

    /**Called by the observable when it has been mutated to notify observers to update
     *
     * @param observable the client model
     * @param o passed object
     */
    @Override
    public void update(Observable observable, Object o) {

    }

    /**
     * Removes this presenter as an observer on the client model
     */
    private void stopObserving() {
        mClientModel.deleteObserver(this);
    }

}
