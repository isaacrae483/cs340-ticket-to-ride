package edu.byu.cs340.tickettoride.Client.model;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.model.events.bank.BankCardsChanged;
import edu.byu.cs340.tickettoride.Client.model.events.chat.ChatSendFailed;
import edu.byu.cs340.tickettoride.Client.model.events.game.PlayerCountChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.ActiveGameChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameAdded;
import edu.byu.cs340.tickettoride.Client.model.events.gamelist.GameListChanged;
import edu.byu.cs340.tickettoride.Client.model.events.gamelobby.GameStarted;
import edu.byu.cs340.tickettoride.Client.model.events.hand.HandChanged;
import edu.byu.cs340.tickettoride.Client.model.events.login.LoginSuccess;
import edu.byu.cs340.tickettoride.Client.model.events.map.RouteClaimed;
import edu.byu.cs340.tickettoride.Client.model.events.traincarddeck.TCDeckSizeChanged;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Board.Routes;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.Chat;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Decks.Bank;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Decks.TrainCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.EventEmitter;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Game.MapGames;
import edu.byu.cs340.tickettoride.shared.Game.events.Event;
import edu.byu.cs340.tickettoride.shared.Game.events.chat.ChatAdded;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestCardDraw;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestCardReturned;
import edu.byu.cs340.tickettoride.shared.Game.events.destCard.DestDeckSizeChanged;
import edu.byu.cs340.tickettoride.shared.Game.events.PlayerTurnChanged;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientModel extends EventEmitter {
    private static ClientModel _instance;
    private ClientModel(){
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
    private Chat chatMessages;
    private TrainCardDeck trainCardDeck;
    private Bank bank;
    private Routes mRoutes = new Routes();
    boolean mDrawnCards = false;
    private Player winningPlayer;

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

    public void drawDestCards(Player player) {

        if (player.getPlayerName().equals(getUsername())) {
            Player p = activeGame.getPlayer(player.getPlayerName());
            resetPlayer(player);

            int diff = player.getNumDestCards() - p.getNumDestCards();
            int last = player.getNumDestCards() - 1;
            DestCard draw1 = null;
            DestCard draw2 = null;
            DestCard draw3 = null;

            p = activeGame.getPlayer(player.getPlayerName());
            if (diff > 0) {
                draw1 = p.DestCardAt(last);
            }
            if (diff > 1) {
                draw2 = p.DestCardAt(last - 1);
            }
            if (diff > 2) {
                draw3 = p.DestCardAt(last - 2);
            }
            emitEvent(new DestCardDraw(draw1, draw2, draw3));
        }
        else {
            resetPlayer(player);
        }
        emitEvent(new DestDeckSizeChanged());
    }

    public void returnDestCard(DestCard toReturn) throws DestCardDeck.AlreadyInDeckException {
        Player current = activeGame.getPlayer(username);
        current.returnDestCard(activeGame, toReturn);
        emitEvent(new DestCardReturned(toReturn));
    }

    public int getDestCardDeckSize() {
        return destCardDeckSize;
    }

    public List<DestCard> getDestCards() {
        Player current = activeGame.getPlayer(username);
        return current.getTickets();
    }

    public void incrementPlayers(ID id, Player newUser){
        Game game = this.getGame(id);
        game.addPlayer(newUser);
        emitEvent(new PlayerCountChanged(game));
    }

    public void startGame(ID gameId) {
        Game game = this.getGame(gameId);
        game.startGame();
        mDrawnCards = false;
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
        Player current = getActiveGame().getPlayer(username);
        return current.getCards();
    }

    public List<TrainCard> getCardsInBank() {
        return bank.getCards();
    }

    public int getTrainCardDeckSize() {
        return trainCardDeck.getSize();
    }

    public void drewDestCards(int numCards) {
        destCardDeckSize -= numCards;
        emitEvent(new DestDeckSizeChanged());
    }

    public boolean drawnYet() {
        return mDrawnCards;
    }

    public void haveDoneFirstDraw() {
        mDrawnCards = true;
    }

    public void setWinningPlayer(Player player){
        this.winningPlayer = player;
        emitEvent(new Event(){});
    }

    public Username getWinningPlayer(){
        if(winningPlayer == null){
            try{
                username = new Username("NoOneWon");
            }catch(Username.InvalidUserNameException e){
                return null;
            }
            return username;
        }
        return winningPlayer.getPlayerName();
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
                player.addTrainCarPoints(points);
                break;
            }
        }
        emitEvent(new Event() {});//should pass a real event
    }
    public void addTrainCard(TrainCard card){
        Player current = activeGame.getPlayer(username);
        current.DrawCard(card);
        emitEvent(new HandChanged());
    }
    public void removeTrainCard(TrainCard card){
        Player current = activeGame.getPlayer(username);
        //current.removeCards(1, card.getColor());
        emitEvent(new HandChanged());
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


    public void updatePlayerTurn(){
        activeGame.nextPlayerTurn();
        emitEvent(new PlayerTurnChanged());
    }

    public Username getPlayerTurn() {
        return activeGame.getPlayerTurn();
    }

    public void replaceFaceUpTrainCard(TrainCard card, int pos) {
        bank.replaceCard(pos, card);
        emitEvent(new BankCardsChanged());
    }

    public TrainCard getFaceUpCard(int pos) {
        return bank.getCards().get(pos);
    }

    public Routes getRoutes() {
        return mRoutes;
    }

    public void claimRoute(Route route, Player player) {
        Route modelRoute = mRoutes.getRoute(route.getId());
        modelRoute.claimRoute(player);
        emitEvent(new RouteClaimed(modelRoute));
    }

    public void modifyTrainCardDeckSize(int deckSize) {
        trainCardDeck.drawCard();
        emitEvent(new TCDeckSizeChanged());
    }

    public void resetPlayer(Player player) {
        activeGame.ResetPlayer(player);
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
