package edu.byu.cs340.tickettoride.shared.Game.Decks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

public class TrainCardDeck {
    private Deque<TrainCard> deck;

    private List<TrainCard> discardPile = new ArrayList<>();

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

    public TrainCard peekCard() {
        return deck.peekFirst();
    }

    /**
     * @pre none
     * @post deck should have one fewer card, or have zero cards.
     * @return drawn TrainCard or null if empty
     */
    public TrainCard drawCard() {
        if (deck.size() == 1) {
            takeCards(discardPile);
        }
        return deck.size() == 0 ? null : deck.removeFirst();
    }

    /**
     * @pre none
     * @post deck has one additional card if card is non-null
     * @param card TrainCard to put on bottom of deck
     * @return true if card is non-null and successfully inserted
     */
    public boolean returnCard(TrainCard card) {
        if (card == null) return false;
        return deck.offerLast(card);
    }

    /**
     * @pre none
     * @post none
     * @return number of TrainCards in deck
     */
    public Integer getSize() {
        return deck.size();
    }

    public void takeCards(List<TrainCard> cards) {
        Collections.shuffle(cards);
        deck.addAll(cards);
        cards.clear();
    }

    public void addToDiscardPile(TrainCard card) {
        if (card != null)
            discardPile.add(card);
    }
}
