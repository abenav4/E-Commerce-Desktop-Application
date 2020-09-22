package entities;

import java.io.Serializable;

public class NewItemRequest extends Request implements Serializable {
    private Item newItem;

    /**
     * Class constructor.
     * Creates a NewItemMessage with a content and an item that a user wants to add to the system
     * @param content is the content of the message
     * @param item is the new item created and belonging to the user
     */
    public NewItemRequest(String content, Item item) {
        super(content, new String[]{"Confirm", "Deny"}, item.getOwnerName());
        this.newItem = item;
    }

    /**
     * Getter for the new item that request looking at
     * @return the new item
     */
    public Item getNewItem(){
        return newItem;
    }

    /**
     * Returns a string representation of the message
     * @return the content and item of the message in a string representation
     */
    @Override
    public String toString() {
        return super.toString() +  "\nThe item: \n"+newItem;
    }
}
