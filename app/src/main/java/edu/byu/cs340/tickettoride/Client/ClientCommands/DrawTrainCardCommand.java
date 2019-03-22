package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class DrawTrainCardCommand implements ICommand {
    private TrainCard card;
    private Player player;

    public DrawTrainCardCommand(TrainCard card, Player player) {
        this.card = card;
        this.player = player;
    }

    @Override
    public Object execute() {
        ClientFacade.instance().drawTrainCard(card, player);
        return null;
    }
}