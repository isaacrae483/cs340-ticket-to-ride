package edu.byu.cs340.tickettoride.shared.Game.Board;

import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
public interface IRoute {
    Integer getId();
    IPlayer.Color getClaimColor();
}
