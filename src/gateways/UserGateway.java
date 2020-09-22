package gateways;

import entities.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserGateway {
    private Map<String, User> mapOfUsers;

    /**
     * Creates a new gateway that loads in the HashMap of user objects for an .ser file.
     * @param filepath the directory where the .ser file is stored
     * @throws IOException If the path is not valid or an error happened when reading or writing the file
     * @throws ClassNotFoundException If the class cannot be found
     */
    public UserGateway(String filepath) throws IOException, ClassNotFoundException{
        File file = new File(filepath);
        if (file.exists()) {
            this.mapOfUsers = readFromFile(filepath);
        } else {
            file.createNewFile();
        }
        if(this.mapOfUsers == null) {
            this.mapOfUsers = new HashMap<>();
        }
    }
    /**
     * Deserializes the arraylist of user objects into the program.
     * @param filepath Filepath to the .ser file storing the User objects.
     * @return the hashmap of user objects
     * @throws IOException If the file cannot be read
     * @throws ClassNotFoundException If the class cannot be found
     */
    public Map<String, User> readFromFile(String filepath) throws IOException, ClassNotFoundException{
        Map<String, User> userObjects;
        // load in the objects
        InputStream file = new FileInputStream(filepath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // deserialize the hashmap of user objects
        userObjects = (Map<String, User>) input.readObject();
        input.close();
        return userObjects;
    }

    /**
     * Serializes the arraylist of user objects.
     * @param filepath where this file will be stored
     * @param userObjects HashMap with Users and their respective usernames that we want to serialize.
     * @throws IOException when an error occur when serializing
     */
    public void writeToFile(String filepath, Map<String, User> userObjects) throws IOException{
        // load allUsers onto the file at designed path
        FileOutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutputStream output = new ObjectOutputStream(buffer);

        // serialize objects
        this.mapOfUsers = userObjects;
        output.writeObject(userObjects);
        output.close();
    }

    /**
     * Returns the now deserialized map of user objects
     * @return the HashMap of user objects
     */
    public Map<String, User> getMapOfUsers() { return this.mapOfUsers; }
}
