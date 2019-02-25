package edu.byu.cs340.tickettoride.shared.Game.Decks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Colors;

public class TrainCardDeck {
    private Deque<TrainCard> deck;

    public TrainCardDeck() {
        deck = new ArrayDeque<>();

        List<TrainCard> temp = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            temp.add(new TrainCard(Colors.PINK));
            temp.add(new TrainCard(Colors.WHITE));
            temp.add(new TrainCard(Colors.BLUE));
            temp.add(new TrainCard(Colors.YELLOW));
            temp.add(new TrainCard(Colors.ORANGE));
            temp.add(new TrainCard(Colors.BLACK));
            temp.add(new TrainCard(Colors.RED));
            temp.add(new TrainCard(Colors.GREEN));
        }

        for (int i = 0; i < 14; i++) {
            temp.add(new TrainCard(Colors.RAINBOW));
        }

        Collections.shuffle(temp);
        deck.addAll(temp);
    }

    public TrainCard drawCard() {
        return deck.removeFirst();
    }

    public void returnCard(TrainCard card) {
        deck.addLast(card);
    }
}
