package edu.byu.cs340.tickettoride.shared.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.byu.cs340.tickettoride.shared.Game.Board.Board;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.Chat;
import edu.byu.cs340.tickettoride.shared.Game.Decks.Bank;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Decks.TrainCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Game extends EventBubbler implements IGameListEntry {
    private ID id;
    private List<Player> players;
    private int numPlayers;
    private final int MAX_PLAYERS = 5;
    private final int MIN_PLAYERS = 2;
    boolean gameStarted = false;
    private Chat chat;
    private Board board;
    private TrainCardDeck trainCardDeck = new TrainCardDeck();
    private DestCardDeck destCardDeck  = new DestCardDeck();
    private Bank bank = new Bank();
    private Username playerTurn;
    private int playerTurnIndex;
    public Game() {
        id = ID.generate();
        chat = new Chat(id);

    }

    //this constructor is the initialize game constructor.... may need to be changed?
    public Game(ID id) {
        this.id = id;
        chat = new Chat(this.id);
        board = new Board();
        playerTurnIndex = 0;
//        playerTurn = players.get(playerTurnIndex).getPlayerName();
    }

    public int getPlayerTurnIndex() {
        return playerTurnIndex;
    }

    public void nextPlayerTurn(){
        playerTurnIndex = (playerTurnIndex + 1) % (players.size());
        playerTurn = players.get(playerTurnIndex).getPlayerName();
    }

    public void claimRoute(Route route, Player player){
        //This may need some help and extra thought.
        board.claimRoute(route, player);
    }

    private void initializeGame(){
        //Do stuff
    }

    @Override
    public int getPlayerCount(){
        return players.size();
    }

    @Override
    public boolean isGameFull() {
        return (getPlayerCount() >= MAX_PLAYERS);
    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public boolean canGameBeStarted() {
        return (getPlayerCount() >= MIN_PLAYERS && getPlayerCount() <= MAX_PLAYERS);
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void startGame() {
        gameStarted = true;
    }

    public Player GetLeader() {
        if (players != null && !players.isEmpty()) {
            return players.get(0);
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(player);
    }

    public boolean contains(Username name) {
        boolean isIn = false;
        for (Player p : players) {
            if (p.getPlayerName().equals(name)) {
                isIn = true;
            }
        }
        return isIn;
    }

    public Chat getChat() {
        return chat;
    }

    public Set<DestCard> drawTickets() {
        return destCardDeck.drawCards();
    }

    public void returnTickets(DestCard card) throws DestCardDeck.AlreadyInDeckException {
        destCardDeck.returnCard(card);
    }

    public TrainCard drawCard() {
        return trainCardDeck.drawCard();
    }

    public int destCardDeckSize() {
        return destCardDeck.size();
    }

    public Player getPlayer(IPlayer.Color color) {
        for (Player p : players) {
            if (p.getColor().equals(color)) {
                return p;
            }
        }
        return null;
    }
}