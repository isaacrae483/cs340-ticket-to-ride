package edu.byu.cs340.tickettoride.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
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

    Object getRequestBody() {
        Object result = null;

        try (Reader requestBody = new InputStreamReader(exchange.getRequestBody())) {
            Class type = Class.forName(exchange.getRequestHeaders().getFirst("Java-Class"));
            result = Codec.SINGLETON.decode(requestBody, type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    Object getHeaderData() {
        Object o = null;
        try {
            StringReader userHeader = new StringReader(exchange.getRequestHeaders().getFirst("Data"));
            Class type = Class.forName(exchange.getRequestHeaders().getFirst("Java-Class"));
            o = Codec.SINGLETON.decode(userHeader, type);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
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
