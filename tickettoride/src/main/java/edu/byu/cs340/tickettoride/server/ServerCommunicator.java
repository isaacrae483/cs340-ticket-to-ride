package edu.byu.cs340.tickettoride.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;

import edu.byu.cs340.tickettoride.shared.Codec;

public class ServerCommunicator implements AutoCloseable {
    private HttpExchange exchange;
    private Writer responseBody;
    private Object responseData;
    private int rCode;

    public ServerCommunicator(HttpExchange exchange) {
        this.exchange = exchange;
        this.responseBody = new OutputStreamWriter(exchange.getResponseBody());
        this.responseData = null;
        this.rCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
    }

    String getRequestMethod() {
        return exchange.getRequestMethod();
    }

    Class getRequestBodyType() throws ClassNotFoundException {
        return Class.forName(exchange.getRequestHeaders().getFirst("Java-Class"));
    }

    <T> T getRequestBody(Class<T> type) {
        T result = null;

        try (Reader requestBody = new InputStreamReader(exchange.getRequestBody())) {
            result = Codec.SINGLETON.decode(requestBody, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    void success(Object data) {
        rCode = HttpURLConnection.HTTP_OK;
        responseData = data;
    }

    void error() {

    }

    @Override
    public void close() throws IOException {
        exchange.sendResponseHeaders(rCode, 0);

        if (responseData != null) {
            Codec.SINGLETON.encode(responseData, responseBody);
        }

        responseBody.close();
    }
}
