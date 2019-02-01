package edu.byu.cs340.tickettoride.Client.presenters;

import android.os.AsyncTask;
import android.widget.Toast;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class LoginPresenter {


    public void login(Username username, Password password){

    }

    public void register(Username username, Password password){

    }

    public void update(){
        //changes to the GameList Fragment
    }

    public void displayToast(String string){
        //displays toast usually an error that couldn't log in or register
    }


    public class LoginTask extends AsyncTask<User, Integer, Boolean> {
        User user;
        Username username;
        Password password;
        @Override
        protected Boolean doInBackground(User...users){
            user = users[0];
            username = user.getUserName();
            password = user.getPassword();

            ClientFacade.instance().login(username, password);
            return true;
        }
    }

}
