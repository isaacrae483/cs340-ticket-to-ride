package edu.byu.cs340.tickettoride.shared.Commands;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class CommandData {
    public enum commandType {LOGIN, REGISTER, JOINGAME, CREATEGAME, INCREMENTPLAYER, NEWGAME}

    public CommandData(commandType type, String arg1, String arg2) {
        this.type = type;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public commandType type;
    public String arg1;
    public String arg2;
}
