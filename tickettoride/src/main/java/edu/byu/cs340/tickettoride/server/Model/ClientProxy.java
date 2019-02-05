package edu.byu.cs340.tickettoride.server.Model;

import edu.byu.cs340.tickettoride.server.Observers.IClientObserver;
import edu.byu.cs340.tickettoride.server.ServerModel;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.User.User;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientProxy implements IClientObserver, IClient {

    private Username user;

    public ClientProxy(Username user) {
        this.user = user;
    }

    @Override
    public void OnPlayerJoin(Username user, ID game) {
        incrementPlayers(game, user);
    }

    @Override
    public void OnNewGame(ID game) {
        addGame(new Game(game));
    }

    @Override
    public void incrementPlayers(ID id, Username newUser) {
        ServerModel.SINGLTON.getCommandList().AddCommand(
                user, new ClientCommandData(
                        ClientCommandData.CommandType.INCREMENTPLAYER, newUser, id
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
}
