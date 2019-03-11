package edu.byu.cs340.tickettoride.shared.Commands;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientCommandData {
    public enum CommandType {INCREMENTPLAYER, NEWGAME, STARTGAME, CHAT, ADD_CARDS }

    public ClientCommandData(CommandType type, Player player, ID game) {
        this.id = game;
        this.player = player;
        this.type = type;
    }

    public ClientCommandData(CommandType type, Game game) {
        this.game = game;
        this.type = type;
    }

    public  ClientCommandData(CommandType type, ID id) {
        this.type = type;
        this.id = id;
    }

    public ClientCommandData(CommandType type, Username username, ID id, String message) {
        this.id = id;
        this.message = message;
        this.username = username;
        this.type = type;
    }

    public ClientCommandData(CommandType type, List<TrainCard> cards) {
        this.type = type;
        this.cards = cards;
    }

    public ID id;
    public Game game;
    public Player player;
    public String message;
    public Username username;
    public List<TrainCard> cards;

    public CommandType type;
}
