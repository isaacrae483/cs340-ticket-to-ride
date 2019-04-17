package com.example.allplugins.SQLPlugin;

import java.util.Map;

public interface UserDAO {
    void register(String username, String password);
    Map<String, String> getUsers();
    void updateCommandQueue(String username, String commandList);
    String getCommands(String username);

}
