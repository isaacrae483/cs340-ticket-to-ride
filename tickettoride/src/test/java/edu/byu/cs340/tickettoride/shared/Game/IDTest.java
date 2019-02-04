package edu.byu.cs340.tickettoride.shared.Game;

import java.util.HashSet;

import static org.junit.Assert.*;

public class IDTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getId() {
    }

    @org.junit.Test
    public void generate() {
        HashSet<ID> ids = new HashSet<>();
        final int numTests = 1000;
        for (int i = 0; i < 1000; ++i) {
            //because using internal strings as hash code, verifies that there are no duplicates
            assertTrue(ids.add(ID.generate()));
        }

    }
}