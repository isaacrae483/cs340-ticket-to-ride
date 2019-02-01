package edu.byu.cs340.tickettoride.Client;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
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

    public void login(Username username, Password password){
        LoginResult result = ServerProxy.instance().login(username, password);
        if(result.getSuccess()){
            ClientModel.instance().setUsername(username);
        }
        else{
            //calls the presenter to display a toast with the error
        }
    }

    public void register(Username username, Password password){
        LoginResult result = ServerProxy.instance().register(username, password);
        if(result.getSuccess()){
            ClientModel.instance().setUsername(username);
        }
        else{
            //calls the presenter to display a toast with the error
        }

    }

    @Override
    public void incrementPlayers(ID id, Username newUser) {
        ClientModel.instance().incrementPlayers(id, newUser);

        //needs to update view
    }

    @Override
    public void addGame(Game game) {
        ClientModel.instance().addGame(game);

        //needs to update view
    }
}
