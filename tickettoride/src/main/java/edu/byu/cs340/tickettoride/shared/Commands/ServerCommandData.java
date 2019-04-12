package edu.byu.cs340.tickettoride.shared.Commands;

import java.io.Serializable;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerCommandData implements Serializable {
    public enum commandType {LOGIN, REGISTER, JOINGAME, CREATEGAME, STARTGAME,
        CHAT, DRAWTICKETS, RETURNCARD, DRAWFACEUPCARD, DRAWFACEDOWNCARD, CLAIMROUTE,
        FINISHDESTCARDS}

    public ServerCommandData(commandType type, Username username, Password password) {
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public ServerCommandData(commandType type, Username username) {
        this.type = type;
        this.username = username;
    }

    // DRAWFACEDOWNCARD, etc
    public ServerCommandData(commandType type, Username username, ID game) {
        this.type = type;
        gameID = game;
        this.username = username;
    }

    //DRAWFACEUPCARD
    public ServerCommandData(commandType type, Integer index, Username username, ID gameID) {
        this.type = type;
        this.username = username;
        this.gameID = gameID;
        this.index = index;
    }

    // RETURNCARD
    public ServerCommandData(commandType type, Username username, DestCard destCard, ID gameID) {
        this.type = type;
        this.username = username;
        this.gameID = gameID;
        this.destCard = destCard;
    }

    // CHAT
    public ServerCommandData(commandType type, ChatMessage message) {
        this.type = type;
        this.message = message;
    }

    // CLIAMROUTE
    public ServerCommandData(commandType type, Username username, Route route, ID gameID){
        this.type = type;
        this.username = username;
        this.route = route;
        this.gameID = gameID;
    }



    public commandType type;
    public Username username;
    public Password password;
    public ID gameID;
    public DestCard destCard;
    public ChatMessage message;
    public Integer index;
    public Player player;
    public Route route;
}
