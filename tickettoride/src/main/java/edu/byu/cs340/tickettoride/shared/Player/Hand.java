package edu.byu.cs340.tickettoride.shared.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

public class Hand {
    private List<TrainCard> trainCards = new ArrayList<>();
    private List<DestCard> destCards = new ArrayList<>();

    public Hand(){

    }

    public int getNumTrainCards() {
        return trainCards.size();
    }

    public int getNumDestCards() {
        return destCards.size();
    }

    /**
     * pre: None
     * post: None
     * @return destCards
     */
    public List<DestCard> getDestCards() {
        return destCards;
    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }


    /**
     * pre: card != Null
     * post: size of trainCards one larger than it was
     *      trainCards contains card as well as all cards it had before
     * @param card
     */

    public void addCard(TrainCard card){
        trainCards.add(card);
    }

    /**
     * pre: card != Null
     * post: size of destCards one larger than it was
     *     destCards contains card as well as all cards it had before
     * @param card
     */

    public void addTicket(DestCard card){
        destCards.add(card);
    }

    /**
     * pre: num != 0 and color != Null
     * post: the train card deck is the same and contains at least the given number of the given color or rainbows
     * @param num
     * @param color
     * @return
     */

    public boolean hasCards(int num, Colors color){
        int count = 0;

        for(TrainCard card : trainCards){
            if(card.getColor() == color || card.getColor() == Colors.RAINBOW){
                count++;
            }
        }
        return count >= num;
    }

    public void popDestCard(DestCard card) {
        destCards.remove(card);
    }

    public List<DestCard> getTickets() {
        return destCards;
    }

    /**
     * pre: hasCards function returns true for same num and color
     * post: train card hand contains the same number of cards minus num, cards of the given color @param color are first removed, then rainbow cards are removed
     * @param num
     * @param color
     */
    public ArrayList<TrainCard> removeCards(int num, Colors color){
        ArrayList<TrainCard> removedCards = new ArrayList<>();
        for(TrainCard card : trainCards){
            if(card.getColor() == color){
                //trainCards.remove(card);
                removedCards.add(card);
                num--;
                if(num == 0){
                    cleanUp(removedCards);
                    return removedCards;
                }
            }
        }
        for(TrainCard card : trainCards){
            if(card.getColor() == Colors.RAINBOW){
                //trainCards.remove(card);
                removedCards.add(card);
                num--;
                if(num == 0){
                    cleanUp(removedCards);
                    return removedCards;
                }
            }
        }
        return new ArrayList<>();
    }

    private void cleanUp(ArrayList<TrainCard> removableCards){
        for(TrainCard remove: removableCards){
            trainCards.remove(remove);
        }
    }
}
