package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.List;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.views.IResultsView;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class ResultsPresenter extends Presenter implements IResultsPresenter, Observer {

    private IResultsView mResultsView;
    private ModelFacade mModelFacade;

    public ResultsPresenter (IResultsView view){
        super();
        mResultsView = view;
        mModelFacade = ModelFacade.instance();
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
        List<Player> players = mModelFacade.getPlayers();
        int count = 1;
        for(Player player: players){
            switch(count){
                case 1:
                    mResultsView.uploadPlayer1Points(player);
                    count++;
                    break;
                case 2:
                    mResultsView.uploadPlayer2Points(player);
                    count++;
                    break;
                case 3:
                    mResultsView.uploadPlayer3Points(player);
                    count++;
                    break;
                case 4:
                    mResultsView.uploadPlayer4Points(player);
                    count++;
                    break;
                case 5:
                    mResultsView.uploadPlayer5Points(player);
                    count++;
                    break;
                default:
                    break;
            }
        }
        mResultsView.setWinner(mModelFacade.getWinningPlayer());
    }

}
