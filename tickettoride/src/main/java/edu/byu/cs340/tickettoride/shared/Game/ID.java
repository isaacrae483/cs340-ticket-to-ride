package edu.byu.cs340.tickettoride.shared.Game;

public class ID{
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
}