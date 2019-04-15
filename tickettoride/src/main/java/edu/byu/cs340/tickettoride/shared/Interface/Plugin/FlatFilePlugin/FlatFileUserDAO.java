package edu.byu.cs340.tickettoride.shared.Interface.Plugin.FlatFilePlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.Interface.Plugin.UserDAO;

public class FlatFileUserDAO extends FlatFileParentDAO implements UserDAO {


    private File userDir;
    private File commandDir;

    public FlatFileUserDAO() {
        userDir = new File("users");
        userDir.mkdirs();

        commandDir = new File("userCommands");
        commandDir.mkdirs();
    }

    @Override
    public void register(String username, String password) {
        try {
            this.write(password, userDir.getName() + File.separator + username);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getUsers() {
        Map<String, String> result = new HashMap<>();
        try {
            File[] files = userDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    result.put(file.getName(), this.readFile(userDir + File.separator + file.getName()));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public void updateCommandQueue(String username, String commandList) {
        try {
            this.write(commandList, commandDir.getName() + File.separator + username);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCommands(String username) {
        String res = null;
        try {
            res = this.readFile(commandDir + File.separator + username);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
