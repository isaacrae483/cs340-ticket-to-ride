package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.Result.DrawFaceUpCardResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

/**
 * Created by Thomas Lewis on 3/25/19.
 */
public class DrawFaceUpCardCommand implements ICommand {
    private Integer mCardIndex;
    private Username mPlayer;
    private ID mGameId;

    public DrawFaceUpCardCommand(Integer cardIndex, Username player, ID gameId) {
        this.mCardIndex = cardIndex;
        this.mPlayer = player;
        this.mGameId = gameId;
    }

    @Override
    public DrawFaceUpCardResult execute() {
        return ServerFacade.SINGLETON.drawFaceUpCard(mCardIndex, mPlayer, mGameId);
    }
}