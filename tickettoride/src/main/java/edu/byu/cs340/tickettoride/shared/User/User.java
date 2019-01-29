package edu.byu.cs340.tickettoride.shared.User;

public class User{
    private Username username;
    private Password password;

    public User(Username userName, Password password) {
        this.username = userName;
        this.password = password;
    }

    public Username getUserName() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}