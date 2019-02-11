package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;

/**
 * Created by Thomas Lewis on 2/5/19.
 */
public interface IGameListView {
    /** setGameList - Given a List of game entries, displays them in the view
     *
     *
     * @param games the List of games
     */
    void setGameList(List<IGameListEntry> games);

    /** addGameToList - Given a game entry, add it to the view to display
     *
     *  DOES NOT CHECK IF ENTRY IS UNIQUE
     *
     * @param game The entry to add
     */
    void addGameToList(IGameListEntry game);

    /** updateGame - Given a game entry, change the entry in the UI with the same return value for getId(), so that it matches the new entry
     *
     *
     * @param game The game entry to update
     */
    void updateGame(IGameListEntry game);

    /** makeToast - Given a string, display a toast on the view with that text
     *
     *
     * @param toastText The text to display
     */
    void makeToast(String toastText);

    /**
     * Starts and moves to the game lobby view
     */
    void moveToGameLobby();

    /**
     * If the game was not able to be joined, whether full or some other reason, display an error to the user
     */
    void displayGameJoinError();
}
