package binSerialization;

import java.io.*;

public class BinSerialization {
    public void saveObject(Serializable object, String filename) throws IOException {
        ObjectOutputStream objstream = new ObjectOutputStream(new FileOutputStream(filename));

        // The writeObject() method automatically transforms the contents of the object to bytes.
        // An error is generated if the object does not implement the Serialize interface
        objstream.writeObject(object);
        objstream.close();
    }

    // Deserializes the object stored in the provied path and returns this
    // object without any casting it to a specific type
    public static Object loadObject(String filename) throws ClassNotFoundException, IOException {
        ObjectInputStream objstream = new ObjectInputStream(new FileInputStream(filename));

        // Read the bytes and creates the object in memory
        Object object = objstream.readObject();
        objstream.close();

        return object;
    }
}
