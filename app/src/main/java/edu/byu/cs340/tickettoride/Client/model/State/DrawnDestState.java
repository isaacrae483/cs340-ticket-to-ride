package edu.byu.cs340.tickettoride.Client.model.State;

public class DrawnDestState extends TurnState {
    @Override
    public TurnState returnDestCard(){
        //TODO actually return destination card (check number?)
        return this;
    }
    @Override
    public TurnState finishDrawingDestCards(){
        return new OtherTurnState();
    }
}
