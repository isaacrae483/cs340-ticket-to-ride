package edu.byu.cs340.tickettoride.Client.presenters;

import android.graphics.Paint;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.ClientModel;
import edu.byu.cs340.tickettoride.Client.views.GameListActivity;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class GameListPresenter implements IGameListPresenter, Observer {

    private GameListActivity mGameListActivity;
    private ClientFacade mClientFacade;
    private ClientModel mClientModel;

    public GameListPresenter(GameListActivity activity) {
        mGameListActivity = activity;
        mClientFacade = ClientFacade.instance();
        mClientModel = ClientModel.instance();

        // TESTING PURPOSES ONLY
        ArrayList<IGameListEntry> testList = new ArrayList<IGameListEntry>();
        try {
            Game game = new Game();
            game.addPlayer(new Username("testUser"));
            Game game2 = new Game();
            game2.addPlayer(new Username("testUser"));
            game2.addPlayer(new Username("testUser"));
            game2.addPlayer(new Username("testUser"));
            game2.addPlayer(new Username("testUser"));
            game2.addPlayer(new Username("testUser"));
            testList.add(game);
            testList.add(game2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        mGameListActivity.setGameList(testList);

    }

    public void joinGame(ID id){
        JoinGameTask task = new JoinGameTask();
        task.execute(id);
    }

    public void createGame(){
        CreateGameTask task = new CreateGameTask();
        task.execute(0);
    }

    public void update(){
        //changes to the GameList Fragment
    }

    @Override
    public void addGamePressed() {
        // FOR TESTING PURPOSES ONLY
        ArrayList<IGameListEntry> testList = new ArrayList<IGameListEntry>();
        try {
            Game game = new Game();
            game.addPlayer(new Username("testUser"));
            Game game2 = new Game();
            game2.addPlayer(new Username("testUser"));
            game2.addPlayer(new Username("testUser"));
            game2.addPlayer(new Username("testUser"));
            game2.addPlayer(new Username("testUser"));
            game2.addPlayer(new Username("testUser"));
            testList.add(game);
            testList.add(game2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        mGameListActivity.addGameToList(testList.get(0));
        mGameListActivity.addGameToList(testList.get(1));
        mGameListActivity.displayToast("Add Game Pressed");
    }

    @Override
    public void joinGamePressed(ID gameID) {
        mGameListActivity.displayToast(gameID.getId() + " pressed");
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
