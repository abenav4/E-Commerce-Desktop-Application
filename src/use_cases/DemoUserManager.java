package use_cases;

import entities.DemoUser;
import entities.Item;

import java.util.List;

public class DemoUserManager {

    private DemoUser demoUser;

    /**
     * Constructs a new DemoUserManager and stores an instance of DemoUser as an instance variable
     * @param username the string username
     * @param password the string password
     */
    public DemoUserManager(String username, String password) {
        this.demoUser = new DemoUser(username, password);
    }


    /**
     * Get this demo user's inventory
     * @return return this user's inventory
     */
    public List<Item> getUserInventory() { return this.demoUser.getPersonalInventory(); }

    /**
     * Return this user's wishlist
     * @return return this user's wishlist
     */
    public List<Item> getUserWishlist() { return this.demoUser.getWishlist(); }

    /**
     * Add to the wishlist
     * @param item the item to be added
     */
    public void addDemoWishlist(Item item) {
        this.demoUser.addWishlist(item);
    }


    /**
     * Add an item to the user's personal inventory
     * @param item the item to be added
     */
    public void addToInventory(Item item) {
        this.demoUser.addPersonalInventory(item);
    }


    /**
     * Set a new password
     * @param password Sets a new password
     */
    public void setPassword(String password) { demoUser.setPassword(password); }
}
