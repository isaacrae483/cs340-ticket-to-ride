package edu.byu.cs340.tickettoride.Client.ClientCommands;

import edu.byu.cs340.tickettoride.Client.ClientFacade;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class DrawFaceUpCardCommand implements ICommand {
    private Player player;

    // TODO: Needs index of the drawn card
    public DrawFaceUpCardCommand(int card, Player player) {
        this.player = player;
    }

    @Override
    public Object execute() {
        ClientFacade.instance().drawFaceUpCard(player);
        return null;
    }
}
