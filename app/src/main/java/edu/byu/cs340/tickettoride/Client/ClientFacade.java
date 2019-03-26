package edu.byu.cs340.tickettoride.Client;

import java.util.List;
import java.util.Set;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.shared.Game.Board.Route;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IClient;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ClientFacade implements IClient {
    private static ClientFacade _instance;
    private ClientFacade(){

    }

    public static ClientFacade instance() {
        if (_instance == null){
            _instance = new ClientFacade();
        }
        return _instance;
    }

    private ClientModel model = ClientModel.instance();
    private Username _username;


    @Override
    public void incrementPlayers(ID id, Player player) {
        ClientModel.instance().incrementPlayers(id, player);
    }

    @Override
    public void addGame(Game game) {
        ClientModel.instance().addGame(game);
        if(model.getUsername().equals(game.GetLeader().getPlayerName())){
            model.setActiveGameID(game.getId());
        }
    }

    @Override
    public void startGame(ID gameId) {
        ClientModel.instance().startGame(gameId);
    }

    @Override
    public void receiveChat(ChatMessage chatMessage) {
        ClientModel.instance().addChatMessage(chatMessage);
    }

    @Override
    public void addCards(Player player) {
        model.ResetPlayer(player);
    }

    @Override
    public void setFaceUpCard(TrainCard cards, int pos) {
        model.replaceFaceUpTrainCard(cards, pos);
    }

    @Override
    public void drawTrainCard(Player player) {
        model.ResetPlayer(player);
    }

    @Override
    public void drawFaceUpCard(Player player) {
        model.ResetPlayer(player);
    }

    @Override
    public void claimRoute(Route route, Player player) {

    }

    @Override
    public void lastTurn() {

    }

    @Override
    public void endGame(List<Player> players) {

    }

    @Override
    public void addGameHistory(String data) {

    }

    @Override
    public void changeDestDeckSize(int offset, Player player) {
        model.drewDestCards(offset);
        model.ResetPlayer(player);
    }


}
