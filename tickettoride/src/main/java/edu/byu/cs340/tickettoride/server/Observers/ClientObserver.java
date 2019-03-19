package edu.byu.cs340.tickettoride.server.Observers;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs340.tickettoride.server.Observers.Event.AddCardsEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.AddGameEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.ChatEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.FaceUpCardEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.GameEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.LastTurnEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.PlayerJoinedGameEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.RouteClaimedEvent;
import edu.byu.cs340.tickettoride.server.Observers.Event.StartGameEvent;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Result.RouteClaimedResult;
import edu.byu.cs340.tickettoride.shared.User.Username;

public abstract class ClientObserver implements IClient, Observer {
    @Override
    public void update(Observable o, Object data) {

        if (data instanceof StartGameEvent) {
            StartGameEvent event = (StartGameEvent) data;
            this.startGame(event.getId());
        }
        else if (data instanceof AddGameEvent) {
            AddGameEvent event = (AddGameEvent) data;
            this.addGame(event.getGame());
        }
        else if (data instanceof PlayerJoinedGameEvent) {
            PlayerJoinedGameEvent event = (PlayerJoinedGameEvent) data;
            this.incrementPlayers(event.getGame(), event.getPlayer());
        }
        else if (data instanceof GameEvent) {
            this.handleGameEvent((GameEvent) data);
        }


    }

    public ClientObserver(Username user) {
        this.user = user;
    }

    protected Username getUser() {
        return user;
    }

    private Username user;

    private void handleGameEvent(GameEvent e) {
        Game game = ServerModel.SINGLETON.getStartedGame(e.getId());
        if (!game.contains(user)) {
            return;
        }

        if (e instanceof ChatEvent) {
            ChatEvent event = (ChatEvent) e;
            this.receiveChat(event.getMessage());
        }
        else if (e instanceof FaceUpCardEvent) {
            FaceUpCardEvent event = (FaceUpCardEvent) e;
            this.setFaceUpCard(event.getCard(), event.getIndex());
        }
        else if (e instanceof AddCardsEvent) {
            AddCardsEvent event = (AddCardsEvent) e;
            this.addCards(event.getCards(), event.getPlayer());
        }
        else if (e instanceof RouteClaimedEvent) {
            RouteClaimedEvent event = (RouteClaimedEvent) e;
            this.claimRoute(event.getRoute(), event.getPlayer());
        }
        else if (e instanceof LastTurnEvent) {
            LastTurnEvent event = (LastTurnEvent) e;
            this.lastTurn();
        }
    }
}
