package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class GlobalInventory implements Serializable {

    private Map<String, Item> itemMap;
    private List<String> itemIdCollection;
    private List<Item> removedItems;
    /**
     * Create a HashMap to store the information of item within the GlobalInventory
     * Crease an ArrayList to store all the ID that has been assigned to Item.
     * The constructor will be called with no parameter and automatically construct an empty HashMap
     * and an empty ArrayList.
     */


    public GlobalInventory(){
        removedItems = new ArrayList<>();
        itemMap = new HashMap<>();
        itemIdCollection = new ArrayList<>();
    }

    /**
     * Add ItemID to ItemIdCollection
     * @param itemID is the unique id each item has.

     */

    public void addItemIdToCollection(String itemID){
        itemIdCollection.add(itemID);
    }


    /**
     * getter for the Arraylist of itemID that have ever existed in the program.
     * @return the ArrayList of all ID that has ever been assigned to Item
     */


    public List<String> getItemIdCollection() {
        return itemIdCollection;
    }

    /**
     * Getter of Item in GlobalInventory with ItemID been called
     * @param itemID is the unique ID of each Item.

     * @return Item with itemID been called
     * and return null if the itemID is not in the GlobalInventory
     */

    public Item getItem(String itemID) {

            return itemMap.get(itemID);


    }

    /**
     * Setter of Item in GlobalInventory with itemID as key and Item as what key refers to
     * @param itemID is the key for itemMap
     * @param item is what itemID refers to in itemMap
     */

    public void addItem(String itemID, Item item){
        itemMap.put(itemID, item);
    }

    /**
     * Remove item with specific itemID and add to removedItems
     * @param itemID is the key for itemMap.

     */

    public void removeItemAndAddToGarbageBin(String itemID){
        removedItems.add(itemMap.get(itemID));
        itemMap.remove(itemID);
    }

    /**
     * Remove item with specific itemID
     * @param itemID is the key for itemMap.

     */

    public void removeItemOnly(String itemID){
        itemMap.remove(itemID);
    }

    /**
     * Undos the last delete of an item by user
     * @param userid The user who's last deleted item will be brought back
     */
    public void undoDeleteItem(String userid){
        for (int j = removedItems.size() - 1; j >= 0; j--) {
            if (removedItems.get(j).getOwnerName().equals(userid)){
                Item restore = removedItems.get(j);
                itemMap.put(restore.getItemID(), restore);
                removedItems.remove(restore);
                break;
            }

        }
    }

    /**
     * @param itemID is the itemID that needed to be checked about its existence in itemMap
     * @return true if the itemID exists in the itemMap.
     */

    public boolean containsKey(String itemID){
        return itemMap.containsKey(itemID);
    }

    /**
     * method for search engine that if the user want ot search the items that belongs to a specific person.
     * @param ownerName is the owner name the user wants to search in GlobalInventory
     * @return an arraylist of Item belongs to the User with specific ownerName within GlobalInventory
     */

    public List<Item> searchByOwnerName(String ownerName){
        ArrayList<Item> personalInventory = new ArrayList<>();
        for (int i = 0; i < itemMap.size(); i++){
            Set<String> keys = itemMap.keySet();
            String f = new ArrayList<>(keys).get(i);
            if (itemMap.get(f).getOwnerName().equals(ownerName)){
                personalInventory.add(itemMap.get(f));
            }
        }
        return personalInventory;
    }


    /**
     * change the String representation of GlobalInventory.
     * @return String of global's content.
     */


    public String toString() {
        StringBuilder i = new StringBuilder();
        for (String key : itemMap.keySet()) {
            i.append(key).append(itemMap.get(key)).append("\n");
        }
        // from https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
        return i.toString();
    }

    /**
     * return true if the globalInventory has no item in it currently
     * @return true if the globalInventory has no item in it currently
     */


    public boolean isEmpty(){
        return itemMap.size() == 0;
    }

}
