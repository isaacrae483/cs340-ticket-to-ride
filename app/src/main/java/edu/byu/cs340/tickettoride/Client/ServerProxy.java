
package edu.byu.cs340.tickettoride.Client;

import java.net.URL;

import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData.commandType;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IServer;
import edu.byu.cs340.tickettoride.shared.Result.ChatResult;
import edu.byu.cs340.tickettoride.shared.Result.CreateGameResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceDownCardResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceUpCardResult;
import edu.byu.cs340.tickettoride.shared.Result.DrawTicketsResult;
import edu.byu.cs340.tickettoride.shared.Result.JoinGameResult;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.Result.ReturnTicketResult;
import edu.byu.cs340.tickettoride.shared.Result.RouteClaimedResult;
import edu.byu.cs340.tickettoride.shared.Result.StartGameResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerProxy implements IServer {
    private static ServerProxy _instance;

    private ServerProxy() {

    }

    public static ServerProxy instance() {
        if (_instance == null) {
            _instance = new ServerProxy();
        }
        return _instance;
    }

    private URL host;
    private ServerCommandData data;
    private ClientCommunicator communicator = new ClientCommunicator();

    @Override
    public LoginResult login(Username username, Password password) {
        data = new ServerCommandData(commandType.LOGIN, username, password);
        return communicator.send(data, LoginResult.class);
    }

    @Override
    public LoginResult register(Username username, Password password) {
        data = new ServerCommandData(commandType.REGISTER, username, password);
        return communicator.send(data, LoginResult.class);
    }

    @Override
    public JoinGameResult joinGame(Username username, ID id) {
        data = new ServerCommandData(commandType.JOINGAME, username, id);
        return communicator.send(data, JoinGameResult.class);
    }

    @Override
    public CreateGameResult createGame(Username username) {
        data = new ServerCommandData(commandType.CREATEGAME, username);
        return communicator.send(data, CreateGameResult.class);
    }

    @Override
    public ClientCommandList getCommands(Username username) {
        //data = new ServerCommandData(commandType.GETCOMMANDS, username);
        return communicator.get(username, ClientCommandList.class);
    }

    @Override
    public StartGameResult startGame(Username username, ID id) {
        data = new ServerCommandData(commandType.STARTGAME, username, id);
        return communicator.send(data, StartGameResult.class);
    }


    @Override
    public DrawTicketsResult drawTickets(Username username, ID game) {
        data = new ServerCommandData(commandType.DRAWTICKETS, username, game);
        return communicator.send(data, DrawTicketsResult.class);
    }

    @Override
    public ChatResult chat(ChatMessage message) {
        data = new ServerCommandData(commandType.CHAT, message);
        return communicator.send(data, ChatResult.class);
    }

    @Override
    public ReturnTicketResult returnTickets(Username username, DestCard card, ID game) {
        data = new ServerCommandData(commandType.RETURNCARD, username, card, game);
        return communicator.send(data, ReturnTicketResult.class);
    }

    @Override
    public DrawFaceUpCardResult drawFaceUpCard(Integer index, Username player, ID game) {
        data = new ServerCommandData(commandType.DRAWFACEUPCARD, index, player, game);
        return communicator.send(data, DrawFaceUpCardResult.class);
    }

    @Override
    public DrawFaceDownCardResult drawFaceDownCard(Username player, ID game) {
        data = new ServerCommandData(commandType.DRAWFACEDOWNCARD, player, game);
        return communicator.send(data, DrawFaceDownCardResult.class);
    }

    @Override
    public RouteClaimedResult routeClaimed(Route route, Username player, ID game) {
        return null;
    }

    public void setHost(URL host) {
        this.host = host;
        communicator.setURL(host);
    }
}

