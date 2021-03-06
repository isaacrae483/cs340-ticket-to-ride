package edu.byu.cs340.tickettoride.shared.Commands;


import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.ChatCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.CreateGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.DrawFaceDownCardCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.DrawFaceUpCardCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.DrawTicketsCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.FinishDrawingDestCardsCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.JoinGameCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.LoginCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.RegisterCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.ReturnCardCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.RouteClaimedCommand;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommands.StartGameCommand;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;

public class ServerCommandFactory {
    public static ICommand Generate(ServerCommandData info) {
        switch (info.type) {
            case LOGIN:
                return new LoginCommand(info.username, info.password);
            case JOINGAME:
                return new JoinGameCommand(info.username, info.gameID);
            case REGISTER:
                return new RegisterCommand(info.username, info.password);
            case CREATEGAME:
                return new CreateGameCommand(info.username);
            case STARTGAME:
                return new StartGameCommand(info.username, info.gameID);
            case CHAT:
                return new ChatCommand(info.message);
            case RETURNCARD:
                return new ReturnCardCommand(info.username, info.destCard, info.gameID);
            case DRAWTICKETS:
                return new DrawTicketsCommand(info.username, info.gameID);
            case DRAWFACEUPCARD:
                return new DrawFaceUpCardCommand(info.index, info.username, info.gameID);
            case DRAWFACEDOWNCARD:
                return new DrawFaceDownCardCommand(info.username, info.gameID);
            case CLAIMROUTE:
                return new RouteClaimedCommand(info.route, info.username, info.gameID);
            case FINISHDESTCARDS:
                return  new FinishDrawingDestCardsCommand(info.username, info.gameID);
        }
        //tried to create non-existent command
        assert (false);
        return null;
    }

}
