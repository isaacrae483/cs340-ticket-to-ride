package edu.byu.cs340.tickettoride.Client.presenters;

import android.os.AsyncTask;
import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.Client.ClientModel;
import edu.byu.cs340.tickettoride.Client.views.GameListActivity;
import edu.byu.cs340.tickettoride.Client.views.LoginActivity;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class LoginPresenter extends Presenter implements ILoginPresenter, Observer {

    private LoginActivity mLoginActivity;
    private ClientFacade mClientFacade;

    private Username mUsername = null;
    private Password mPassword = null;
    private URL mSeverHost = null;


    public LoginPresenter(LoginActivity activity) {
        // Call Presenter constructor
        super();

        mLoginActivity = activity;
        mClientFacade = ClientFacade.instance();

        // If user is already logged in, start GameListActivity
    }


    /**
     * Creates an async task through the facade to log in the user
     *
     */
    public void login(){
        mClientFacade.login(mUsername, mPassword, mSeverHost);
    }

    public void register() {
        mClientFacade.register(mUsername, mPassword, mSeverHost);
    }
    /**
     * Grabs all necessary information from the data model to display the view accurately, then updates the view accordingly
     */
    @Override
    public void syncWithModel() {
        super.syncWithModel();
    }

    /**Called by the observable when it has been mutated to notify observers to update
     *
     * @param observable the client model
     * @param o passed object
     */
    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

    }

    /**
     * When login has been pressed on the view, this function fires.
     * It ensures all the fields have been entered correctly, and if so, attempts to login
     */
    @Override
    public void loginPressed() {
        if (mUsername != null && mPassword != null && mSeverHost != null) {
            mLoginActivity.enableButtons(false);
            login();
        } else {
            if (mUsername == null) {
                String error = mLoginActivity.getResources().getString(R.string.username_incorrect_format);
                mLoginActivity.warnUsername(error);
            }
            if (mPassword == null) {
                String error = mLoginActivity.getResources().getString(R.string.password_incorrect_format);
                mLoginActivity.warnPassword(error);
            }
            if (mSeverHost == null) {
                String error = mLoginActivity.getResources().getString(R.string.url_incorrect_format);
                mLoginActivity.warnHost(error);
            }
        }
    }


    /**
     * When register has been pressed on the view, this function fires.
     * It ensures all the fields have been entered correctly, and if so, attempts to register
     */
    @Override
    public void registerPressed() {
        if (mUsername != null && mPassword != null && mSeverHost != null) {
            mLoginActivity.enableButtons(false);
            register();
        } else {
            if (mUsername == null) {
                String error = mLoginActivity.getResources().getString(R.string.username_incorrect_format);
                mLoginActivity.warnUsername(error);
            }
            if (mPassword == null) {
                String error = mLoginActivity.getResources().getString(R.string.password_incorrect_format);
                mLoginActivity.warnPassword(error);
            }
            if (mSeverHost == null) {
                String error = mLoginActivity.getResources().getString(R.string.url_incorrect_format);
                mLoginActivity.warnHost(error);
            }
        }
    }


    /**
     * Fires whenever the username value changes in the view
     *
     * Does input validation, and stores the username if it is valid.
     * If the username is invalid, the value in storage is set to null.
     *
     * @param username The username
     */
    @Override
    public void usernameChanged(String username) {
        try {
            mUsername = new Username(username);
        } catch (Username.InvalidUserNameException e) {
            mUsername = null;
        }
    }

    /**
     * Fires whenever the password value changes in the view
     *
     * Does input validation, and stores the password if it is valid.
     * If the password is invalid, the value in storage is set to null.
     *
     * @param password The password
     */
    @Override
    public void passwordChanged(String password) {
        try {
            mPassword = new Password(password);
        } catch (Password.InvalidPasswordException e) {
            mPassword = null;
        }
    }


    /**
     * Fires whenever the server host field value changes in the view
     *
     * Does input validation, and stores the host if it is a valid URL according to java's URL class.
     * If the URL is invalid, the value in storage is set to null.
     *
     *
     * @param host The host
     */
    @Override
    public void serverHostChanged(String host) {
        try {
            mSeverHost = new URL(host);
        } catch (MalformedURLException e) {
            mSeverHost = null;
        }
    }

}
