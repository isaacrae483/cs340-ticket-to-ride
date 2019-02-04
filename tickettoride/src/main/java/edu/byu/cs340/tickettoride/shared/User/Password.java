package edu.byu.cs340.tickettoride.shared.User;

public class Password{
    private String password;

    public Password(String password) throws InvalidPasswordException {
        qualifyPassword(password);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public class InvalidPasswordException extends Throwable{
        // Parameterless Constructor
        public InvalidPasswordException() {}

        // Constructor that accepts a message
        private InvalidPasswordException(String message)
        {
            super(message);
        }

    }

    private void qualifyPassword(String password) throws InvalidPasswordException {
        password = password.toLowerCase();
        if(!password.matches("[a-z0-9!@#$%^&*()][a-z0-9!@#$%^&*()]*")){
            throw new InvalidPasswordException("The password you have entered is invalid.");
        }

    }
}