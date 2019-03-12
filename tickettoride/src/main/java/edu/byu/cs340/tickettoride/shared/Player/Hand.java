package edu.byu.cs340.tickettoride.shared.Player;

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

    public boolean hasCards(int num, Enum color){
        int count = 0;

        for(TrainCard card : trainCards){
            if(card.getColor() == color || card.getColor() == Colors.RAINBOW){
                count++;
            }
        }
        if(count >= num)
            return true;
        else
            return false;
    }

    /**
     * pre: hasCards function returns true for same num and color
     * post:
     * @param num
     * @param color
     */
    public void removeCards(int num, Colors color){
        for(TrainCard card : trainCards){
            if(card.getColor() == color){
                trainCards.remove(card);
                num--;
                if(num == 0){
                    return;
                }
            }
        }
        for(TrainCard card : trainCards){
            if(card.getColor() == Colors.RAINBOW){
                trainCards.remove(card);
                num--;
                if(num == 0){
                    return;
                }
            }
        }
    }
}
