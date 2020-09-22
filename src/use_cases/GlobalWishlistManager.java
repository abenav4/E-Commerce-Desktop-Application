package use_cases;

import entities.GlobalWishlist;
import entities.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GlobalWishlistManager implements Serializable {

     // globalWishlist is the GlobalWishlist we want to modify.
    private GlobalWishlist globalWishlist;

    /**
     * constructor for the class
     * @param globalWishlist - The globalwishlist it takes in
     */
    public GlobalWishlistManager(GlobalWishlist globalWishlist) {
        this.globalWishlist = globalWishlist;
    }

    /**
     * Return if an item is wanted by anyone
     * @param itemid - id of item that is wanted
     * @return Whether or not anyone wants the item
     */
    public boolean isItemWanted(String itemid){
        return globalWishlist.isItemWanted(itemid);
    }

    /**
     * add the user's wish to the global wishlist
     * @param itemid - the item the user wants
     * @param userid - the user who wants it
     */

    public void addWish(String itemid, String userid) {
        globalWishlist.addWish(itemid, userid);
    }


    /**
     * remove the user's wish from the global wishlist
     * Make sure you ALWAYS call isItemWanted() before calling this else will error
     * @param itemid - the item the user wants to be removed from
     * @param userid - the user who wants it
     */

    public void removeWish(String itemid, String userid) {
        globalWishlist.removeWish(itemid, userid);
    }


    /**
     * Remove itemid key from globalwishlist
     * Make sure you ALWAYS call isItemWanted() before calling this else will error
     * @param itemid - id of item that you want removed
     */

    public void removeItem(String itemid) {
        globalWishlist.removeItem(itemid);
    }

    /**
     * Returns the itemid and userid if anyone wants a item in the given arraylist. else empty arraylist.
     * @param allItems - all the items a user has
     * @return an arraylist of 2 strings, the first string is the interested itemid, the second string is the first user
     * who wants the earliest item in the given inventory arraylist. If no match, return empty arraylist.
     */

    public List<String> userWhoWants (List<Item> allItems){
        List<String> interested = new ArrayList<>();
        for (Item allItem : allItems) {
            String currentItemid = allItem.getItemID();
            if (globalWishlist.isItemWanted(currentItemid)) {
                interested.add(currentItemid);
                interested.add(globalWishlist.getFirstInterestedUser(currentItemid));
                return interested;
            }
        }
        return interested;
    }

    /**
     * Return all items in the user's personal wishlist
     * Make sure you ALWAYS call isValidUser() before calling this.
     * @param userid id of user who's wishlist is wanted
     * @return arraylist of their wishlist
     */
    public List<String> getPersonWishlist(String userid){
        return globalWishlist.getPersonWishlist(userid);
    }

    public GlobalWishlist getGlobalWishlistData(){
        return globalWishlist;
    }

    /**
     * Return all itemids of the items that are in userB's wishlist
     * Make sure you ALWAYS call isValidUser() before calling this.
     * @param allItems items in question
     * @param userB the user in question
     * @return list of itemids of all the items that userB wants
     */
    public List<String> getInterestedItems(List<Item> allItems, String userB){
        return globalWishlist.getInterestedItems(allItems, userB);
    }

}

