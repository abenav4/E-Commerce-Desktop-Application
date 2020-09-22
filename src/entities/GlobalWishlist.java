package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalWishlist implements Serializable {
    private Map<String, List<String>> wishMap;

    /*
    * Constructor just creates a new empty hashmap.
    */
    public GlobalWishlist(){
        wishMap = new HashMap<>();
    }


     /**
     * Add an user to list of users wanting an item. If no users want it yet, create item key and add user.
      * Make sure you ALWAYS call isItemWanted() before calling this else will error
      * @param itemid - id of item that is wanted
     * @param userid - id of user that wants the item
     */

    public void addWish(String itemid, String userid){
        if (wishMap.get(itemid) == null) {
            wishMap.put(itemid, new ArrayList<>());
        }
        wishMap.get(itemid).add(userid);
    }


     /**
     * Remove an user from wanting an item. If after removing, no users want the item, delete item key.
     * Make sure you ALWAYS call isItemWanted() before calling this else will error
      *  @param itemid - id of item that is wanted
     * @param userid - id of user that wants the item
     */

    public void removeWish(String itemid, String userid){
        wishMap.get(itemid).remove(userid);
        if (wishMap.get(itemid).isEmpty()){
            wishMap.remove(itemid);
        }
    }

    /**
     * Remove itemid key from globalwishlist
     * Make sure you ALWAYS call isItemWanted() before calling this else will error
     * @param itemid - id of item that you want removed
     */

    public void removeItem(String itemid) {
        if (wishMap.get(itemid) != null) {
            wishMap.remove(itemid);
        }
    }


     /**
     * Return if an item is wanted by anyone
     * @param itemid - id of item that is wanted
      * @return Whether or not anyone wants the item
     */
    public boolean isItemWanted(String itemid){
        return wishMap.containsKey(itemid);
    }


    /**
     * Return first userid who wants the item
     * @param itemid - id of item that is of interest
     * @return first userid of whoever wants the item
     */
    public String getFirstInterestedUser(String itemid){
        return wishMap.get(itemid).get(0);
    }

    /**
     * Return all userids that wants the item
     * Make sure you ALWAYS call isItemWanted() before calling this.
     * @param itemid - id of first user that wants the item
     * @return - arraylist of all users who want this item
     */
    public List<String> getAllInterestedUsers(String itemid){
        if (isItemWanted(itemid)) {
            return wishMap.get(itemid);
        }
        else { return new ArrayList<>();}
    }

    /**
     * Return all userids that wants every item
     * @return - A string representation of all users who wants all items
     */
    @Override
    public String toString(){
        String i = "";
        for (String key : wishMap.keySet()) {
            i += key + wishMap.get(key)+ "\n";
        }
        // from https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
        return i;
    }


    /**
     * Return all items in the user's personal wishlist
     * Make sure you ALWAYS call isValidUser() before calling this.
     * @param userid id of user who's wishlist is wanted
     * @return arraylist of their wishlist
     */
    public List<String> getPersonWishlist(String userid){
        List<String> wishlist = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : wishMap.entrySet()) {
            String item = entry.getKey();
            List<String> interestedusers = entry.getValue();
            if (interestedusers.contains(userid)){
                wishlist.add(item);
            }
            }
        return wishlist;
        }

    /**
     * Return all itemids of the items that are in userB's wishlist
     * Make sure you ALWAYS call isValidUser() before calling this.
     * @param allItems items in question
     * @param userB the user in question
     * @return list of itemids of all the items that userB wants
     */
    public List<String> getInterestedItems(List<Item> allItems, String userB){
        List<String> interested = new ArrayList<>();
        for (Item allItem : allItems) {
            String currentItemid = allItem.getItemID();
            if (wishMap.containsKey(currentItemid)) {
                if (wishMap.get(currentItemid).contains(userB)){ interested.add(currentItemid); }
            }
        }
        return interested;
    }
}

