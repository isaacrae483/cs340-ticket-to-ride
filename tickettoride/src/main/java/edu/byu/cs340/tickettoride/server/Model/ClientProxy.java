package edu.byu.cs340.tickettoride.server.Model;

import java.util.List;
import java.util.Objects;

import edu.byu.cs340.tickettoride.server.Observers.IClientObserver;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientProxy implements IClientObserver, IClient {

    private Username user;

    public ClientProxy(Username user) {
        this.user = user;
    }

    @Override
    public void OnPlayerJoin(Player player, ID game) {
        incrementPlayers(game, player);
    }

    @Override
    public void OnNewGame(Game game) {
        addGame(game);
    }

    @Override
    public void OnGameStart(ID gameStart) {
        this.startGame(gameStart);
    }

    @Override
    public void OnDraw(List<TrainCard> cards, Player p) {
        this.addCards(cards, p);
    }

    @Override
    public void OnFaceUpUpdate(TrainCard card, int pos) {
        this.setFaceUpCard(card, pos);
    }

    @Override
    public void OnChat(ChatMessage message) {
        ServerModel model = ServerModel.SINGLETON;
        Game game = model.getMapStartedGames().getGame(message.getGame());
        if (game.contains(user)) {
            receiveChat(message);
        }
    }


    @Override
    public void incrementPlayers(ID id, Player player) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.INCREMENTPLAYER, player, id
                )
        );
    }

    @Override
    public void addGame(Game game) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.NEWGAME, game
                )
        );
    }

    @Override
    public void startGame(ID gameId) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.STARTGAME, gameId
                )
        );
    }

    @Override
    public void receiveChat(ChatMessage message) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.CHAT,
                        message.getUser(), message.getGame(), message.getMessage()
                )
        );
    }

    @Override
    public void addCards(List<TrainCard> cards, Player p) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.ADD_CARDS,
                        cards,
                        p
                )
        );
    }

    @Override
    public void setFaceUpCard(TrainCard card, int pos) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.REPLACE_FACE_UP,
                        card,
                        pos
                )
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientProxy that = (ClientProxy) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
