package use_cases;


import entities.Item;
import entities.GlobalInventory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GlobalInventoryManager implements Serializable {

    // gI is the GlobalInventory we want to modify.
    private GlobalInventory globalInventory;

    /**
     * construct the Use Case class to do some changes on globalinventory.
     *
     * @param globalInventory - the globalInventory it takes in
     */
    public GlobalInventoryManager(GlobalInventory globalInventory) {
        this.globalInventory = globalInventory;
    }

    /**
     * getter to get the Item from GlobalInventory with itemID.
     * @param itemID is the unique id that each item has.
     * @return the Item with itemID and return nothing if ID doesn't exist in GlobalInventory
     */


    public Item getItemFromGI(String itemID){
        return (Item) globalInventory.getItem(itemID);
    }


    public List<Item> getItemsFromGI(ArrayList<String> itemIDList){
        List<Item> newList = new ArrayList<>();
        if (itemIDList.size() == 0){
            return newList;
        }
        for (String s : itemIDList) {
            newList.add(globalInventory.getItem(s));
        }
        return newList;
    }


    private String IdGenerator() {
        Random rand = new Random();
        int id = rand.nextInt(900000000) + 100000000;
        String ID = Integer.toString(id);
        while (globalInventory.getItemIdCollection().contains(ID)) {
            id = rand.nextInt(900000000) + 100000000;
            ID = Integer.toString(id);
        }
        return ID;
    }


    /**
     * add the item to globalInventory with an unique Id generated automatically
     * The ID generated will be assigned to the Item
     * and then the that ItemID will be sent to IdCollection to record
     *
     * @param item set what the key refers to in globalInventory
     */

    public void addItemToHashMap(Item item) {

        if (!item.hasID()) {
            globalInventory.addItem(item.getItemID(), item);
        }
        else {

            String itemID = IdGenerator();

            item.setItemID(itemID);
            globalInventory.addItemIdToCollection(itemID);

            globalInventory.addItem(itemID, item);


        }
    }


    /**
     * remove the item with specific itemID and adds item to garbage bin
     *
     * @param itemID is the itemID of the item we want to remove
     */

    public void removeItemAndAddToGarbageBin(String itemID) {
        globalInventory.removeItemAndAddToGarbageBin(itemID);
    }

    /**
     * Undos the last delete of an item by user
     * @param userid The user who's last deleted item will be brought back
     */
    public void undoDeleteItem(String userid){
        globalInventory.undoDeleteItem(userid);
    }

    /**
     * Remove item with specific itemID
     * @param itemID is the key for itemMap.

     */

    public void removeItemOnly(String itemID){
        globalInventory.removeItemOnly(itemID);
    }


    /**
     * generate an arraylist of Item belongs to the specific owner
     * @param ownerName is the name of item the user want to search
     * @return an arraylist of Item belongs to the specific owner
     */
    public List<Item> getPersonInventory (String ownerName){ return globalInventory.searchByOwnerName(ownerName); }

    /**
     * returns whether the global inventory contains an item
     *
     * @param item is the name of item the user want to search
     * @return whether the item is in the global inventory
     */
    public boolean contains(Item item) {
        return globalInventory.containsKey(item.getItemID());
    }


    /**
     * return if gI has no Item in it.
     *
     * @return true if gI has no Item in it.
     */


    public boolean hasNoItem() {
        return globalInventory.isEmpty();

    }


    public GlobalInventory getGlobalInventoryData(){
        return globalInventory;
    }
}


