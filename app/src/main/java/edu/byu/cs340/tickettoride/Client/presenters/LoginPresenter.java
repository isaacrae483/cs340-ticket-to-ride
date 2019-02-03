package edu.byu.cs340.tickettoride.Client.presenters;

import android.os.AsyncTask;
import android.widget.Toast;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class LoginPresenter {
        Username username;
        Password password;

    public void login(String username_string, String password_string){
        try{
            username = new Username(username_string);
        }catch(Exception e){
            displayToast("Invalid Username");
        }
        try{
            password = new Password(password_string);
        }catch(Exception e){
            displayToast("Invalid Password");
        }
        User user = new User(username, password);
        LoginTask task = new LoginTask();
        task.execute(user);
    }

    public void register(String username_string, String password_string){
        try{
            username = new Username(username_string);
        }catch(Exception e){
            displayToast("Invalid Username");
        }
        try{
            password = new Password(password_string);
        }catch(Exception e){
            displayToast("Invalid Password");
        }
        User user = new User(username, password);
        RegisterTask task = new RegisterTask();
        task.execute(user);
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

    public class RegisterTask extends AsyncTask<User, Integer, Boolean> {
        User user;
        Username username;
        Password password;
        @Override
        protected Boolean doInBackground(User...users){
            user = users[0];
            username = user.getUserName();
            password = user.getPassword();

            ClientFacade.instance().register(username, password);
            return true;
        }
    }

}
