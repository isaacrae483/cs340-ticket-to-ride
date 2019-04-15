package edu.byu.cs340.tickettoride.server.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.Interface.ICommand;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class CommandList implements Serializable {
    private Map<Username, ClientCommandList> commands;

    public CommandList() {
        commands = new HashMap<>();
    }

    public ClientCommandList GetCommandsNoClear(Username username) {
        if (!commands.containsKey(username)) {
            return new ClientCommandList();
        }
        ClientCommandList result = commands.get(username);
        return result;
    }

    public ClientCommandList GetCommands(Username user) {
        ClientCommandList result = GetCommandsNoClear(user);
        commands.remove(user);
        return result;
    }

    public void AddCommand(Username user, ClientCommandData command) {
        if (!commands.containsKey(user)) {
            commands.put(user, new ClientCommandList());
        }
        commands.get(user).add(command);
    }

    public void AddCommands(Username user, ClientCommandList list) {
        commands.put(user, list);
    }
}
