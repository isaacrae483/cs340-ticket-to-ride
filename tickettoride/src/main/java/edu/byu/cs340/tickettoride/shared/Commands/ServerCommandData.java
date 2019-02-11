package edu.byu.cs340.tickettoride.shared.Commands;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ServerCommandData {
    public enum commandType {LOGIN, REGISTER, JOINGAME, CREATEGAME, GETCOMMANDS}

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

    public commandType type;
    public Username username;
    public Password password;
    public ID gameID;
}
