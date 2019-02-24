package edu.byu.cs340.tickettoride.shared.Commands;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerCommandData {
    public enum commandType {LOGIN, REGISTER, JOINGAME, CREATEGAME, STARTGAME, CHAT, DRAWTICKETS, RETURNCARD}

    public ServerCommandData(commandType type, Username username, Password password) {
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public ServerCommandData(commandType type, Username username) {
        this.type = type;
        this.username = username;
    }

    public ServerCommandData(commandType type, Username username, ID game) {
        this.type = type;
        gameID = game;
        this.username = username;
    }

    // RETURNCARD
    public ServerCommandData(commandType type, Username username, DestCard destCard, ID gameID) {
        this.type = type;
        this.username = username;
        this.gameID = gameID;
        this.destCard = destCard;
    }

    // CHAT
    public ServerCommandData(commandType type, Username username, ID gameID, String message) {
        this.type = type;
        this.username = username;
        this.gameID = gameID;
        this.message = message;
    }

    public commandType type;
    public Username username;
    public Password password;
    public ID gameID;
    public DestCard destCard;
    public String message;
}
