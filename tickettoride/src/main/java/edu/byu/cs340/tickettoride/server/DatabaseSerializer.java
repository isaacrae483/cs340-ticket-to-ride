package edu.byu.cs340.tickettoride.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;


public class DatabaseSerializer {

    public static Object fromString(String s) {
        try {
                byte [] data = Base64.getDecoder().decode( s );
                ObjectInputStream ois = new ObjectInputStream(
                        new ByteArrayInputStream(  data ) );
                Object o  = ois.readObject();
                ois.close();
                return o;
        }
        catch (IOException|ClassNotFoundException e) {
            return null;
        }
    }

    public static String toString(Serializable o) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( o );
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }
        catch (IOException e) {
            return null;
        }
    }
}
