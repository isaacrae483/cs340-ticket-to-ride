package edu.byu.cs340.tickettoride.shared.Game.Decks;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;

public class Bank {
    private List<TrainCard> bank;
    public TrainCard drawCard(int id){
        return bank.get(id);

    }
    public void replaceCard(int id, TrainCard card){
        bank.add(id, card);
    }
}
