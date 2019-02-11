package edu.byu.cs340.tickettoride.Client.views;

/**
 * Created by Thomas Lewis on 2/5/19.
 */
public interface ILoginView {

    void warnUsername();
    void warnPassword();
    void warnHost();
    void moveToGameList();
    void enableButtons(Boolean isEnabled);
    void displayRegisterSuccess();
    void displayRegisterFailed();
    void displayLoginSuccess();
    void displayLoginFailed();

}
