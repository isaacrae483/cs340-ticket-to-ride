package edu.byu.cs340.tickettoride.Client;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.byu.cs340.tickettoride.shared.Codec;

public class ClientCommunicator {

    private URL url;

    public ClientCommunicator() {
    }

    public ClientCommunicator(URL url) {
        setURL(url);
    }

    public void setURL(URL url) {
        this.url = url;
    }

    public <T> T send(Object data, Class<T> returnType) {
        T result = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (data == null) {
                return null;
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
//                    BufferedReader reader = new BufferedReader(responseBody);
//                    String line;
//                    while (reader.ready()) {
//                        Log.d("Reader", reader.readLine());
//                    }
//                    reader.reset();
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


    public <T> T get(Object data, Class<T> returnType) {
        T res = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            StringBuilder dataString = new StringBuilder();
            Codec.SINGLETON.encode(data, dataString);
            connection.setRequestProperty("Data", dataString.toString());
            connection.setRequestProperty("Java-Class", data.getClass().getName());

            connection.connect();
            try (Reader responseBody = new InputStreamReader(connection.getInputStream())) {
                res = Codec.SINGLETON.decode(responseBody, returnType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String ...args) throws Exception {
        String result = new ClientCommunicator(new URL ("http","localhost", 8080, "/"))
                .send(3, String.class);
        System.out.println(result);
    }

}
