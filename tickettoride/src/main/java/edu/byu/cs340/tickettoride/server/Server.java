package edu.byu.cs340.tickettoride.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.DAOFactory;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.FlatFilePlugin.FlatFileFactory;
import edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin.SQLDAOFactory;

public class Server {
    public static final int DEFAULT_PORT = 8080;
    public static final int DEFAULT_MAX_CONNECTIONS = 10;

    private HttpServer server;

    public Server(int port, int backlog) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), backlog);
    }

    public Server setExecutor(Executor executor) {
        this.server.setExecutor(executor);
        return this;
    }

    public Server createContext(String path, HttpHandler handler) {
        this.server.createContext(path, handler);
        return this;
    }

    public void start() {
        this.server.start();
    }

    public static void main(String ...args) {

        try {
            if (args.length > 1) {
                DAOFactory daoFactory;

                if (args[0].equals("SQL")) {
                    daoFactory = new SQLDAOFactory();
                } else if (args[0].equals("FlatFile")){
                    daoFactory = new FlatFileFactory();
                } else {
                    System.out.println("INVALID ARGUMENT: {DAOFactory} must be \"SQL\" or \"FlatFile\"");
                    return;
                }

                int deltas = Integer.parseInt(args[1]);

                ServerModel.SINGLETON.AddFactory(daoFactory, deltas);
            }
            else {
                System.out.println("NOT ENOUGH ARGUMENTS: need {DAOFactory} {deltas}");
                return;
            }

            new Server(DEFAULT_PORT, DEFAULT_MAX_CONNECTIONS)
                    .setExecutor(null)
                    .createContext("/", new CommandHandler())
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
