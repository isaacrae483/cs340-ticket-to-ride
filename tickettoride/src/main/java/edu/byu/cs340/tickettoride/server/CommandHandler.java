package edu.byu.cs340.tickettoride.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandData;
import edu.byu.cs340.tickettoride.shared.Commands.ServerCommandFactory;

public class CommandHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try (ServerCommunicator communicator = new ServerCommunicator(httpExchange)) {
            if (communicator.getRequestMethod().equals("POST")) {
                Object o = communicator.getRequestBody();

                if (o instanceof ServerCommandData) {
                    Object result = ServerCommandFactory.Generate((ServerCommandData) o);
                    communicator.success(result);
                } else {
                    System.out.println(o.getClass());
                    communicator.error();
                }
            }
        }
    }
}
