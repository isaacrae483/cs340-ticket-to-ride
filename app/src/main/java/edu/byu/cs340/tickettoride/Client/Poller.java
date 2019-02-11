package edu.byu.cs340.tickettoride.Client;


import android.os.Handler;

import edu.byu.cs340.tickettoride.Client.ClientCommands.CommandFactory;
import edu.byu.cs340.tickettoride.shared.Commands.ClientCommandList;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class Poller {
    public Poller () {}
    private Handler mHandler;

    public void startPolling(Username username){
        mHandler = new Handler();
        poll(username);
    }
    private void poll(final Username username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    final ClientCommandList commandDataList = ServerProxy.instance().getCommands(username);
                    if (commandDataList == null)
                        continue;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < commandDataList.size(); i++) {
                                CommandFactory.Generate(commandDataList.get(i)).execute();
                            }
                        }
                    });
                }
            }
        }).start();
    }
}
