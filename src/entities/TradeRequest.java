package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class TradeRequest extends Request implements Serializable {

    private String userA; // username of user who initiate the trade request (borrower in one way trade)
    private String userB; // username of user who gets the trade request (lander in one way trade)
    private List<Item> itemA ; // userA's items
    private List<Item> itemB ;  // userB's items
    private boolean perm;
    private LocalDateTime date;
    private String place = "";
    private int numberOfEditA = 3;
    private int numberOfEditB = 3;

    /**
     * Class constructor.
     * A message to a User from another user that asks them to make a decision on a trade request
     * @param content is the content of the message
     * @param sender is the sender's username
     * @param userA username of one of the user that is involved in the trade
     * @param userB username of the other user that is involved in the trade
     * @param itemA userA's items that userA is lending in this trade
     * @param itemB userB's items that userB is lending in this trade
     * @param perm whether the trade is permanent or not
     */
    public TradeRequest(String content, String sender, String userA, String userB, List<Item> itemA,
                        List<Item> itemB, boolean perm) {
        super(content, new String[]{"confirm", "deny", "edit"}, sender);
        this.userA = userA;
        this.userB = userB;
        this.itemA = itemA;
        this.itemB = itemB;
        this.perm = perm;
    }

    /**
     * get the userA involved in this trade request
     * @return userA
     */
    public String getUserA() {
        return userA;
    }

    /**
     * get the userA involved in this trade request
     * @return userB
     */
    public String getUserB() {
        return userB;
    }

    /**
     * get the items belongs to userA
     * @return list of items involved in this trade request belong to userA
     */
    public List<Item> getItemA() {
        return itemA;
    }
    /**
     * get the items belongs to userB
     * @return list of items involved in this trade request belong to userB
     */
    public List<Item> getItemB() {
        return itemB;
    }

    /**
     * returns if this a permanent trade request
     * @return true if this is a permanent trade request, flase if this is a temporary trade request
     */
    public boolean isPerm() {
        return perm;
    }

    /**
     * get the meeting date set by the user
     * @return the date of the meeting
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * get the place of the meeting
     * @return the place of the meeting
     */
    public String getPlace() {
        return place;
    }

    /**
     * get the number of edit for userA
     * @return the number of edit for userA
     */
    public int getNumberOfEditA() {
        return numberOfEditA;
    }

    /**
     * get the number of edit for userB
     * @return the number of edit for userB
     */
    public int getNumberOfEditB() {
        return numberOfEditB;
    }

    /**
     * set the date of the meeting
     * @param date date of the meeting
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * set the place of the meeting
     * @param place the place of the meeting
     */
    public void setPlace(String place) {
        this.place = place;
    }
    /**
     * set the number of edits for userA
     * @param numberOfEditA number of edit for userA
     */
    public void setNumberOfEditA(int numberOfEditA) {
        this.numberOfEditA = numberOfEditA;
    }

    /**
     * set the number of edits for userB
     * @param numberOfEditB number of edit for userB
     */
    public void setNumberOfEditB(int numberOfEditB) {
        this.numberOfEditB = numberOfEditB;
    }


    /**
     * Returns a string representation of the message
     * @return the content and trade request of the message in a string representation
     */

    @Override
    public String toString(){
        String info;
        StringBuilder itema = new StringBuilder();
        StringBuilder itemb = new StringBuilder();
        for (Item i : itemA){
            String item = i.getName() + ", ";
            itema.append(item);
        }
        for (Item i : itemB){
            String item = i.getName() + ", ";
            itemb.append(item);
        }
        if (itemA.isEmpty()){
            info =  "TraderA (Borrower): " + getUserA() +
                    "\nTraderB: " + getUserB() +
                    "\nItem from B: " + itemb.substring(0, itemb.length() - 2) +
                    "\nPlace: " + getPlace() +
                    "\nDate: " + getDate().toString();
        }
        else if (itemB.isEmpty()){
            info = "TraderA: " + getUserA() +
                    "\nTraderB (Borrower): " + getUserB() +
                    "\nItem from A: " +itema.substring(0, itema.length() - 2) +
                    "\nPlace: " + getPlace() +
                    "\nDate: " + getDate().toString();
        }
        else info = "TraderA: " + getUserA() +
                    "\nTraderB: " + getUserB() +
                    "\nItem from A: " + itema.substring(0, itema.length() - 2) +
                    "\nItem from B: " + itemb.substring(0, itemb.length() - 2) +
                    "\nPlace: " + getPlace() +
                    "\nDate: " + getDate().toString();

        if(perm) info = info + "\nTrade type: permanent trade";
        else info = info + "\nTrade type: temporary trade";
        return info;
    }
}
