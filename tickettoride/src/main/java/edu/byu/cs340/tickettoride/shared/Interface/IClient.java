package edu.byu.cs340.tickettoride.shared.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Player.Points;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IClient {
    void incrementPlayers(ID id, Player newUser);
    void addGame(Game game);
    void startGame(ID gameId);
    void receiveChat(ChatMessage chatMessage);
    void addCards(Player player);
    void setFaceUpCard(TrainCard card, int pos);

    void drawTrainCard(Player player);
    void drewFaceUpCard(Player player, int newTrainCardDeckSize, List<TrainCard> newTrainCards);
    void claimRoute(Route route, Player player);
    void lastTurn();//simply notify that it is last turn could influence state
    void endGame(List<Player> players); //this way we can get final points from all players(after destination cards) to determine winner
    void addGameHistory(String data);
    void changeDestDeckSize(int offset, Player player);
    void updateTurn(ID game);
    void changeTCDeckSize(Integer size, Player player);
}
