package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.server.ServerFacade;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;
import edu.byu.cs340.tickettoride.shared.User.Password;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class RegisterCommand implements ICommand {

    public RegisterCommand(Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    private Username username;
    private Password password;

    @Override
    public LoginResult execute() {
        return ServerFacade.SINGLETON.register(username, password);
    }
}
