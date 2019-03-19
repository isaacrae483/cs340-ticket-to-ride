package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.views.IResultsView;

public class ResultsPresenter extends Presenter implements IResultsPresenter, Observer {

    private IResultsView mResultsView;
    private ModelFacade mModelFacade;

    public ResultsPresenter (IResultsView view){
        super();
        mResultsView = view;
        mModelFacade = ModelFacade.instance();
    }
}
