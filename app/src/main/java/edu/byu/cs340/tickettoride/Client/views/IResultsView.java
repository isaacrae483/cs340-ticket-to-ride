package edu.byu.cs340.tickettoride.Client.views;


import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IResultsView {
    public void uploadPlayer1Points(Player players);
    public void uploadPlayer2Points(Player players);
    public void uploadPlayer3Points(Player players);
    public void uploadPlayer4Points(Player players);
    public void uploadPlayer5Points(Player players);
    public void setWinner(Username winner);
}
