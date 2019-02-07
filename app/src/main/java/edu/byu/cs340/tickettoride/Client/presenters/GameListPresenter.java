package edu.byu.cs340.tickettoride.Client.presenters;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.ClientModel;
import edu.byu.cs340.tickettoride.Client.views.GameListActivity;
import edu.byu.cs340.tickettoride.Client.views.IGameListView;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer.Color;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class GameListPresenter extends Presenter implements IGameListPresenter, Observer {

    private IGameListView mGameListView;
    private ClientFacade mClientFacade;

    public GameListPresenter(GameListActivity activity) {
        mGameListView = activity;
        mClientFacade = ClientFacade.instance();

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

    public void joinGame(ID id){
        JoinGameTask task = new JoinGameTask();
        task.execute(id);
    }

    public void createGame(){
        CreateGameTask task = new CreateGameTask();
        task.execute(0);
    }

    @Override
    public void addGamePressed() {
        // FOR TESTING PURPOSES ONLY
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
        mGameListView.addGameToList(testList.get(0));
        mGameListView.addGameToList(testList.get(1));
        mGameListView.displayToast("Add Game Pressed");
    }

    @Override
    public void joinGamePressed(ID gameID) {

        //TODO: TEMPORARY FOR TESTING
        mGameListView.moveToGameLobby();
        mGameListView.displayToast(gameID.getId() + " pressed");
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
    }

    @Override
    public void update(Observable observable, Object o) {

    }


    public class JoinGameTask extends AsyncTask<ID, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(ID...ids){

            ClientFacade.instance().joinGame(ids[0]);
            return true;
        }
    }

    public class CreateGameTask extends AsyncTask<Integer, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Integer...something){
            ClientFacade.instance().createGame();
            return true;
        }
    }
}
