package edu.byu.cs340.tickettoride.Client.model;

import java.net.URL;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IModelFacade {
    void login(Username username, Password password, URL url);
    void register(Username username, Password password, URL host);
    void joinGame(ID id);
    void createGame();
    void sendStartGame(ID gameId);
    void sendChat(ChatMessage message);
    void drawTickets();
    void returnTicket(DestCard card);
    void drawFaceUpCard(Integer index, Username player);
    void drawFaceDownCard(Username player);
}
