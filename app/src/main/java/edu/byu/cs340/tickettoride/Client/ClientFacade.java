package edu.byu.cs340.tickettoride.Client;

import android.os.AsyncTask;

import java.net.URL;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameJoinError;
import edu.byu.cs340.tickettoride.Client.model.events.login.LoginFailed;
import edu.byu.cs340.tickettoride.Client.model.events.login.RegisterFailed;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
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
        //ClientModel.instance().setUsername(username);

    }

    public void register(Username username, Password password, URL host){
        ServerProxy.instance().setHost(host);
        User user = new User(username, password);
        RegisterTask task = new RegisterTask();
        task.execute(user);
        //ClientModel.instance().passErrorEvent(new RegisterFailed());
    }

    public void joinGame(ID id){
        Username username = ClientModel.instance().getUsername();
        new JoinGameTask(username, id).execute();
    }

    public void createGame(){
        Username username = ClientModel.instance().getUsername();
        new CreateGameTask().execute(username);
    }

    @Override
    public void incrementPlayers(ID id, Player player) {
        ClientModel.instance().incrementPlayers(id, player);
    }

    @Override
    public void addGame(Game game) {
        if (ClientModel.instance().getGame(game.getId()) != null)
            return;
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
            if(result != null && result.getSuccess()){
                ClientModel.instance().setUsername(username);
                ClientModel.instance().setGames(result.getGames());
            }
            else{
                ClientModel.instance().passErrorEvent(new LoginFailed());
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
        }


    }

    public class RegisterTask extends AsyncTask<User, Integer, LoginResult> {
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

            return ServerProxy.instance().register(username, password);
        }

        @Override
        protected void onPostExecute(LoginResult result){
            super.onPostExecute(result);

            if(result != null && result.getSuccess()){
                ClientModel.instance().setUsername(username);
                ClientModel.instance().setGames(result.getGames());
            }
            else{
                ClientModel.instance().passErrorEvent(new RegisterFailed());
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

    }

    public class JoinGameTask extends AsyncTask<Void, Integer, JoinGameResult> {

        private Username mUser;
        private ID mGameId;

        public JoinGameTask(Username user, ID gameId) {
            mUser = user;
            mGameId = gameId;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JoinGameResult doInBackground(Void... voids) {
            return ServerProxy.instance().joinGame(mUser, mGameId);
        }

        @Override
        protected void onPostExecute(JoinGameResult result) {
            super.onPostExecute(result);
            if (result == null || !result.getSuccess())
                ClientModel.instance().passErrorEvent(new GameJoinError());
            //ClientModel.instance().incrementPlayers(mGameId, result.getPlayer());
            ClientModel.instance().setActiveGameID(mGameId);
        }
    }

    public class CreateGameTask extends AsyncTask<Username, Integer, CreateGameResult> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected CreateGameResult doInBackground(Username... usernames) {
            return ServerProxy.instance().createGame(usernames[0]);
        }

        @Override
        protected void onPostExecute(CreateGameResult result) {
            super.onPostExecute(result);
            if (result == null || !result.getSuccess())
                ClientModel.instance().passErrorEvent(new GameJoinError());
            ClientModel.instance().addGame(result.getGame());
            ClientModel.instance().setActiveGameID(result.getGame().getId());
        }
    }

    //public class StartGameTask extends AsyncTask<Username, Integer,>





}
