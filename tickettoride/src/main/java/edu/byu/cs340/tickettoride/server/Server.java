package edu.byu.cs340.tickettoride.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.Executor;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.DAOFactory;
//import edu.byu.cs340.tickettoride.shared.Interface.Plugin.SQLPlugin.SQLDAOFactory;

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
            if (args.length > 3) {
                DAOFactory daoFactory = null;
                try {
                    File pluginJarFile = new File(args[0], args[1]);
                    URL pluginURL = pluginJarFile.toURI().toURL();
                    URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

                    // Load the jar file's plugin class, create and return an instance
                    Class<? extends DAOFactory> messagePluginClass = (Class<DAOFactory>) loader.loadClass(args[2]);
                    daoFactory = messagePluginClass.getDeclaredConstructor(null).newInstance();


                    int deltas = Integer.parseInt(args[3]);

                    ServerModel.SINGLETON.AddFactory(daoFactory, deltas);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Usage: java Application pluginDirectory pluginJarFileName pluginClassName deltas");
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
