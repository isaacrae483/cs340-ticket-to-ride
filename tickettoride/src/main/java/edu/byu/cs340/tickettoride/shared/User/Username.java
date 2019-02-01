package edu.byu.cs340.tickettoride.shared.User;

public class Username{
    private String username;

    public Username(String username) {
        try {
            qualifyUsername(username);
            this.username = username;
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    private class InvalidUserNameException extends Throwable{
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