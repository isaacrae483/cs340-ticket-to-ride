package edu.byu.cs340.tickettoride.shared.Player;

import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Player implements IPlayer {

    private Username username;
    private Color color;
    private Hand hand = new Hand();
    private Points trainCarPoints;
    private Points ticketPoints;
    private Points negTicketPoints;
    private Points longTrainPoints;
    private Points totalPoints;
    private TrainPieces trainPieces = new TrainPieces();

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

    public Hand getHand(){
        if(hand == null){
            hand = new Hand();
        }
        return hand;
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
}
