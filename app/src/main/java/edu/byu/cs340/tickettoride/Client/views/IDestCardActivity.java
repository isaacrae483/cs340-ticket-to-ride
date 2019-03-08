package edu.byu.cs340.tickettoride.Client.views;

import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public interface IDestCardActivity {
    void addCard(DestCard card);
    void setCards(List<DestCard> cards);
    void onCardDraw(DestCard card1, DestCard card2, DestCard card3);
    void onCardReturn(DestCard card);
}
