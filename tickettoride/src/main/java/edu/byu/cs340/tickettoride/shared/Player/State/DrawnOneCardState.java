package edu.byu.cs340.tickettoride.shared.Player.State;


import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class DrawnOneCardState extends TurnState {

    @Override
    public TurnState drawFaceUpCard(Player player, Game game, int index){
        TrainCard card = game.peekFaceUp(index);
        if (card.getColor().equals(Colors.RAINBOW)) {
            return this;
        }

        player.DrawCard(game.drawFaceUpCard(index));
        game.nextPlayerTurn();
        return new OtherTurnState();
    }

    @Override
    public TurnState drawFaceDownCard(Player player, Game game){
        player.DrawCard(game.drawCard());
        game.nextPlayerTurn();
        return new OtherTurnState();
    }
}
