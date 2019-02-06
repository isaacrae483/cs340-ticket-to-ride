package edu.byu.cs340.tickettoride.Client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.byu.cs340.tickettoride.shared.Codec;

public class ClientCommunicator {

    private String serverUrl;
    private int port;

    public ClientCommunicator() {
    }

    public ClientCommunicator(String serverUrl, int port) {
        this.serverUrl = serverUrl;
        this.port = port;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public <T> T send(Object data, Class<T> returnType) {
        T result = null;

        try {
            URL url = new URL("http", serverUrl, port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (data == null) {
                connection.setRequestMethod("GET");
                connection.connect();
            } else {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                connection.setRequestProperty("Java-Class", data.getClass().getName());

                try (Writer requestBody = new OutputStreamWriter(connection.getOutputStream())) {
                    Codec.SINGLETON.encode(data, requestBody);
                }
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (Reader responseBody = new InputStreamReader(connection.getInputStream())) {
                    result = Codec.SINGLETON.decode(responseBody, returnType);
                }
            } else {
                throw new RuntimeException(connection.getResponseCode() + " " + connection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String ...args) throws Exception {
        String result = new ClientCommunicator("localhost", 8080).send(3, String.class);
        System.out.println(result);
    }

}
