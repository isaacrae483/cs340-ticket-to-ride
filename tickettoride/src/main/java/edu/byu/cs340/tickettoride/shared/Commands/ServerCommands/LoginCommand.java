package edu.byu.cs340.tickettoride.shared.Commands.ServerCommands;

import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.Result.LoginResult;

public class LoginCommand implements ICommand {
    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;


    @Override
    public LoginResult execute() {
        return null;
    }
}
