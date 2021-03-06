package edu.byu.cs340.tickettoride.server.Model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import edu.byu.cs340.tickettoride.server.Observers.ClientObserver;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientProxy extends ClientObserver {


    public ClientProxy(Username user) {
        super(user);
    }

    @Override
    public void incrementPlayers(ID id, Player player) {
        ServerModel.SINGLETON.AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.INCREMENTPLAYER, player, id
                )
        );
    }

    @Override
    public void addGame(Game game) {
        ServerModel.SINGLETON.AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.NEWGAME, game
                )
        );
    }

    @Override
    public void startGame(ID gameId) {
        ServerModel.SINGLETON.AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.STARTGAME, gameId
                )
        );
    }

    @Override
    public void receiveChat(ChatMessage message) {
        ServerModel.SINGLETON.AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.CHAT,
                        message.getUser(), message.getGame(), message.getMessage()
                )
        );
    }

    @Override
    public void addCards(Player p) {
        ServerModel.SINGLETON.AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.ADD_CARDS,
                        p
                )
        );
    }

    @Override
    public void setFaceUpCard(TrainCard card, int pos) {
        ServerModel.SINGLETON.AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.REPLACE_FACE_UP,
                        card,
                        pos
                )
        );
    }
//////////////////////////////////////////new for phase three  ///////////// not yet implemented////////////////////////////////
    @Override
    public void drawTrainCard(Player player) {
        ServerModel.SINGLETON.AddCommand(getUser(),
                new ClientCommandData(
                        ClientCommandData.CommandType.DRAW_TRAIN_CARD,
                        player
                ));

    }

    @Override
    public void drewFaceUpCard(Player player, int newTrainCardDeckSize, List<TrainCard> newTrainCards) {
        ServerModel.SINGLETON.AddCommand(getUser(),
                new ClientCommandData(
                        ClientCommandData.CommandType.DRAW_FACE_UP,
                        player,
                        newTrainCardDeckSize,
                        newTrainCards
                ));
    }

    @Override
    public void claimRoute(Route route, Player player) {
        ServerModel.SINGLETON.AddCommand(getUser(),
                new ClientCommandData(
                        ClientCommandData.CommandType.CLAIM_ROUTE,
                        route,
                        player
                ));
    }

    @Override
    public void lastTurn() {
        ServerModel.SINGLETON.AddCommand(
                getUser(),
                new ClientCommandData(
                        ClientCommandData.CommandType.LAST_TURN
                )
        );
    }

    @Override
    public void endGame(List<Player> players) {
        ServerModel.SINGLETON.AddCommand(
                getUser(),
                new ClientCommandData(
                        ClientCommandData.CommandType.END_GAME,
                        players
                )
        );
    }

    @Override
    public void addGameHistory(String data) {

    }

    @Override
    public void changeDestDeckSize(int offset, Player player) {
        ServerModel.SINGLETON.AddCommand(
                getUser(),
                new ClientCommandData(
                        ClientCommandData.CommandType.DEST_DECK_CHANGE,
                        offset,
                        player
                )
        );
    }

    public void updateTurn(ID game) {
        ServerModel.SINGLETON.AddCommand(
                getUser(),
                new ClientCommandData(
                        ClientCommandData.CommandType.NEXT_TURN,
                        game
                )
        );
    }

    @Override
    public void changeTCDeckSize(Integer size, Player player) {
        ServerModel.SINGLETON.AddCommand(
                getUser(),
                new ClientCommandData(
                        ClientCommandData.CommandType.TC_DECK_CHANGE,
                        size,
                        player
                )
        );
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientProxy that = (ClientProxy) o;
        return Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }
}
