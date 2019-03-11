package edu.byu.cs340.tickettoride.shared.Game.Decks;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

public class Bank {
    private List<TrainCard> bank = new ArrayList<>();
    public Bank() {
        bank.add(0, null);
        bank.add(1, null);
        bank.add(2, null);
        bank.add(3, null);
        bank.add(4, null);

    }
    public TrainCard drawCard(int id){
        return bank.get(id);

    }
    public void replaceCard(int id, TrainCard card){
        bank.add(id, card);
    }

    public List<TrainCard> getCards() {
        return bank;
    }
}
