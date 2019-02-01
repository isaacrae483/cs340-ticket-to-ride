package edu.byu.cs340.tickettoride.shared.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void user() throws Exception{
        try {
            User test3 = new User(new Username("ASDF1!#@$3457asdflj"), new Password(""));
            User test2 = new User(new Username(""), new Password("ASDF"));
            User test1 = new User(new Username("HelloWorld"), new Password("Password1235!@#$"));
        } catch (Throwable t){
            t.getMessage();
        }
    }

}