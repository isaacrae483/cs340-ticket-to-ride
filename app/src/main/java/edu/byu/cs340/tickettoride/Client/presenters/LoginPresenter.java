package edu.byu.cs340.tickettoride.Client.presenters;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.model.events.login.LoginFailed;
import edu.byu.cs340.tickettoride.Client.model.events.login.LoginSuccess;
import edu.byu.cs340.tickettoride.Client.model.events.login.RegisterFailed;
import edu.byu.cs340.tickettoride.Client.model.events.login.RegisterSuccess;
import edu.byu.cs340.tickettoride.Client.views.ILoginView;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class LoginPresenter extends Presenter implements ILoginPresenter {

    private ILoginView mLoginView;
    private ModelFacade mModelFacade;

    private Username mUsername = null;
    private Password mPassword = null;
    private URL mSeverHost = null;


    public LoginPresenter(ILoginView view) {
        // Call Presenter constructor
        super();
        try {
            mSeverHost = new URL("http://10.0.2.2:8080");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mLoginView = view;
        mModelFacade = ModelFacade.instance();

        // If user is already logged in, start GameListActivity
    }


    /**
     * Asks the ClientFacade to do operations to register the user
     *
     */
    public void login(){
        mModelFacade.login(mUsername, mPassword, mSeverHost);
        //mLoginView.moveToGameList();
    }


    /**
     * Asks the ClientFacade to do operations to login the user
     */
    public void register() {
        mModelFacade.register(mUsername, mPassword, mSeverHost);
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
        Log.d("Presenter", "Update called from observer");
        if (o instanceof LoginSuccess) {
            mLoginView.displayLoginSuccess();
            mLoginView.enableButtons(true);
            mLoginView.moveToGameList();
        } else if (o instanceof RegisterSuccess) {
            mLoginView.displayRegisterSuccess();
            mLoginView.enableButtons(true);
            mLoginView.moveToGameList();
        } else if (o instanceof RegisterFailed) {
            mLoginView.displayRegisterFailed();
            mLoginView.enableButtons(true);
        } else if (o instanceof LoginFailed) {
            mLoginView.displayLoginFailed();
            mLoginView.enableButtons(true);
        }
    }

    /**
     * When login has been pressed on the view, this function fires.
     * It ensures all the fields have been entered correctly, and if so, attempts to login
     */
    @Override
    public void loginPressed() {
        if (mUsername != null && mPassword != null && mSeverHost != null) {
            mLoginView.enableButtons(false);
            login();
        } else {
            if (mUsername == null) {
                mLoginView.warnUsername();
            }
            if (mPassword == null) {
                mLoginView.warnPassword();
            }
            if (mSeverHost == null) {
                mLoginView.warnHost();
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
            mLoginView.enableButtons(false);
            register();
        } else {
            if (mUsername == null) {
                mLoginView.warnUsername();
            }
            if (mPassword == null) {
                mLoginView.warnPassword();
            }
            if (mSeverHost == null) {
                mLoginView.warnHost();
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
            mSeverHost = new URL("http://" + host + ":8080");
        } catch (MalformedURLException e) {
            mSeverHost = null;
        }
    }

}
