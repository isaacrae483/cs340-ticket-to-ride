package FlatFilePlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FlatFileGameDAO extends FlatFileParentDAO implements GameDAO{
    private File gameDir;
    private File deltaDir;
    private int deltas;

    public FlatFileGameDAO(int deltas) {
        this.deltas = deltas;
        gameDir = new File("games");
        deltaDir = new File (gameDir + File.separator + "deltas");
        gameDir.mkdirs();
        deltaDir.mkdirs();
    }

    @Override
    public void updateGame(String data, String game, String id) {
        File deltaList = new File(deltaDir + File.separator + id);
        deltaList.mkdirs();
        if(deltaList.length() < deltas){
            try {
                this.write(data, deltaList + File.separator + deltaList.length());
            } catch(Exception e){
                e.printStackTrace();
            }
        } else {
            deltaList.delete();
            try {
                this.write(game, gameDir + File.separator + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteGame(String id) {
        new File(id).delete();
    }

    @Override
    public String getGame(String id) {
        String game = "";
        try{
            game = readFile(gameDir.toString() + File.separator + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    @Override
    public List<String> getCommands(String id) {
        List<String> commands = new ArrayList<>();
        File game = new File(deltaDir + File.separator + id);
        game.mkdirs();
        File[] listOfFiles = game.listFiles();
        try{
            for(int i = 0; i < listOfFiles.length; i++){
                commands.add(readFile(listOfFiles[i].getName()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return commands;
    }

    @Override
    public List<String> getIDs() {
        List<String> games = new ArrayList<>();
        File[] listOfFiles = gameDir.listFiles();
        try{
            for(int i = 0; i < listOfFiles.length; i++){
                if(!listOfFiles[i].getName().equals("deltas"))
                    games.add(listOfFiles[i].getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }
}
