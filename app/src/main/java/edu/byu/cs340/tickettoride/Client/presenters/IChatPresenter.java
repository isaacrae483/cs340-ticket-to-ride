package edu.byu.cs340.tickettoride.Client.presenters;

public interface IChatPresenter extends IPresenter {

    void sendPressed();

    void messageTextChanged(String toString);
}
