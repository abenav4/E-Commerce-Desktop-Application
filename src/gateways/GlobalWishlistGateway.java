package gateways;

import entities.GlobalWishlist;

import java.io.*;
public class GlobalWishlistGateway implements Serializable{

    GlobalWishlist wishlist;

    /**
     * Creates a new gateway that loads in the GlobalWishlist stored in a .ser file
     * @param filepath the directory where the .ser file is stored
     * @throws IOException If something is wrong with the filepath or file
     * @throws ClassNotFoundException If the class cannot be found
     */
    public GlobalWishlistGateway(String filepath)  throws IOException, ClassNotFoundException{
        File file = new File(filepath);
        if (file.exists()) {
            wishlist = readFromFile(filepath);
            if(wishlist == null) {
                wishlist = new GlobalWishlist();
            }
        } else {
            file.createNewFile();
            wishlist = new GlobalWishlist();
        }
    }
    /**
     * Deserializes the contents of the GlobalWishlist that is serialized.
     * @param filepath Filepath to the .ser file storing the GlobalWishlist
     * @return the deserialized GlobalWishlist
     * @throws IOException If the file cannot be read
     * @throws ClassNotFoundException If the class cannot be found
     */
    public GlobalWishlist readFromFile(String filepath) throws IOException, ClassNotFoundException{
        GlobalWishlist wishItems;

        // load in the objects
        InputStream file = new FileInputStream(filepath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // deserialize the hashmap of user objects
        wishItems = (GlobalWishlist) input.readObject();
        input.close();
        return wishItems;
    }

    /**
     * Serializes the arraylist of Message objects.
     * @param filepath where this file will be stored
     * @param wishlistItems2 new GlobalWishList to overwrite previous one during saving.
     * @throws IOException when an error occur when serializing
     */
    public void writeToFile(String filepath, GlobalWishlist wishlistItems2) throws IOException{
        // load allUsers onto the file at designed path
        FileOutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutputStream output = new ObjectOutputStream(buffer);

        // serialize objects
        wishlist = wishlistItems2;
        output.writeObject(wishlistItems2);
        output.close();
    }

    /**
     * Returns WishListItems which is shared between all users.
     * @return returns GlobalWishList presently saved in serialized file.
     */
    public GlobalWishlist getWishlistItems() {
        return wishlist;
    }

}
