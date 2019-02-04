package edu.byu.cs340.tickettoride.Client.presenters;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.ClientModel;
import edu.byu.cs340.tickettoride.Client.views.LoginActivity;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class LoginPresenter implements ILoginPresenter, Observer {

    private LoginActivity mLoginActivity;
    private ClientFacade mClientFacade;
    private ClientModel mClientModel;

    private Username mUsername = null;
    private Password mPassword = null;
    private URL mSeverHost = null;


    public LoginPresenter(LoginActivity activity) {
        mLoginActivity = activity;
        mClientFacade = ClientFacade.instance();
        mClientModel = ClientModel.instance();

        // If user is already logged in, start GameListActivity
    }

    public void login(Username username, Password password){

    }

    public void register(Username username, Password password) {

    }
    /*
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
    */

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void loginPressed() {
        if (mUsername != null && mPassword != null && mSeverHost != null) {
            mLoginActivity.enableButtons(false);
            User loginUser = new User(mUsername, mPassword);
            //new LoginTask().execute()
        }
    }

    @Override
    public void registerPressed() {

    }

    @Override
    public void usernameChanged(String username) {
        try {
            mUsername = new Username(username);
        } catch (Username.InvalidUserNameException e) {
            String error = mLoginActivity.getResources().getString(R.string.username_incorrect_format);
            mLoginActivity.warnUsername(error);
        }
    }

    @Override
    public void passwordChanged(String password) {
        try {
            mPassword = new Password(password);
        } catch (Password.InvalidPasswordException e) {
            String error = mLoginActivity.getResources().getString(R.string.password_incorrect_format);
            mLoginActivity.warnPassword(error);
        }
    }

    @Override
    public void serverHostChanged(String host) {
        try {
            mSeverHost = new URL(host);
        } catch (MalformedURLException e) {
            String error = mLoginActivity.getResources().getString(R.string.url_incorrect_format);
            mLoginActivity.warnHost(error);
        }
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
