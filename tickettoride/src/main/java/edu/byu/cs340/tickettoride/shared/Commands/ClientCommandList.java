package edu.byu.cs340.tickettoride.shared.Commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientCommandList implements Serializable {
    private List<ClientCommandData> data = new ArrayList<>();

    public ClientCommandData get(int index) {
        return data.get(index);
    }

    public void add(ClientCommandData item) {
        data.add(item);
    }

    public int size() {
        return data.size();
    }
}
