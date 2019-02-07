package edu.byu.cs340.tickettoride.server.Model;

import java.util.Objects;

import edu.byu.cs340.tickettoride.server.Observers.IClientObserver;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.User;
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
    public void incrementPlayers(ID id, Player player) {
        ServerModel.SINGLTON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.INCREMENTPLAYER, player, id
                )
        );
    }

    @Override
    public void addGame(Game game) {
        ServerModel.SINGLTON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.NEWGAME, game
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
