package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public interface IDestCardActivity {
    void addCard(DestCard card);
    void setCards(List<DestCard> cards);
    void onCardDraw(DestCard card1, DestCard card2, DestCard card3);
    void onCardReturn(DestCard card, ReturnCardLimit cardLimit);
    void SetDeckSize(int size);

    class ReturnCardLimit {
        private int limit;
        private ReturnCardLimit(int limit) {
            this.limit = limit;
        }
        static public ReturnCardLimit Two() {
            return new ReturnCardLimit(2);
        }
        static public ReturnCardLimit One() {
            return new ReturnCardLimit(1);
        }
        public int value() {
            return limit;
        }
    }
}
