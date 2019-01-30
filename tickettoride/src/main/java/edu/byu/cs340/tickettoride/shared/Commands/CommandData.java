package edu.byu.cs340.tickettoride.shared.Commands;

import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class CommandData {
    public enum commandType {LOGIN, REGISTER, JOINGAME, CREATEGAME}
    private commandType type = null;
    private Username username = null;
    private Password password = null;
    private ID gameID = null;

    public CommandData(commandType type, Username username, Password password){
        this.type = type;
        this.username = username;
        this.password = password;
    }
    public CommandData(commandType type, Username username, ID ID){
        this.type = type;
        this.username = username;
        this.gameID = ID;
    }
    public CommandData(commandType type, Username username){
        this.type = type;
        this.username = username;

    }
}
