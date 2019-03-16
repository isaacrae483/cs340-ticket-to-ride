package edu.byu.cs340.tickettoride.Client.model.State;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;

public class OtherTurnState extends TurnState {

    private ClientModel model = ClientModel.instance();
    @Override
    public TurnState nextTurn(){
        model.updatePlayerTurn();
        if (model.getPlayerTurn().equals(model.getUsername())) {
            return new BeginTurnState();
        }
        else {
            return this;
        }
    }
}
