package edu.byu.cs340.tickettoride.Client;

import android.os.AsyncTask;

import java.net.URL;
import java.util.Observable;

import edu.byu.cs340.tickettoride.server.Server;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientFacade implements IClient {
    private static ClientFacade _instance;
    private ClientFacade(){

    }

    public static ClientFacade instance() {
        if (_instance == null){
            _instance = new ClientFacade();
        }
        return _instance;
    }

    /**
     * Called asynchronously from the presenters to login
     *
     * @param username
     * @param password
     * @param host
     */
    public void login(Username username, Password password, URL host){
        ServerProxy.instance().setHost(host);
        User user = new User(username, password);
        LoginTask task = new LoginTask();
        task.execute(user);

    }

    public void register(Username username, Password password, URL host){
        ServerProxy.instance().setHost(host);
        User user = new User(username, password);
        RegisterTask task = new RegisterTask();
        task.execute(user);

    }

    public void joinGame(ID id){
        Username username = ClientModel.instance().getUsername();
        JoinGameResult result = ServerProxy.instance().joinGame(username, id);
        if(result.getSuccess()){
            incrementPlayers(id, result.getPlayer());
        }
        else{
            //calls the presenter to display a toast with the error
        }
    }

    public void createGame(){
        Username username = ClientModel.instance().getUsername();
        CreateGameResult result = ServerProxy.instance().createGame(username);
        if(result.getSuccess()){
            addGame(result.getGame());
        }
        else{
            //calls the presenter to display a toast with the error
        }
    }

    @Override
    public void incrementPlayers(ID id, Player player) {
        ClientModel.instance().incrementPlayers(id, player);
    }

    @Override
    public void addGame(Game game) {
        ClientModel.instance().addGame(game);
    }

    /**
     * Async task that attempts to login
     */
    public class LoginTask extends AsyncTask<User, Integer, LoginResult> {
        User user;
        Username username;
        Password password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected LoginResult doInBackground(User...users){
            user = users[0];
            username = user.getUserName();
            password = user.getPassword();

            LoginResult result = ServerProxy.instance().login(username, password);
            return result;
        }

        @Override
       protected void onPostExecute(LoginResult result){

            if(result.getSuccess()){
                // Can't manipulate model from network thread
                ClientModel.instance().setUsername(username);
                ClientModel.instance().setGames(result.getGames());
                // mLoginActivity.displayToast(mLoginActivity.getResources().getString(R.string.login_success));
                //stopObserving();
                //mLoginActivity.startActivity(GameListActivity.newIntent(mLoginActivity), new Bundle());
            }
            else{
                //calls the presenter to display a toast with the error
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
        }


    }

    public class RegisterTask extends AsyncTask<User, Integer, Boolean> {
        User user;
        Username username;
        Password password;
        LoginResult result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Boolean doInBackground(User...users){
            user = users[0];
            username = user.getUserName();
            password = user.getPassword();

            ServerProxy.instance().register(username, password);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success){
            super.onPostExecute(success);
            if (success) {
                if(result.getSuccess()){
                    // Can't manipulate model from network thread
                    ClientModel.instance().setUsername(username);
                    ClientModel.instance().setGames(result.getGames());
                    // mLoginActivity.displayToast(mLoginActivity.getResources().getString(R.string.login_success));
                    //stopObserving();
                    //mLoginActivity.startActivity(GameListActivity.newIntent(mLoginActivity), new Bundle());
                }
                else{
                    //calls the presenter to display a toast with the error
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
        }

    }


}
