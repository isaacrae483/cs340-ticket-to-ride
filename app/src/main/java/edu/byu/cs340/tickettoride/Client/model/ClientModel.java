package edu.byu.cs340.tickettoride.Client.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.model.events.bank.BankCardsChanged;
import edu.byu.cs340.tickettoride.Client.model.events.hand.HandChanged;
import edu.byu.cs340.tickettoride.Client.model.events.map.RouteClaimed;
import edu.byu.cs340.tickettoride.Client.model.events.traincarddeck.TCDeckSizeChanged;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Board.Routes;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Decks.Bank;
import edu.byu.cs340.tickettoride.shared.Game.Decks.TrainCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.EventEmitter;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;
import edu.byu.cs340.tickettoride.Client.model.events.chat.ChatSendFailed;
import edu.byu.cs340.tickettoride.shared.Game.events.chat.ChatAdded;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestCardDraw;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestCardReturned;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestDeckSizeChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameAdded;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameListChanged;
import edu.byu.cs340.tickettoride.Client.model.events.game.PlayerCountChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelobby.GameStarted;
import edu.byu.cs340.tickettoride.Client.model.events.login.LoginSuccess;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.ActiveGameChanged;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.Chat;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.Player.Hand;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientModel extends EventEmitter {
    private static ClientModel _instance;
    private ClientModel(){
        hand = new Hand();
        trainCardDeck = new TrainCardDeck();
        bank = new Bank();
        destCardDeckSize = DestCardDeck.standardSize;
    }
    public static ClientModel instance(){
        if (_instance == null){
            _instance = new ClientModel();
        }
        return _instance;
    }

    private Game activeGame;
    private Username username;
    private MapGames games = new MapGames();
    private ID activeGameID;
    private Hand hand;
    private Chat chatMessages;
    private TrainCardDeck trainCardDeck;
    private Bank bank;
    private Routes mRoutes = new Routes();

    private int destCardDeckSize;

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
        emitEvent(new LoginSuccess());
    }

    public MapGames getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        for (Game g : games) {
            this.games.addGame(g);
        }
        emitEvent(new GameListChanged());
    }

    public Game getGame(ID id){
        return games.getGame(id);
    }

    public void addGame(Game game){
        games.addGame(game);
        emitEvent(new GameAdded(game));
    }

    public void drawDestCards(DestCard card1, DestCard card2, DestCard card3) {
        if (card1 != null) {
            hand.addTicket(card1);
            destCardDeckSize -= 1;
        }
        if (card2 != null) {
            hand.addTicket(card2);
            destCardDeckSize -= 1;
        }
        if (card3 != null) {
            hand.addTicket(card3);
            destCardDeckSize -=1;
        }

        emitEvent(new DestCardDraw(card1, card2, card3));
        emitEvent(new DestDeckSizeChanged());
    }

    public void returnDestCard(DestCard toReturn) {
        hand.getDestCards().remove(toReturn);
        destCardDeckSize += 1;
        emitEvent(new DestCardReturned(toReturn));
        emitEvent(new DestDeckSizeChanged());
    }

    public int getDestCardDeckSize() {
        return destCardDeckSize;
    }

    public List<DestCard> getDestCards() {
        return hand.getDestCards();
    }

    public void incrementPlayers(ID id, Player newUser){
        Game game = this.getGame(id);
        game.addPlayer(newUser);
        emitEvent(new PlayerCountChanged(game));
    }

    public void startGame(ID gameId) {
        this.getGame(gameId).startGame();
        emitEvent(new GameStarted(this.getGame(gameId)));
    }

    // Active Game is defined as the game the user is currently observing, whether or not it has started.
    public ID getActiveGameID() {
        return activeGameID;
    }

    public void setActiveGameID(ID activeGameID) {
        this.activeGameID = activeGameID;
        activeGame = getGame(getActiveGameID());
        emitEvent(new ActiveGameChanged());
    }

    public Game getActiveGame() {
        return activeGame;
    }

    public void addChatMessage(ChatMessage chat){
        if(chatMessages == null){
            chatMessages = new Chat(activeGameID);
            emitEvent(new ChatAdded(chat));
        }
        try{
            chatMessages.add(chat);
            emitEvent(new ChatAdded(chat));
        }catch(Exception e){
            passErrorEvent(new ChatSendFailed());
        }
    }

    public Chat getChatMessages(){
        return chatMessages;
    }

    public List<TrainCard> getPlayerTrainCards() {
        return hand.getTrainCards();
    }

    public List<TrainCard> getCardsInBank() {
        return bank.getCards();
    }

    public int getTrainCardDeckSize() {
        return trainCardDeck.getSize();
    }

//    public void passErrorEvent(ErrorEvent errorEvent) {
//        emitEvent(errorEvent);
//    }
//
//    private void emitEvent(Event event) {
//        setChanged();
//        notifyObservers(event);
//    }

    //for Demo first half
    public void updatePoints(int points){
        for(Player player : activeGame.getPlayers()){
            if(player.getPlayerName().getUsername().equals(username.getUsername())){
                player.addPoints(points);
                break;
            }
        }
        emitEvent(new Event() {});//should pass a real event
    }
    public void addTrainCard(TrainCard card){
        hand.addCard(card);
        emitEvent(new HandChanged());//should pass a real event
    }
    public void removeTrainCard(TrainCard card){
        hand.removeCards(1, card.getColor());
        emitEvent(new HandChanged());//should pass a real event
    }

    public void addDestCard(DestCard card){
        hand.addTicket(card);
        emitEvent(new Event() {});//should pass a real event
    }
    public void updateOppTrainCard(TrainCard card){
        for(Player player : activeGame.getPlayers()){
            if(!player.getPlayerName().equals(username)){
                player.getHand().addCard(card);
                break;
            }
        }
        emitEvent(new Event() {});//should pass a real event
    }
    public void updateOppTrainCars(int cars){
        for(Player player : activeGame.getPlayers()){
            if(!player.getPlayerName().equals(username)){
                player.playTrains(cars);
                break;
            }
        }
        emitEvent(new Event() {});//should pass a real event
    }
    public void updateOppDestCard(DestCard card){
        for(Player player : activeGame.getPlayers()){
            if(!player.getPlayerName().equals(username)){
                player.getHand().addTicket(card);
                break;
            }
        }
        emitEvent(new Event() {});//should pass a real event
    }

    public void updatePlayerTurn(){
        activeGame.nextPlayerTurn();
    }

    public void replaceFaceUpTrainCard(TrainCard card, int pos) {
        bank.replaceCard(pos, card);
        emitEvent(new BankCardsChanged());
    }

    public Routes getRoutes() {
        return mRoutes;
    }
    public void claimRoute(Route route) {
        Route modelRoute = mRoutes.getRoute(route.getId());
        modelRoute.claimRoute(route.getClaimedBy());
        emitEvent(new RouteClaimed(modelRoute));
    }

    public void modifyTrainCardDeckSize(int deckSize) {
        trainCardDeck.drawCard();
        emitEvent(new TCDeckSizeChanged());
    }




//    public void passErrorEvent(ErrorEvent errorEvent) {
//        emitEvent(errorEvent);
//    }
//
//    private void emitEvent(Event event) {
//        setChanged();
//        notifyObservers(event);
//    }

}
