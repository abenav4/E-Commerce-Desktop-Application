package gateways;

import entities.GlobalInventory;
import use_cases.GlobalInventoryManager;

import java.io.*;

public class GlobalInventoryGateways implements Serializable{
    private String filePath;
    private GlobalInventory globalInventory;

    /**
     * Creates a new gateway that loads GlobalInventory in a .ser file\
     * @param filePath filePath of the ser file containing the serialized GlobalInventory
     * @throws IOException If something is wrong with the filepath or file
     * @throws ClassNotFoundException If the class cannot be found
     */
    public GlobalInventoryGateways(String filePath) throws IOException, ClassNotFoundException{
        this.filePath = filePath;

        File file = new File(filePath);
        if (file.exists()) {
            readFromFile();
            if(globalInventory == null) {
                globalInventory = new GlobalInventory();
            };
        } else {
            file.createNewFile();
            globalInventory = new GlobalInventory();

        }
    }

    /**
     * Deserializes the GlobalInventory object into the program.
     * will assign gI to the GlobalInventory that stored in the program.
     * @throws IOException If the file cannot be read
     * @throws ClassNotFoundException If the class cannot be found
     */
    public void readFromFile() throws IOException, ClassNotFoundException{
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // deserialize the Map
        globalInventory = (GlobalInventory) input.readObject();
        input.close();
    }

    /**
     * Serialize the GlobalInventory into .ser file
     * @param gi the GlobalInventory Object that we want to store in the .ser file
     * @throws IOException when an error occur when serializing
     */

    public void writeToFile(GlobalInventory gi) throws IOException{
        OutputStream file = new FileOutputStream(filePath);

        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        globalInventory = gi;
        output.writeObject(gi);
        output.close();
    }

    /**
     * Getter for GlobalInventory that stored in .ser file.
     * @return the globalInventory that stored in .ser file.
     */
    public GlobalInventory getGlobalInventory() {
        return globalInventory;
    }
}

