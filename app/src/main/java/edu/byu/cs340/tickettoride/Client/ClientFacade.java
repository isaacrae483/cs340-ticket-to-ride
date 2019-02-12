package edu.byu.cs340.tickettoride.Client;

import android.os.AsyncTask;

import java.lang.reflect.Method;
import java.net.URL;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameJoinError;
import edu.byu.cs340.tickettoride.Client.model.events.login.LoginFailed;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientFacade implements IClient, ICallBack {
    private static ClientFacade _instance;
    private ClientFacade(){

    }

    public static ClientFacade instance() {
        if (_instance == null){
            _instance = new ClientFacade();
        }
        return _instance;
    }

    private ClientModel model = ClientModel.instance();
    private Username _username;

    /**
     * Called asynchronously from the presenters to login
     *
     * @param username
     * @param password
     * @param host
     */
    public void login(Username username, Password password, URL host){
        ServerProxy.instance().setHost(host);
        _username = username;

        GenericData info = new GenericData("login",
                new Class<?>[] {Username.class, Password.class},
                new Object[] {username, password});

        GenericTask task = new GenericTask<LoginResult>(this);
        task.execute(info);

    }

    public void register(Username username, Password password, URL host){
        ServerProxy.instance().setHost(host);
        _username = username;

        GenericData info = new GenericData("register",
                new Class<?>[] {Username.class, Password.class},
                new Object[] {username, password});

        GenericTask task = new GenericTask<LoginResult>(this);
        task.execute(info);

    }
    public void joinGame(ID id){
        GenericData info = new GenericData("joinGame",
                new Class<?>[] {Username.class, ID.class},
                new Object[] {model.getUsername(), id});

        GenericTask task = new GenericTask<JoinGameResult>(this);
        task.execute(info);
    }

    public void createGame(){
        GenericData info = new GenericData("createGame",
                new Class<?>[] {Username.class},
                new Object[] {model.getUsername()});

        GenericTask task = new GenericTask<CreateGameResult>(this);
        task.execute(info);
    }

    @Override
    public void startGame(ID gameId) {
        GenericData info = new GenericData("startGame",
                new Class<?>[] {Username.class, ID.class},
                new Object[] {model.getUsername(), gameId});

        GenericTask task = new GenericTask<StartGameResult>(this);
        task.equals(info);
    }

    @Override
    public void incrementPlayers(ID id, Player player) {
        ClientModel.instance().incrementPlayers(id, player);
    }

    @Override
    public void addGame(Game game) {
        ClientModel.instance().addGame(game);
    }



    public <T> void update(T response){

        if(response != null && response.getClass() == LoginResult.class){
            LoginResult result = (LoginResult) response;
            if(result.getSuccess()){
                model.setGames(result.getGames());
                model.setUsername(_username);
                new Poller().startPolling(_username);
            }
            else{
                ClientModel.instance().passErrorEvent(new LoginFailed());
            }
        }
        else if(response != null && response.getClass() == CreateGameResult.class){
            CreateGameResult result = (CreateGameResult) response;
            if(result.getSuccess()){
                joinGame(result.getGame().getId());
                return;
                //model.addGame(result.getGame());
            }
            else{
                ClientModel.instance().passErrorEvent(new GameJoinError());
            }
        }
        else if(response != null && response.getClass() == JoinGameResult.class){
            JoinGameResult result = (JoinGameResult) response;
            if(result.getSuccess()){
                model.setActiveGameID(result.getId());
            }
            else{
                ClientModel.instance().passErrorEvent(new GameJoinError());
            }
        }
        else if(response != null && response.getClass() == StartGameResult.class){
            StartGameResult result = (StartGameResult) response;
            if(!result.getSuccess()) {
                //throw error
            }
        }
        else{
            //report error
        }

    }

    /**
     * Generic Async task
     */
    public class GenericTask<T> extends AsyncTask<Object, GenericData, T> {

        private ICallBack callBack;

        public GenericTask(ICallBack callBack){
            this.callBack = callBack;
        }

        ServerProxy server = ServerProxy.instance();
        private String _className;
        private String _methodName;
        private Class<?>[] _paramTypes;
        private Object[] _paramValues;

        @Override
        protected T doInBackground(Object...datas){
            GenericData data = (GenericData) datas[0];

            _className = data.get_className();
            _methodName = data.get_methodName();
            _paramTypes = data.get_paramTypes();
            _paramValues = data.get_paramValues();
            T result = null;

            try {
                Class<?> receiver = Class.forName(_className);
                Method method = receiver.getMethod(_methodName, _paramTypes);
                result = (T) method.invoke(server, _paramValues);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            return result;
        }

        @Override
       protected void onPostExecute(T result){
            callBack.update(result);
        }


    }

}
