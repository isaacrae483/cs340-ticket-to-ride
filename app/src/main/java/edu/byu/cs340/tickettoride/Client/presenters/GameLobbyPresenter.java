package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.model.events.game.PlayerCountChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelobby.GameStarted;
import edu.byu.cs340.tickettoride.Client.model.events.gamelobby.StartGameError;
import edu.byu.cs340.tickettoride.Client.views.IGameLobbyView;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 2/6/19.
 */
public class GameLobbyPresenter extends Presenter implements IGameLobbyPresenter, Observer {

    private IGameLobbyView mGameLobbyView;
    private ModelFacade mModelFacade;

    public GameLobbyPresenter(IGameLobbyView view) {
        mGameLobbyView = view;
        mModelFacade = ModelFacade.instance();

//        // TODO: FOR TESTING PURPOSES ONLY
//        class Player implements IPlayer {
//            @Override
//            public Color getColor() {
//                return Color.BLACK;
//            }
//
//            @Override
//            public Username getPlayerName() {
//                Username user = null;
//                try {
//                    user = new Username("hi");
//                } catch (Throwable e) {
//
//                }
//                return user;
//            }
//        }
//        ArrayList<IPlayer> playerList = new ArrayList<IPlayer>();
//        playerList.add(new Player());
//        playerList.add(new Player());
//
//        mGameLobbyView.setPlayerSet(playerList);

        mGameLobbyView.displayStartGame(true);
    }

    private void playerCountChanged(Game game) {
        if (mClientModel.getActiveGame() == null)
            mGameLobbyView.finishView();

        mGameLobbyView.setPlayerSet(mClientModel.getActiveGame().getPlayers());
        Username thisUser = mClientModel.getUsername();
        if (mClientModel.getActiveGame().canGameBeStarted() &&
                game.GetLeader().getPlayerName().equals(thisUser)) {
            mGameLobbyView.displayStartGame(true);
        } else {
            mGameLobbyView.displayStartGame(false);
        }
    }

    @Override
    public void startGamePressed() {
        mModelFacade.sendStartGame(mClientModel.getActiveGameID());
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
        Game currentGame = mClientModel.getActiveGame();
        if (currentGame == null) {
            mGameLobbyView.finishView();
        }
        if (currentGame.isGameStarted()) {
            mGameLobbyView.moveToStartGame();
        } else {
            mGameLobbyView.setPlayerSet(mClientModel.getActiveGame().getPlayers());
            mGameLobbyView.displayStartGame(currentGame.GetLeader().getPlayerName().equals(mClientModel.getUsername())
            && currentGame.canGameBeStarted());
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof PlayerCountChanged) {
            PlayerCountChanged e = (PlayerCountChanged) o;
            if (!mClientModel.getActiveGameID().equals(e.getGame().getId()))
                return;
            playerCountChanged(e.getGame());
        } else if (o instanceof GameStarted) {
            GameStarted e = (GameStarted) o;
            Game game = e.getGame();
            if (game.getId().equals(mClientModel.getActiveGameID()))
                mGameLobbyView.moveToStartGame();
        } else if (o instanceof StartGameError) {

        }
    }
}
