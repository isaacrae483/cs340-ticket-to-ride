package edu.byu.cs340.tickettoride.Client.views;

/**
 * Created by Thomas Lewis on 2/5/19.
 */
public interface ILoginView {

    void warnUsername(String error);
    void warnPassword(String error);
    void warnServerHost(String error);

}
