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
                return new AddCardsCommand(info.cards, info.player);
            case REPLACE_FACE_UP:
                return new ReplaceFaceUpCardCommand(info.trainCard, info.pos);

        }
        //tried to create non-existent command
        assert (false);
        return null;
    }
}
