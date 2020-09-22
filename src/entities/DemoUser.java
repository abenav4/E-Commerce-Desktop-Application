package entities;

import java.util.ArrayList;
import java.util.List;

public class DemoUser extends Account {
    private List<Item> personalInventory = new ArrayList<>();
    private List<Item> personalWishlist = new ArrayList<>();

    /**
     * Constructs a new DemoUser account.
     * @param username string username
     * @param password string password
     */
    public DemoUser(String username, String password){
        super(username, password);
    }

    /**
     * Return the personal inventory
     * @return the List of this DemoUser's personal inventory
     */
    public List<Item> getPersonalInventory() { return this.personalInventory; }

    /**
     * Get the wishlist
     * @return the list of this user's personal wishlist
     */
    public List<Item> getWishlist() { return this.personalWishlist; }


    /**
     * Add an item to this demo user's inventory
     * @param item the item to be added
     */
    public void addPersonalInventory(Item item) { this.personalInventory.add(item); }

    /**
     * Add an item to this user's wishlist
     * @param item Item to be added
     */
    public void addWishlist(Item item) { this.personalWishlist.add(item); }

}
