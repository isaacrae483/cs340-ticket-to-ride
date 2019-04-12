package edu.byu.cs340.tickettoride.shared.Player.State;

import java.io.Serializable;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class OtherTurnState extends TurnState implements Serializable {

    @Override
    public TurnState nextTurn(Player player, Game game){
        if (game.getPlayerTurn().equals(player.getPlayerName())) {
            return new BeginTurnState();
        }

        return this;
    }
}
