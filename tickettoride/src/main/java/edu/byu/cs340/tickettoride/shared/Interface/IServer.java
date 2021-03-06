package edu.byu.cs340.tickettoride.shared.Interface;

import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.ChatResult;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceDownCardResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceUpCardResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.FinishDrawingDestCardsResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnTicketResult;
import edu.byu.cs340.tickettoride.shared.Result.RouteClaimedResult;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public interface IServer {
    LoginResult login(Username username, Password password);
    LoginResult register(Username username, Password password);
    JoinGameResult joinGame(Username username, ID id);
    CreateGameResult createGame(Username username);
    ClientCommandList getCommands(Username username);
    StartGameResult startGame(Username username, ID id);
    DrawTicketsResult drawTickets(Username username, ID game);
    ChatResult chat(ChatMessage message);
    ReturnTicketResult returnTickets(Username username, DestCard card, ID game);
    RouteClaimedResult routeClaimed(Route route, Username player, ID game);
    DrawFaceUpCardResult drawFaceUpCard(Integer index, Username player, ID game);
    DrawFaceDownCardResult drawFaceDownCard(Username player, ID game);
    FinishDrawingDestCardsResult finishDrawingDestCards(Username player, ID game);
}
