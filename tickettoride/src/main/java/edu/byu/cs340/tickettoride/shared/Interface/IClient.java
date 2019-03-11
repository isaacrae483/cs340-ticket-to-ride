package edu.byu.cs340.tickettoride.shared.Interface;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IClient {
    void incrementPlayers(ID id, Player newUser);
    void addGame(Game game);
    void startGame(ID gameId);
    void receiveChat(ChatMessage chatMessage);
    void addCards(List<TrainCard> cards);
}
