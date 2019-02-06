package edu.byu.cs340.tickettoride.Client.presenters;

import edu.byu.cs340.tickettoride.Client.views.LoginActivity;

/**
 * Created by Thomas Lewis on 2/3/19.
 */
public interface ILoginPresenter extends IPresenter {

    void loginPressed();
    void registerPressed();
    void usernameChanged(String username);
    void passwordChanged(String password);
    void serverHostChanged(String host);


}
