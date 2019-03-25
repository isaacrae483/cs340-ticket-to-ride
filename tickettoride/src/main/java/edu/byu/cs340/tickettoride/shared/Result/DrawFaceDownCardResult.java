package edu.byu.cs340.tickettoride.shared.Result;

import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;

/**
 * Created by Thomas Lewis on 3/25/19.
 */
public class DrawFaceDownCardResult {
    private Boolean success;
    private TrainCard mDrawnTCard;

    public DrawFaceDownCardResult(Boolean success, TrainCard card) {
        this.success = success;
        mDrawnTCard = card;

    }

    public Boolean getSuccess() {
        return success;
    }

    public TrainCard getDrawnCard() {
        return mDrawnTCard;
    }
}
