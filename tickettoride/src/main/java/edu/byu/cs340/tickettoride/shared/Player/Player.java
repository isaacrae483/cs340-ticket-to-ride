package edu.byu.cs340.tickettoride.shared.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.State.BeginTurnState;
import edu.byu.cs340.tickettoride.shared.Player.State.DrawnDestState;
import edu.byu.cs340.tickettoride.shared.Player.State.OtherTurnState;
import edu.byu.cs340.tickettoride.shared.Player.State.TurnState;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Player implements IPlayer, Serializable {

    private Username username;
    private Color color;
    private Hand hand = new Hand();
    private Points trainCarPoints;
    private Points ticketPoints;
    private Points negTicketPoints;
    private Points longTrainPoints;
    private Points totalPoints;
    private TrainPieces trainPieces = new TrainPieces();

    public TurnState getState() {
        return state;
    }

    private TurnState state = new BeginTurnState();

    public Player(Username username, Color color) {
        this.username = username;
        this.color = color;
        trainCarPoints = new Points();
        ticketPoints = new Points();
        negTicketPoints = new Points();
        longTrainPoints = new Points();
        totalPoints = new Points();

    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Username getPlayerName() {
        return username;
    }

    public int getTrainCarPoints(){
        return trainCarPoints.getPoints();
    }

    public void addTrainCarPoints(int add){
        if(trainCarPoints == null){
            trainCarPoints = new Points();
        }
        trainCarPoints.addPoints(add);
        totalPoints.addPoints(add);
    }

    public int getTicketPoints() {
        return ticketPoints.getPoints();
    }

    public void addTicketPoints(int add) {
        ticketPoints.addPoints(add);
        totalPoints.addPoints(add);
    }

    public void calculateNegTicketPoints(){
        for(DestCard card : hand.getDestCards()){
            if(!card.isCompleted()){
                addNegTicketPoints((-1)*card.getPoints());
            }
        }
    }

    public int getNegTicketPoints() {
        return negTicketPoints.getPoints();
    }

    public void addNegTicketPoints(int add) {
        negTicketPoints.addPoints(add);
        totalPoints.addPoints(add);
    }

    public int getLongTrainPoints() {
        return longTrainPoints.getPoints();
    }

    public void addLongTrainPoints(int add) {
        longTrainPoints.addPoints(add);
        totalPoints.addPoints(add);
    }

    public int getTotalPoints() {
        return totalPoints.getPoints();
    }



    public void playTrains(int amount) {
        if(trainPieces == null){
            trainPieces = new TrainPieces();
        }
        trainPieces.playTrains(amount);
    }

    public boolean canBuild(int amount){
        return trainPieces.canBuild(amount);
    }

    public boolean lastTurn(){
        return trainPieces.lastTurn();
    }


    public int getTrainPieces() {
        return trainPieces.getPieces();
    }

    public boolean isLastTurn(){
        if(trainPieces == null){
            trainPieces = new TrainPieces();
            return false;
        }
        return trainPieces.lastTurn();
    }

    public void DrawDestCards(Set<DestCard> cards) {
        for (DestCard c : cards) {
            hand.addTicket(c);
        }
    }

    public ArrayList<TrainCard> playRouteCards(Route route){
        if(hand.hasCards(route.getLength(), route.getColor())){
            return hand.removeCards(route.getLength(), route.getColor());
        }
        else
            return new ArrayList<>();
    }

    public boolean hasTrainCars(int length){
        return getTrainPieces() >= length;
    }
    public DestCard DestCardAt(int index) {
        return hand.getDestCards().get(index);
    }

    public List<DestCard> getTickets() {
        return hand.getTickets();
    }

    public List<TrainCard> getCards() {
        return hand.getTrainCards();
    }

    public int getNumDestCards() {
        return hand.getNumDestCards();
    }

    public int getNumTrainCards() {
        return hand.getNumTrainCards();
    }

    public void DrawCard(TrainCard card) {
        hand.addCard(card);
    }

    public void ReturnTicket(DestCard card) {
        hand.popDestCard(card);
    }

    public void returnDestCard(Game game, DestCard card) throws DestCardDeck.AlreadyInDeckException {
        state = state.returnDestCard(this, game, card);
    }
    public  void drawDestCard(Game game){
        state = state.drawDestCard(this, game);
    }
    public  void finishDrawingDestCards(Game game){
        state = state.finishDrawingDestCards(this, game);
    }
    public  void drawFaceUpCard(Game game, int index){
        state = state.drawFaceUpCard(this, game, index);
    }
    public void drawFaceDownCard(Game game){
        state = state.drawFaceDownCard(this, game);
    }
    public void claimRoute(Game game, Route route){
        state = state.claimRoute(this, game, route);
    }
    public void nextTurn(Game game){
        state = state.nextTurn(this, game);
    }

    public void setTurn() {
        state = new BeginTurnState();
    }

    public boolean isTurn() {
        return !(state.getClass() == OtherTurnState.class);
    }
}
