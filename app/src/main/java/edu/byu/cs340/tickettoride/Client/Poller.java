package edu.byu.cs340.tickettoride.Client;


import android.content.PeriodicSync;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


import edu.byu.cs340.tickettoride.server.Model.CommandList;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandData;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Poller {
    public Poller () {}
    private List<ClientCommandData> commandList;


    public void startPolling(){

    }
    private void poll(Username username) {
        while (true) {
            commandList = (List<ClientCommandData>) ServerProxy.instance().getCommands(username);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getMessage();
                e.getStackTrace();
            }
        }
    }
    private void executeListResult(){

    }
}
