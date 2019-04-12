package edu.byu.cs340.tickettoride.shared.Game.Decks;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.City;

public class DestCardDeck implements Serializable {

    public DestCardDeck() {
        ArrayList<DestCard> temp = new ArrayList<>();
        temp.add(new DestCard(City.LOS_ANGELES, City.NEW_YORK, 21));
        temp.add(new DestCard(City.DULUTH, City.HOUSTON, 8));
        temp.add(new DestCard(City.SAULT_ST_MARIE, City.NASHVILLE, 8));
        temp.add(new DestCard(City.NEW_YORK, City.ATLANTA, 6));
        temp.add(new DestCard(City.PORTLAND, City.NASHVILLE, 17));
        temp.add(new DestCard(City.VANCOUVER, City.MONTREAL, 20));
        temp.add(new DestCard(City.DULUTH, City.EL_PASO, 10));
        temp.add(new DestCard(City.TORONTO, City.MIAMI, 10));
        temp.add(new DestCard(City.PORTLAND, City.PHOENIX, 11));
        temp.add(new DestCard(City.DALLAS, City.NEW_YORK, 11));
        temp.add(new DestCard(City.CALGARY, City.SALT_LAKE_CITY, 7));
        temp.add(new DestCard(City.CALGARY, City.PHOENIX, 13));
        temp.add(new DestCard(City.LOS_ANGELES, City.MIAMI, 20));
        temp.add(new DestCard(City.WINNIPEG, City.LITTLE_ROCK, 11));
        temp.add(new DestCard(City.SAN_FRANCISCO, City.ATLANTA, 17));
        temp.add(new DestCard(City.KANSAS_CITY, City.HOUSTON, 5));
        temp.add(new DestCard(City.LOS_ANGELES, City.CHICAGO, 16));
        temp.add(new DestCard(City.DENVER, City.PITTSBURGH, 11));
        temp.add(new DestCard(City.CHICAGO, City.SANTA_FE, 9));
        temp.add(new DestCard(City.VANCOUVER, City.SANTA_FE, 13));
        temp.add(new DestCard(City.BOSTON, City.MIAMI, 12));
        temp.add(new DestCard(City.CHICAGO, City.NEW_ORLEANS, 7));
        temp.add(new DestCard(City.MONTREAL, City.ATLANTA, 9));
        temp.add(new DestCard(City.SEATTLE, City.NEW_YORK, 22));
        temp.add(new DestCard(City.DENVER, City.EL_PASO, 4));
        temp.add(new DestCard(City.HELENA, City.LOS_ANGELES, 8));
        temp.add(new DestCard(City.WINNIPEG, City.HOUSTON, 12));
        temp.add(new DestCard(City.MONTREAL, City.NEW_ORLEANS, 13));
        temp.add(new DestCard(City.SAULT_ST_MARIE, City.OKLAHOMA_CITY, 9));
        temp.add(new DestCard(City.SEATTLE, City.LOS_ANGELES, 9));
        Collections.shuffle(temp);
        deck = new ArrayDeque<>(temp);
    }

    public static final int standardSize = 30;

    public class AlreadyInDeckException extends Exception {
        public AlreadyInDeckException(String message) {
            super(message);
        }
    }

    public Set<DestCard> drawCards() {
        Set<DestCard> toReturn = new HashSet<>();
        final int NUM_CARDS = 3;
        for(int i = 0; i < NUM_CARDS && !deck.isEmpty(); ++i){
            toReturn.add(deck.remove());
        }
        return toReturn;
    }

    public void returnCard(DestCard card) throws AlreadyInDeckException {
        if (!deck.contains(card)) {
            deck.add(card);
        }
        else {
            throw new AlreadyInDeckException(card + " is already in the deck");
        }
    }

    public int size() {
        return deck.size();
    }
    private Queue<DestCard> deck = null;
}
