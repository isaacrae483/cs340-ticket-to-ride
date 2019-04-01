package edu.byu.cs340.tickettoride.shared.Game;


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
import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * The Game class is the class that will hold all the elements and methods of a Game.
 */
public class Game extends EventBubbler implements IGameListEntry {
    /**
     * An ID to identify the game from other games
     */
    private ID id;
    /**
     * A list of Players that are participating in the given game.
     */
    private List<Player> players;
    /**
     * a final int to restrict players to the maximum of allowed players in a game.
     */
    private final int MAX_PLAYERS = 5;
    /**
     * a final int to restrict players to the minimum of allowed players in a game.
     */
    private final int MIN_PLAYERS = 2;
    /**
     * A boolean to assert whether the game is started or not.
     */
    boolean gameStarted = false;
    /**
     * Chat is available for player communication and plotting.
     */
    private Chat chat;
    /**
     * The board contains all the characteristics of a ticket to ride board - a map with routes on it.
     */
    private Board board;
    /**
     * TrainDeck holds all the train cards that players will be able to draw out of on their turn
     */
    private TrainCardDeck trainCardDeck = new TrainCardDeck();
    /**
     * DestCardDeck holds the destination cards that players will draw from to obtain more destinations.
     */
    private DestCardDeck destCardDeck  = new DestCardDeck();
    /**
     * The bank is composed of the face up cards that players can choose to draw from on the table.
     */
    private Bank bank = new Bank();
    /**
     * A username helps identify a players turn.
     */
    private Username playerTurn;
    /**
     * Gives an index to the player who's turn it is.
     */
    private int playerTurnIndex;

    private boolean waitingToStart = true;


    /**
     * Initializes a Game
     */
    public Game() {
        id = ID.generate();
        chat = new Chat(id);
    }

    /**
     * pre: id is equal to an active game id.
     * post: a Game is created with a Chat and Board
     * initializes game with active ID inorder to create a chat and board.
     * @param id the id of the game you would like to construct.
     */
    public Game(ID id) {
        this.id = id;
        chat = new Chat(this.id);
        board = new Board();
        playerTurnIndex = 0;
//        playerTurn = players.get(playerTurnIndex).getPlayerName();
    }

    /**
     * pre: None
     * post: returns an int between 0 - (the number of players - 1)
     * returns the Index of the player whose turn it is.
     * @return the current playerTurnIndex
     */
    public int getPlayerTurnIndex() {
        return playerTurnIndex;
    }

    /**
     * pre: None
     * post: playerTurnIndex is an int between 0 - (the number of players - 1) and
     * playerTurn is a player that is in the game. at index playerTurnIndex
     */
    public void nextPlayerTurn(){
        playerTurnIndex = (playerTurnIndex + 1) % (players.size());
        playerTurn = players.get(playerTurnIndex).getPlayerName();
        for (Player p : players) {
            p.nextTurn(this);
        }
    }

    public Username getPlayerTurn() {
        return playerTurn;
    }


    /**
     * pre: None
     * post: returns an int
     * returns the number of players in the game.
     * @return the number of players in the game.
     */
    @Override
    public int getPlayerCount(){
        return players.size();
    }

    /**
     * pre: None
     * post: See if the number of players is less than or equal to 5. if there are five, the boolean
     * should be true.
     * @return boolean whether the game is full or not.
     */
    @Override
    public boolean isGameFull() {
        return (getPlayerCount() >= MAX_PLAYERS);
    }

    /**
     * pre: None
     * post: check if id is a valid ID that exists
     * @return the id of the game.
     */
    @Override
    public ID getId() {
        return id;
    }

    /**
     * pre: make sure game isn't already started
     * post: check that the boolean is set to true if game can be started
     * Sets boolean if to true if there are enough players to begin a game.
     * @return boolean based off the number of players in the game. (there must be between 2-5).
     */
    @Override
    public boolean canGameBeStarted() {
        return (getPlayerCount() >= MIN_PLAYERS && getPlayerCount() <= MAX_PLAYERS);
    }

    /**
     * pre: None
     * post: None
     * @return Boolean on whether the game is started or not.
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * pre: make sure there are enough players to start
     * post: make sure that the pre condition is satisfied and the boolean changed if necessary
     */
    public void startGame() {
        gameStarted = true;
        final int FACE_UP_CARDS = 5;
        for (int i = 0; i < FACE_UP_CARDS; i++) {
            bank.replaceCard(i, trainCardDeck.drawCard());
        }
    }


    public void playersReturnedDestCards() {
        waitingToStart = false;
        getPlayers().get(0).setTurn();
    }

    /**
     * pre: make sure there are players in the game
     * post: make sure that the player at the 0th index is a real player
     *
     * returns the game leader, or the player who created the game.
     * @return the player who created the game.
     */
    public Player GetLeader() {
        if (players != null && !players.isEmpty()) {
            return players.get(0);
        }
        return null;
    }

    /**
     * pre: None
     * post: make sure all the players are legit players
     *
     * @return the list of all the players in the game
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * pre: make sure the player is associated with a registered user
     * post: make sure the players list increases by one after player is added.
     *
     * @param player the player to be added to the list
     */
    public void addPlayer(Player player){
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(player);
    }

    /**
     * pre: make username is a registered username
     * post: if username is legit, make sure isIn is set to true
     * @param name the Username of player that could be in the game
     * @return whether a player with the given username is in the game.
     */
    public boolean contains(Username name) {
        boolean isIn = false;
        for (Player p : players) {
            if (p.getPlayerName().equals(name)) {
                isIn = true;
            }
        }
        return isIn;
    }

    /**
     * pre: none
     * post: make sure object of type Chat is returned
     * @return the Chat associated with the game.
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * pre: make sure deck is not empty
     * post: that deck size decrease
     * @return cards drawn from the destCardsDeck
     */
    public Set<DestCard> drawTickets() {
        return destCardDeck.drawCards();
    }

    /**
     * pre: card is not null
     * post: drawn destination cards reduces one
     *
     * returns destination card that is not wanted by player
     *
     * @param card the destCard the player would like to return
     * @throws DestCardDeck.AlreadyInDeckException throws an exception if the card is already in the deck.
     */
    public void returnTickets(DestCard card) throws DestCardDeck.AlreadyInDeckException {
        destCardDeck.returnCard(card);
    }

    /**
     * pre: make sure trainCardDeck is not empty
     * post: make sure card drawn is not null.
     * @return the card drawn from the trainCardDeck
     */
    public TrainCard drawCard() {
        return trainCardDeck.drawCard();
    }

    /**
     * pre: make sure color matches a color enum in the IPlayer class
     * post: make sure the player exists and the color matches the players color
     *
     * @param color the color of the player they would like to find in the game.
     * @return the player to whom the color belongs.
     */
    public Player getPlayer(IPlayer.Color color) {
        for (Player p : players) {
            if (p.getColor().equals(color)) {
                return p;
            }
        }
        return null;
    }

    public Route getRoute(int id) {
        return board.getRoutes().getRoute(id);
    }

    public Player getPlayer(Username username) {
        for (Player p : players) {
            if (p.getPlayerName().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public TrainCard drawFaceUpCard(int index) {
        TrainCard card = bank.drawCard(index);
        TrainCard replacementCard = trainCardDeck.drawCard();
        bank.replaceCard(index, replacementCard);
        if (bank.needsFullRedraw()) {
            for (int i = 0; i < bank.MAX_CARDS; i++) {
                bank.replaceCard(i, trainCardDeck.drawCard());
            }
        }
        return card;
    }

    public boolean ClaimRoute(Route route, Player player)
    {
        ArrayList<TrainCard> playedCards = player.playRouteCards(route);
        if(playedCards.size() > 0){
            board.claimRoute(route, player);
            awardDestCardPoints(player);
            for (TrainCard card : playedCards) {
                trainCardDeck.addToDiscardPile(card);
            }
            return true;
        }
        else
            return false;

    }

    /**
     * Awards the player that just claimed a route points for having claimed a destination card if they connected cities needed
     *
     * @param player
     */
    private void awardDestCardPoints(Player player) {
        List<DestCard> cards = player.getTickets();
        for (DestCard card : cards) {
            if (!card.isCompleted()) {
                if (board.areTwoCitiesConnectedForUser(
                        card.getCity1(),
                        card.getCity2(),
                        player.getPlayerName(),
                        null
                )) {
                    card.setCompleted(true);
                    player.addTicketPoints(card.getPoints());
                    System.out.println("Awarded player: " + player.getPlayerName().getUsername() + " with dest card points " + card.getPoints());
                }
            }
        }
    }

    public void ReturnDestCard(DestCard card) throws DestCardDeck.AlreadyInDeckException {
        destCardDeck.returnCard(card);
    }

    public TrainCard peekFaceUp(int index) {
        return bank.getCards().get(index);
    }

    public TrainCard peekTrainCardDeck() {
        return trainCardDeck.peekCard();
    }

    public void ResetPlayer(Player player) {

        for (int i = 0; i < players.size(); ++i) {
            if (players.get(i).getPlayerName().equals(player.getPlayerName())) {
                players.set(i, player);
            }
        }
    }

    public void updateWaitingToStart() {
        if (waitingToStart) {
            boolean someonesTurn = false;
            for(Player p : players) {
                if (p.isTurn()) {
                    someonesTurn = true;
                }
            }
            if (!someonesTurn) {
                playersReturnedDestCards();
            }
        }
    }

    public Integer getTrainCardDeckSize() {
        return trainCardDeck.getSize();
    }

    public List<TrainCard> getBankCards() {
        return bank.getCards();
    }


}