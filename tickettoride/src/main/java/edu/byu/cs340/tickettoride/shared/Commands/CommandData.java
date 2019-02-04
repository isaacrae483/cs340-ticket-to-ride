package edu.byu.cs340.tickettoride.shared.Commands;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class CommandData {
    public enum commandType {LOGIN, REGISTER, JOINGAME, CREATEGAME, INCREMENTPLAYER, NEWGAME}

    public CommandData(commandType type, Username username, Password password) {
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public CommandData(commandType type, Username username) {
        this.type = type;
        this.username = username;
    }

    public CommandData(commandType type, Username username, ID id) {
        this.type = type;
        this.username = username;
        this.id = id;
    }

    public commandType type;
    public Username username;
    public Password password;
    public ID id;
}
