package edu.byu.cs340.tickettoride.Client.model;

import android.os.AsyncTask;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Iterator;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.GenericData;
import edu.byu.cs340.tickettoride.Client.ICallBack;
import edu.byu.cs340.tickettoride.Client.Poller;
import edu.byu.cs340.tickettoride.Client.ServerProxy;
import edu.byu.cs340.tickettoride.Client.model.events.chat.ChatSendFailed;
import edu.byu.cs340.tickettoride.Client.model.events.destCard.DestDrawFailed;
import edu.byu.cs340.tickettoride.Client.model.events.destCard.ReturnDestCardFailed;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameJoinError;
import edu.byu.cs340.tickettoride.Client.model.events.gamelobby.StartGameError;
import edu.byu.cs340.tickettoride.Client.model.events.login.LoginFailed;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.ChatResult;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnTicketResult;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ModelFacade implements IModelFacade, ICallBack {
    private static ModelFacade _instance;
    private ModelFacade(){

    }

    public static ModelFacade instance() {
        if (_instance == null){
            _instance = new ModelFacade();
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
    @Override
    public void login(Username username, Password password, URL host){
        ServerProxy.instance().setHost(host);
        _username = username;

        GenericData info = new GenericData("login",
                new Class<?>[] {Username.class, Password.class},
                new Object[] {username, password});

        GenericTask task = new GenericTask<LoginResult>(this);
        task.execute(info);

    }

    @Override
    public void register(Username username, Password password, URL host){
        ServerProxy.instance().setHost(host);
        _username = username;

        GenericData info = new GenericData("register",
                new Class<?>[] {Username.class, Password.class},
                new Object[] {username, password});

        GenericTask task = new GenericTask<LoginResult>(this);
        task.execute(info);

    }

    @Override
    public void joinGame(ID id){

        if (model.getGame(id).contains(model.getUsername())) {
            model.setActiveGameID(id);
        }
        else {
            GenericData info = new GenericData("joinGame",
                    new Class<?>[]{Username.class, ID.class},
                    new Object[]{model.getUsername(), id});

            GenericTask task = new GenericTask<JoinGameResult>(this);
            task.execute(info);
        }
    }

    @Override
    public void createGame(){
        GenericData info = new GenericData("createGame",
                new Class<?>[] {Username.class},
                new Object[] {model.getUsername()});

        GenericTask task = new GenericTask<CreateGameResult>(this);
        task.execute(info);
    }

    @Override
    public void sendStartGame(ID gameId) {
        GenericData info = new GenericData("startGame",
                new Class<?>[] {Username.class, ID.class},
                new Object[] {model.getUsername(), gameId});

        GenericTask task = new GenericTask<StartGameResult>(this);
        task.execute(info);
    }

    @Override
    public void sendChat(ChatMessage message){
        //model.addChatMessage(message);
        GenericData info = new GenericData("chat",
                new Class<?>[] {ChatMessage.class},
                new Object[] {message});

        GenericTask task = new GenericTask<ChatResult>(this);
        task.execute(info);

    }

    @Override
    public void drawTickets(){
        GenericData info = new GenericData("drawTickets",
                new Class<?>[] {Username.class, ID.class},
                new Object[] {model.getUsername(), model.getActiveGameID()});

        GenericTask task = new GenericTask<ChatResult>(this);
        task.execute(info);
    }

    @Override
    public void returnTicket(DestCard card){
        GenericData info = new GenericData("returnTickets",
                new Class<?>[] {Username.class, DestCard.class, ID.class},
                new Object[] {model.getUsername(), card, model.getActiveGameID()});

        GenericTask task = new GenericTask<ChatResult>(this);
        task.execute(info);
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
                model.passErrorEvent(new LoginFailed());
            }
        }
        else if(response != null && response.getClass() == CreateGameResult.class){
            CreateGameResult result = (CreateGameResult) response;
            if(result.getSuccess()){
                return;
                //model.addGame(result.getGame());
            }
            else{
                model.passErrorEvent(new GameJoinError());
            }
        }
        else if(response != null && response.getClass() == JoinGameResult.class){
            JoinGameResult result = (JoinGameResult) response;
            if(result.getSuccess()){
                model.setActiveGameID(result.getId());
            }
            else{
                model.passErrorEvent(new GameJoinError());
            }
        }
        else if(response != null && response.getClass() == StartGameResult.class){
            StartGameResult result = (StartGameResult) response;
            if(!result.getSuccess()) {
                model.passErrorEvent(new StartGameError());
            }
        }
        else if(response != null && response.getClass() == ChatResult.class){
            ChatResult result = (ChatResult) response;
            if(!result.getSuccess()) {
                //sends an error if unsuccessful
                model.passErrorEvent(new ChatSendFailed());
            }
        }
        else if(response != null && response.getClass() == ReturnTicketResult.class){
            //execution on return ticket result
            ReturnTicketResult result = (ReturnTicketResult) response;
            if(!result.getSuccess()) {
                //sends an error if unsuccessful
                model.passErrorEvent(new ReturnDestCardFailed());
            }
            else {
                model.returnDestCard(result.getCard());
            }
        }
        else if(response != null && response.getClass() == DrawTicketsResult.class){
            DrawTicketsResult result = (DrawTicketsResult) response;
            if(!result.getSuccess()){
                model.passErrorEvent(new DestDrawFailed());
            }
            else {
                DestCard card1 = null, card2 = null, card3 = null;
                Iterator<DestCard> iter = result.getCards().iterator();
                if (iter.hasNext()) {
                    card1 = iter.next();
                }
                if (iter.hasNext()) {
                    card2 = iter.next();
                }
                if (iter.hasNext()) {
                    card3 = iter.next();
                }
                model.drawDestCards(card1, card2, card3);
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
