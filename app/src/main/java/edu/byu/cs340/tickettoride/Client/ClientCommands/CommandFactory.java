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
        }
        //tried to create non-existent command
        assert (false);
        return null;
    }
}
