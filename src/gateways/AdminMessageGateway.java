package gateways;

import entities.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminMessageGateway {
   private List<Message> messages;

    /**
     * Creates a new gateway that loads in the Arraylist of Message objects in a .ser file
     * @param filepath the directory where the .ser file is stored
     * @throws IOException If something is wrong with the filepath or file
     * @throws ClassNotFoundException If the class cannot be found
     */
    public AdminMessageGateway(String filepath) throws IOException, ClassNotFoundException {
        File file = new File(filepath);
        if (file.exists()) {
            this.messages = readFromFile(filepath);
            if (messages == null) {
                messages = new ArrayList<Message>();
            }

        } else {
            file.createNewFile();
            messages = new ArrayList<Message>();
        }
    }
    /**
     * Deserializes the arraylist of user objects into the program.
     * @param filepath Filepath to the .ser file storing the User objects.
     * @return the arraylist of Messages an Admin can respond to.
     * @throws IOException If the file cannot be read
     * @throws ClassNotFoundException If the class cannot be found
     */
    public List<Message> readFromFile(String filepath) throws IOException, ClassNotFoundException{
        List<Message> messages2;
        // load in the objects
        InputStream file = new FileInputStream(filepath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // deserialize the hashmap of user objects
        messages2 = (List<Message>) input.readObject();
        input.close();
        return messages2;
    }

    /**
     * Serializes the arraylist of Message objects.
     * @param filepath where this file will be stored
     * @param adminMessages ArrayList of Messages that the Admin must still respond to
     * @throws IOException when an error occur when serializing
     */
    public void writeToFile(String filepath, List <Message> adminMessages) throws IOException {
        // load allUsers onto the file at designed path
        FileOutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutputStream output = new ObjectOutputStream(buffer);

        // serialize objects
        messages = adminMessages;
        output.writeObject(adminMessages);
        output.close();
    }

    /**
     * Returns all messages of the logged in Admin.
     * @return return messages presently saved in the .ser file of a particular Admin.
     */
    public List<Message> getMessages() { return this.messages; }
}
