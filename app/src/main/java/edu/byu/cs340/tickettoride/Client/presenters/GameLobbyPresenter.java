package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.ClientModel;
import edu.byu.cs340.tickettoride.Client.views.IGameLobbyView;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 2/6/19.
 */
public class GameLobbyPresenter extends Presenter implements IGameLobbyPresenter, Observer {

    private IGameLobbyView mGameLobbyView;
    private ClientFacade mClientFacade;

    public GameLobbyPresenter(IGameLobbyView view) {
        mGameLobbyView = view;
        mClientFacade = ClientFacade.instance();

        // TODO: FOR TESTING PURPOSES ONLY
        class Player implements IPlayer {
            @Override
            public Color getColor() {
                return Color.BLACK;
            }

            @Override
            public Username getPlayerName() {
                Username user = null;
                try {
                    user = new Username("hi");
                } catch (Throwable e) {

                }
                return user;
            }
        }
        ArrayList<IPlayer> playerList = new ArrayList<IPlayer>();
        playerList.add(new Player());
        playerList.add(new Player());

        mGameLobbyView.setPlayerSet(playerList);

        mGameLobbyView.displayStartGame(true);
    }

    @Override
    public void startGamePressed() {
        mGameLobbyView.moveToStartGame();
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
