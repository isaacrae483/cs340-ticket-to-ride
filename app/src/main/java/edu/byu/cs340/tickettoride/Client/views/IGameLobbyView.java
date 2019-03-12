package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;

/**
 * Created by Thomas Lewis on 2/5/19.
 */
public interface IGameLobbyView {

    /**displayStartGame - Set if you want the view to display the Start Game button
     *
     * Should only be displayed if the player has permission to start the game
     *
     * @param buttonDisplayed True if you want the player to see start game, otherwise do not display
     */
    void displayStartGame(boolean buttonDisplayed);

    /**setPlayerList - Set the set of players currently in the lobby waiting to join the game
     *
     *
     * @param playerSet The set of players currently waiting for the game to start
     */
    void setPlayerSet(List<? extends IPlayer> playerSet);

    /** makeToast - Given a string, display a toast on the view with that text
     *
     *
     * @param toastText The text to display
     */
    void makeToast(String toastText);

    /** moveToStartGame - Starts the game view
     *
     */
    void moveToStartGame();

    /**
     *  Ends the view if nothing to display
     */
    void finishView();

    void displayStartGameError();

}
