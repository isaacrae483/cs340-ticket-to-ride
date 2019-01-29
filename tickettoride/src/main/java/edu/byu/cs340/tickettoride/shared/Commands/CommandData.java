package edu.byu.cs340.tickettoride.shared.Commands;

import edu.byu.cs340.tickettoride.shared.User.Username;

public class CommandData {
    private int commandType;//this should be an enum instead
    private Username username;
    private String param1;
}
