package edu.byu.cs340.tickettoride.shared.Commands;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientCommandData {
    public enum CommandType {INCREMENTPLAYER, NEWGAME, STARTGAME, CHAT, ADD_CARDS,
        REPLACE_FACE_UP, DRAW_TRAIN_CARD, CLAIM_ROUTE, LAST_TURN, END_GAME, GAME_HISTORY,
        DEST_DECK_CHANGE, NEXT_TURN, DRAW_FACE_UP}

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

    public ClientCommandData(CommandType type, TrainCard card, int pos) {
        this.trainCard = card;
        this.pos = pos;
        this.type = type;
    }

    public ClientCommandData(CommandType type, List<TrainCard> cards, Player player) {
        this.type = type;
        this.cards = cards;
        this.player = player;
    }

    public ClientCommandData(CommandType type, TrainCard card, Player player){
        this.type = type;
        this.trainCard = card;
        this.player = player;
    }

    public ClientCommandData(CommandType type, Route route, Player player){
        this.type = type;
        this.route = route;
        this.player = player;
    }

    public ClientCommandData(CommandType type){
        this.type = type;
    }

    public ClientCommandData(CommandType type, List<Player> players){
        this.type = type;
        this.players = players;
    }

    public ClientCommandData(CommandType type, String message){
        this.type = type;
        this.message = message;
    }



    public ClientCommandData(CommandType type, int pos, Player player) {
        this.type = type;
        this.pos = pos;
        this.player = player;
    }

    public ClientCommandData(CommandType type, Player player) {
        this.type = type;
        this.player = player;
    }

    public ClientCommandData(CommandType type, Player player, int deckSize, List<TrainCard> cards) {
        this.type = type;
        this.deckSize = deckSize;
        this.player = player;
        this.cards = cards;
    }



    public ID id;
    public Game game;
    public Player player;
    public String message;
    public Username username;
    public List<TrainCard> cards;
    public TrainCard trainCard;
    public int pos;
    public int deckSize;
    public Route route;
    public List<Player> players;

    public CommandType type;
}
