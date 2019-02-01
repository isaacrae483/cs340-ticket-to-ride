package edu.byu.cs340.tickettoride.shared.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPassword() throws Exception {
        try{
            Password test2 = new Password("ASDF");
            Password test1 = new Password("ASDF!@#$qwer$%&^");
        }catch(Throwable t){
            t.getMessage();
        }
    }
}