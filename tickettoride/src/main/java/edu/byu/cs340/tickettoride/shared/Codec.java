package edu.byu.cs340.tickettoride.shared;

import com.google.gson.Gson;

import java.io.Reader;

public class Codec {
    public static final Codec SINGLETON = new Codec();

    private Gson gson;

    private Codec() {
        this.gson = new Gson();
    }

    public void encode(Object object, Appendable writer) {
        this.gson.toJson(object, writer);
    }

    public <T> T decode(Reader reader, Class<T> returnType) {
        return this.gson.fromJson(reader, returnType);
    }
}
