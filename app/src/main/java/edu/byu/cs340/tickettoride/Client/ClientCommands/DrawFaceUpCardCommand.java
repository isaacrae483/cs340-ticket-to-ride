package edu.byu.cs340.tickettoride.Client.ClientCommands;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class DrawFaceUpCardCommand implements ICommand {
    private Player player;
    private int trainCardDeckSize;
    private List<TrainCard> newFaceUpCards;

    public DrawFaceUpCardCommand(int trainCardDeckSize, Player player, List<TrainCard> faceUpCards) {
        this.player = player;
        this.trainCardDeckSize = trainCardDeckSize;
        this.newFaceUpCards = faceUpCards;
    }

    @Override
    public Object execute() {
        ClientFacade.instance().drawFaceUpCard(player, trainCardDeckSize, newFaceUpCards);
        return null;
    }
}