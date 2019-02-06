package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;

/**
 * Created by Thomas Lewis on 2/5/19.
 */
public interface IGameListActivity {
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

    /** updateGame - Given a game entry, change the entry with the same return value for getId()
     *
     *
     * @param game The game entry to update
     */
    void updateGame(IGameListEntry game);

    /** displayToast - Given a string, display a toast on the view with that text
     *
     *
     * @param toastText The text to display
     */
    void displayToast(String toastText);
}
