package edu.byu.cs340.tickettoride.shared;

public interface IServer {
    LoginResult login(Username username, Password password);
    LoginResult register(Username username, Password password);
    JoinGameResult joinGame(ID id);
    CreateGameResult createGame();
}
