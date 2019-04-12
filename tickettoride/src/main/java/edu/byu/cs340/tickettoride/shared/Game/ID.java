package edu.byu.cs340.tickettoride.shared.Game;

import java.io.Serializable;
import java.util.Objects;

public class ID implements Serializable {
    private String id;

    //note: private. You should only get an ID by calling the generate method below.
    private ID(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private static int next = 0;
    public static ID generate() {
        ++next;
        ID res = new ID("ID-" + next);
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id1 = (ID) o;
        return this.id.equals(id1.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}