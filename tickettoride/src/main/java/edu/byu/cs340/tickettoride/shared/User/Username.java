package edu.byu.cs340.tickettoride.shared.User;

import java.util.Objects;

public class Username{
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return username;
    }

    public Username(String username) throws InvalidUserNameException {
        qualifyUsername(username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public class InvalidUserNameException extends Throwable{
        // Parameterless Constructor
        public InvalidUserNameException() {}

        // Constructor that accepts a message
        private InvalidUserNameException(String message)
        {
            super(message);
        }
    }

    private void qualifyUsername(String username) throws InvalidUserNameException{
        username = username.toLowerCase();
        if(!username.matches("[a-z0-9!@#$%^&*()][a-z0-9!@#$%^&*()]*")){
            throw new InvalidUserNameException("The username you have entered is invalid.");
        }

    }
}