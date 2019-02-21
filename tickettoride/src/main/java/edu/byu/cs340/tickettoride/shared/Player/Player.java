package edu.byu.cs340.tickettoride.shared.Player;

import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Player implements IPlayer {

    private Username username;
    private Color color;
    private Hand hand;
    private Points points;
    private TrainPieces trainPieces;

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
        points.addPoints(add);
    }

    public Points getPoints(){
        return points;
    }

    public void playTrains(int amount) {
        trainPieces.playTrains(amount);
    }

    public boolean canBuild(int amount){
        return trainPieces.canBuild(amount);
    }

    public boolean lastTurn(){
        return trainPieces.lastTurn();
    }
}
