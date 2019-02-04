package edu.byu.cs340.tickettoride.Client.presenters;

import android.graphics.Paint;
import android.os.AsyncTask;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.ID;

public class GameListPresenter {
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

    public void displayToast(String string){
        //displays toast usually an error that couldn't log in or register
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
