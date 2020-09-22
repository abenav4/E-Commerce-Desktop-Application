package gateways;

import entities.Admin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AdminAccountGateways {
    private String filePath;
    private Map<String, Admin> adminMap = new HashMap<>();

    /**
     * create a gateways that loads a HashMap of Admin with UserName as key.
     * @param filePath the directory where the .ser file is stored
     * @throws IOException If something is wrong with the filepath or file
     * @throws ClassNotFoundException If the class cannot be found
     */

    public AdminAccountGateways(String filePath) throws IOException, ClassNotFoundException{
        this.filePath = filePath;
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile();
            if(adminMap == null){
                adminMap = new HashMap<>();
            }
        } else {
            file.createNewFile();
            adminMap = new HashMap<>();
        }
    }

    /**
     * Deserializes the hashmap of Admin into the program.
     * will assign adminMap to the hashmap which stores Admin with UserName as key
     * @throws IOException If the file cannot be read
     * @throws ClassNotFoundException If the class cannot be found
     */
    public void readFromFile() throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        adminMap = (Map<String, Admin>) input.readObject();
        input.close();
    }

    /**
     * Serialize the HashMap of admin into .ser file
     * @param adminMap the HashMap we want to use to store AdminAccountInformation
     * @throws IOException when an error occur when serializing
     */

    public void saveToFile(Map<String, Admin> adminMap) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        this.adminMap = adminMap;
        output.writeObject(adminMap);
        output.close();
    }

    /**
     * getter for HashMap of Admin that stored in .ser file.
     * @return the HashMap of Admin that stored in .ser file.
     */

    public Map<String, Admin> getAdminMap() {
        return adminMap;
    }

    /**
     * setter for HashMap of Admin with an initial Admin
     */
    public void beginAdminMap() {
        adminMap = new HashMap<>();
        Admin temp = new Admin("admin", "admin");
        adminMap.put("admin", temp);
    }

}
