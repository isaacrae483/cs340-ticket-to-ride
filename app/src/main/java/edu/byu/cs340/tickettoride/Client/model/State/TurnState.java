package edu.byu.cs340.tickettoride.Client.model.State;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;

public abstract class TurnState {
    public TurnState returnDestCard(){return this;}
    public TurnState drawDestCard(){return this;}
    public TurnState finishDrawingDestCards(){return this;}
    public TurnState drawFaceUpCard(int index){return this;}
    public TurnState drawFaceDownCard(){return this;}
    public TurnState claimRoute(Route route){return this;}
    public TurnState nextTurn(){return this;}
}
