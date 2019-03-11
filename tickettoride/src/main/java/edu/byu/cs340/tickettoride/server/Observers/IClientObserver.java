package edu.byu.cs340.tickettoride.server.Observers;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IClientObserver {
    void OnPlayerJoin(Player player, ID game);
    void OnNewGame(Game game);
    void OnGameStart(ID gameStart);
    void OnChat(ChatMessage message);
    void OnDraw(List<TrainCard> cards, Player player);
}

