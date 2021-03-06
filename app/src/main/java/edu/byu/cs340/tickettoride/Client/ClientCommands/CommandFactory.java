package edu.byu.cs340.tickettoride.Client.ClientCommands;


import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class CommandFactory {
    public static ICommand Generate(ClientCommandData info) {
        switch (info.type) {
            case NEWGAME:
                return new NewGameCommand(info.game);
            case INCREMENTPLAYER:
                return new IncrementPlayersCommand(info.player, info.id);
            case STARTGAME:
                return new GameStartedCommand(info.id);
            case CHAT:
                return new ChatCommand(info.username, info.id, info.message);
            case ADD_CARDS:
                return new AddCardsCommand(info.player);
            case REPLACE_FACE_UP:
                return new ReplaceFaceUpCardCommand(info.trainCard, info.pos);
            case LAST_TURN:
                return new LastTurnCommand();
            case CLAIM_ROUTE:
                return new ClaimRouteCommand(info.route, info.player);
            case DEST_DECK_CHANGE:
                return new DestDeckChangedCommand(info.pos, info.player);
            case DRAW_TRAIN_CARD:
                return new DrawTrainCardCommand(info.player);
            case NEXT_TURN:
                return new NextTurnCommand(info.id);
            case DRAW_FACE_UP:
                return new DrawFaceUpCardCommand(info.deckSize, info.player, info.cards);
            case TC_DECK_CHANGE:
                return new TCDeckChangedCommand(info.pos, info.player);
            case END_GAME:
                return new EndGameCommand(info.players);


        }
        //tried to create non-existent command
        assert (false);
        return null;
    }
}

