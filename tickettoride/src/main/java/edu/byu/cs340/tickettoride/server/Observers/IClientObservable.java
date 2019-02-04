package edu.byu.cs340.tickettoride.server.Observers;

public interface IClientObservable {
    void AddObserver(IClientObserver observer);
}
