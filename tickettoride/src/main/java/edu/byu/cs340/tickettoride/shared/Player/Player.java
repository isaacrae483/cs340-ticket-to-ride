package edu.byu.cs340.tickettoride.shared.Player;

import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Player implements IPlayer {

    private Username username;
    private Color color;
    private Hand hand = new Hand();
    private Points points = new Points();
    private TrainPieces trainPieces = new TrainPieces();

    public Player(Username username, Color color) {
        this.username = username;
        this.color = color;


    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Username getPlayerName() {
        return username;
    }

    public void addPoints(int add){
        if(points == null){
            points = new Points();
        }
        points.addPoints(add);
    }

    public Points getPoints(){
        return points;
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

    public TrainPieces getTrainPieces() {
        return trainPieces;
    }

    public boolean isLastTurn(){
        if(trainPieces == null){
            trainPieces = new TrainPieces();
            return false;
        }
        return trainPieces.lastTurn();
    }
}
