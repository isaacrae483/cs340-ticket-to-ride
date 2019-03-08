package edu.byu.cs340.tickettoride.Client.presenters;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.model.events.game.PlayerCountChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.ActiveGameChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameAdded;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameJoinError;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameListChanged;
import edu.byu.cs340.tickettoride.Client.views.GameListActivity;
import edu.byu.cs340.tickettoride.Client.views.IGameListView;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer.Color;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class GameListPresenter extends Presenter implements IGameListPresenter, Observer {

    private IGameListView mGameListView;
    private ModelFacade mModelFacade;
    private boolean mWaitingToJoinGame = false;

    public GameListPresenter(GameListActivity activity) {
        mGameListView = activity;
        mModelFacade = ModelFacade.instance();

        // TESTING PURPOSES ONLY
        ArrayList<IGameListEntry> testList = new ArrayList<IGameListEntry>();
        try {
            Game game = new Game();
            game.addPlayer(new Player(new Username("testUser"), Color.BLACK));
            Game game2 = new Game();
            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
            testList.add(game);
            testList.add(game2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        mGameListView.setGameList(testList);

    }

//    public void createGame(){
//        CreateGameTask task = new CreateGameTask();
//        task.execute(0);
//    }

    @Override
    public void addGamePressed() {
        if (mWaitingToJoinGame)
            return;
        // FOR TESTING PURPOSES ONLY
//        ArrayList<IGameListEntry> testList = new ArrayList<IGameListEntry>();
//        try {
//            Game game = new Game();
//            game.addPlayer(new Player(new Username("testUser"), Color.BLACK));
//            Game game2 = new Game();
//            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
//            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
//            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
//            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
//            game2.addPlayer(new Player(new Username("testUser"), Color.BLACK));
//            testList.add(game);
//            testList.add(game2);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        mGameListView.addGameToList(testList.get(0));
//        mGameListView.addGameToList(testList.get(1));
//        mGameListView.makeToast("Add Game Pressed");
        mWaitingToJoinGame = true;
        mModelFacade.createGame();
    }

    @Override
    public void joinGamePressed(ID gameID) {
        if (mWaitingToJoinGame)
            return;

        mWaitingToJoinGame = true;
        mModelFacade.joinGame(gameID);
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
        if (mClientModel.getGames() != null) {
            List<IGameListEntry> gameList = mClientModel.getGames().getAsList();
            mGameListView.setGameList(gameList);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof GameAdded) {
            GameAdded e = (GameAdded) o;
            mGameListView.addGameToList(e.getGame());
        } else if (o instanceof GameListChanged) {
            syncWithModel();
        } else if (o instanceof PlayerCountChanged) {
            PlayerCountChanged e = (PlayerCountChanged) o;
            mGameListView.updateGame(e.getGame());
        } else if (o instanceof GameJoinError) {
            mWaitingToJoinGame = false;
            mGameListView.displayGameJoinError();
        } else if (o instanceof ActiveGameChanged) {
            mWaitingToJoinGame = false;
            mGameListView.moveToGameLobby();
        }
    }
}
