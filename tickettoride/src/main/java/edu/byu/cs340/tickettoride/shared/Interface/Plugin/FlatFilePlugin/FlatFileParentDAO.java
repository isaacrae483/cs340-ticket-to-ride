package edu.byu.cs340.tickettoride.shared.Interface.Plugin.FlatFilePlugin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FlatFileParentDAO {
    protected void write(String data, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter("filename.txt")) {
            out.println(data);
        }
    }

    protected String readFile(String filename) throws IOException {
        String fileName;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }
}
