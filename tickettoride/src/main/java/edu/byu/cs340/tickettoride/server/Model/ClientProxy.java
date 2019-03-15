package edu.byu.cs340.tickettoride.server.Model;

import java.util.List;
import java.util.Objects;

import edu.byu.cs340.tickettoride.server.Observers.ClientObserver;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
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
        ServerModel.SINGLETON.getCommandList().AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.INCREMENTPLAYER, player, id
                )
        );
    }

    @Override
    public void addGame(Game game) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.NEWGAME, game
                )
        );
    }

    @Override
    public void startGame(ID gameId) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.STARTGAME, gameId
                )
        );
    }

    @Override
    public void receiveChat(ChatMessage message) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.CHAT,
                        message.getUser(), message.getGame(), message.getMessage()
                )
        );
    }

    @Override
    public void addCards(List<TrainCard> cards, Player p) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                getUser(), new ClientCommandData(
                        ClientCommandData.CommandType.ADD_CARDS,
                        cards,
                        p
                )
        );
    }

    @Override
    public void setFaceUpCard(TrainCard card, int pos) {
        ServerModel.SINGLETON.getCommandList().AddCommand(
                getUser(), new ClientCommandData(
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
        return Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }
}
