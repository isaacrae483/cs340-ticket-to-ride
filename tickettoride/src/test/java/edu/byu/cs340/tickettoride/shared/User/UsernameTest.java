package edu.byu.cs340.tickettoride.shared.User;

import static org.junit.Assert.*;

public class UsernameTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getUsername() throws Exception {
        try {
            Username test6 = new Username("AAAA");
            Username test5 = new Username("!@#$%^&*()"); //should be successful
            Username test4 = new Username(""); //throw exception
            Username test3 = new Username("a"); //should be successful
            Username test2 = new Username(" "); //throw exception
            Username test1 = new Username("briansw2"); //should be successful
        }catch(Throwable t){
            t.getMessage();
        }
    }
}