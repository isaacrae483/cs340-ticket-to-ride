package edu.byu.cs340.tickettoride.shared.Commands;

import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientCommandData {
    public enum CommandType {INCREMENTPLAYER, NEWGAME}

    public ClientCommandData(CommandType type, Username username, ID game) {
        this.id = game;
        this.username = username;
        this.type = type;
    }

    public ClientCommandData(CommandType type, Game game) {
        this.game = game;
        this.type = type;
    }

    public ID id;
    public Game game;
    public Username username;

    public CommandType type;
}
