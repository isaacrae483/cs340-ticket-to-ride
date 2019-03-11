package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.model.events.bank.BankCardsChanged;
import edu.byu.cs340.tickettoride.Client.model.events.hand.HandChanged;
import edu.byu.cs340.tickettoride.Client.model.events.traincarddeck.TCDeckSizeChanged;
import edu.byu.cs340.tickettoride.Client.views.IGameView;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

/**
 * Created by Thomas Lewis on 2/6/19.
 */
public class GamePresenter extends Presenter implements IGamePresenter, IDeckPresenter, IPlayerCardsPresenter {

    IGameView mGameView;
    private ClientFacade mClientFacade;

    public GamePresenter(IGameView view) {
        super();
        mGameView = view;
        mClientFacade = ClientFacade.instance();
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
        //mClientModel.getPlayerTrainCards().add(new TrainCard(Colors.RAINBOW));
        mGameView.setPlayerCards(mClientModel.getPlayerTrainCards());
        mGameView.setCardsAvailable(mClientModel.getCardsInBank());
        int deckSize = mClientModel.getTrainCardDeckSize();
        mGameView.setDeckVisible(deckSize != 0);
        mGameView.setDeckSize(deckSize);
//        mClientModel.modifyTrainCardDeckSize(1);
//        mClientModel.addTrainCard(new TrainCard(Colors.ORANGE));
        //mGameView.setDeckVisible();
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);
        if (o instanceof HandChanged) {
            mGameView.setPlayerCards(mClientModel.getPlayerTrainCards());
        }
        else if (o instanceof BankCardsChanged) {
            mGameView.setCardsAvailable(mClientModel.getCardsInBank());
        }
        else if (o instanceof TCDeckSizeChanged) {
            mGameView.setDeckSize(mClientModel.getTrainCardDeckSize());
        }
    }

    @Override
    public void viewChatPressed() {
//        mClientModel.modifyTrainCardDeckSize(1);
//        mClientModel.addTrainCard(new TrainCard(Colors.ORANGE));
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
    public void cardPressed(TrainCard card) {

    }

    @Override
    public void deckPressed() {

    }
}
