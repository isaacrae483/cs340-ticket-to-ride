package edu.byu.cs340.tickettoride.Client.model;

import java.net.URL;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IModelFacade {
    public void login(Username username, Password password, URL url);
    public void register(Username username, Password password, URL host);
    public void joinGame(ID id);
    public void createGame();
    public void sendStartGame(ID gameId);
    public void sendChat(ChatMessage message);
    public void drawTickets();
    public void returnTicket(DestCard card);
}
