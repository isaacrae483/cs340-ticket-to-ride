package edu.byu.cs340.tickettoride.shared.Game.Decks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

public class Bank implements Serializable {
    private List<TrainCard> bank = new ArrayList<>();
    public final int MAX_CARDS = 5;
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
    public TrainCard replaceCard(int id, TrainCard card){
        TrainCard oldCard = bank.get(id);
        bank.set(id, card);
        return oldCard;
    }

    public Boolean needsFullRedraw() {
        int count = 0;
        for (int i = 0; i < MAX_CARDS; i++) {
            if (bank.get(i) != null && bank.get(i).getColor().equals(Colors.RAINBOW))
                count++;
        }
        return count >= 3;
    }

    public List<TrainCard> getCards() {
        return bank;
    }
}
