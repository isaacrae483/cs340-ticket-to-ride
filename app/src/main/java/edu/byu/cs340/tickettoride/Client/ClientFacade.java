package edu.byu.cs340.tickettoride.Client;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
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
    public void addCards(List<TrainCard> cards, Player player) {

        if (player.getPlayerName().equals(model.getUsername())) {
            for (TrainCard card : cards) {
                ClientModel.instance().addTrainCard(card);
            }
        }
        for (TrainCard card : cards) {
            model.getActiveGame().getPlayer(player.getColor()).getHand().addCard(card);
        }
    }

    @Override
    public void setFaceUpCard(TrainCard cards, int pos) {
        model.replaceFaceUpTrainCard(cards, pos);
    }


}
