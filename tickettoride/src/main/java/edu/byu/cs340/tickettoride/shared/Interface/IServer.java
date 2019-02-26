package edu.byu.cs340.tickettoride.shared.Interface;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Result.BeginPlayingResult;
import edu.byu.cs340.tickettoride.shared.Result.ChatResult;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnCardResult;
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
    ChatResult chat(Username username, String message, ID game);
    ReturnCardResult returnCards(Username username, DestCard card, ID game);
}
