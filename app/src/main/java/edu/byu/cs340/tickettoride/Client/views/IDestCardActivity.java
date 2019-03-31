package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public abstract class IDestCardActivity extends PresenterViewActivity {
    public abstract void setCards(List<DestCard> cards);
    public abstract void onCardDraw(DestCard card1, DestCard card2, DestCard card3);
    public abstract void onCardReturn(DestCard card, ReturnCardLimit cardLimit);
    public abstract void SetDeckSize(int size);
    public abstract void FinishedDrawing();
    public abstract void SetNumReturned(int numReturned);

    public static class ReturnCardLimit {
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
