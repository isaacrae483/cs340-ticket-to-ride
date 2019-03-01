package edu.byu.cs340.tickettoride.shared.Game.Decks;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

import static org.junit.Assert.*;

public class DestCardDeckTest {

    private DestCardDeck deck;
    private Set<DestCard> cards;

    @Before
    public void setUp() throws Exception {
        deck = new DestCardDeck();
        cards = new HashSet<>();
    }

    @Test
    public void drawCards() {
        int i = 1;
        while (deck.size() >= 3) {
            cards.addAll(deck.drawCards());
            assertEquals(3 * i, cards.size());
            ++i;
        }
        cards.addAll(deck.drawCards());
        assertTrue(3 * (i - 1) <= cards.size());
        assertTrue(3 * i > cards.size());
    }

    @Test
    public void returnCard() throws DestCardDeck.AlreadyInDeckException {
        int size = deck.size();
        Set<DestCard> cards = deck.drawCards();
        assertEquals(size - 3, deck.size());
        size = deck.size();

        DestCard toReturn = cards.iterator().next();
        deck.returnCard(toReturn);
        assertEquals(size + 1, deck.size());

        boolean success = true;
        try {
            deck.returnCard(toReturn);
        }
        catch (DestCardDeck.AlreadyInDeckException ex) {
            success = false;
        }
        assertEquals(size + 1, deck.size());
        assertFalse(success);

        while (deck.size() > 3) {
            deck.drawCards();
        }
        cards = deck.drawCards();
        assertTrue(cards.contains(toReturn));
    }
}