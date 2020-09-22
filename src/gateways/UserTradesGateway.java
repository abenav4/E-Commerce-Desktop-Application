package gateways;

import entities.Trade;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTradesGateway {

    Map<String, List<Trade>> userTrades;

    /**
     * Creates a new gateway that loads in the HashMap of users and their respective trades for an .ser file.
     * @param filepath the directory where the .ser file is stored
     * @throws IOException If something is wrong with the filepath or file
     * @throws ClassNotFoundException If the class cannot be found
     */
    public UserTradesGateway(String filepath) throws IOException, ClassNotFoundException{
        File file = new File(filepath);
        if (file.exists()) {
            userTrades = readFromFile(filepath);
            if(userTrades == null) {
                userTrades = new HashMap<>();
            }
        } else {
            file.createNewFile();
            userTrades = new HashMap<>();
        }
    }
    /**
     * Deserializes the HashMap of usernames and their trades
     * @param filepath Filepath to the .ser file storing the usernames and their trades
     * @return the HashMap of usernames and their trades
     * @throws IOException If the file cannot be read
     * @throws ClassNotFoundException If the class cannot be found
     */
    public Map<String, List<Trade>> readFromFile(String filepath) throws IOException, ClassNotFoundException{
        Map<String, List<Trade>> userTrades2 = new HashMap<>();

        // load in the objects
        InputStream file = new FileInputStream(filepath);InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // deserialize the hashmap of user objects
        userTrades2 = (Map<String, List<Trade>>) input.readObject();
        input.close();
        return userTrades2;
    }

    /**
     * Serializes the HashMap of usernames and their trades
     * @param filepath where this file will be stored
     * @param userTrades3 HashMap of the trades for all users.
     * @throws IOException when an error occur when serializing
     */
    public void writeToFile(String filepath, Map<String, List<Trade>> userTrades3) throws IOException{
        // load allUsers onto the file at designed path
        FileOutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutputStream output = new ObjectOutputStream(buffer);

        // serialize objects
        userTrades = userTrades3;
        output.writeObject(userTrades3);
        output.close();
    }

    /**
     * returns the HashMap of trades for all users.
     * @return HashMap of trades for all users that is presently serialized.
     */
    public Map<String, List<Trade>> getUserTrades() {
        return userTrades;
    }
}
