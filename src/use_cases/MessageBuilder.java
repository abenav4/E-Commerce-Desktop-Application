package use_cases;
import entities.*;

import java.util.List;

public class MessageBuilder {

    /**
     * Return a ContentMessage with a specified user.
     * @param content the String content
     * @param sender the String username
     * @return the ContentMessage
     */
    public Message getPrivateMessage(String content, String sender){
        return new PrivateMessage(content, sender);
    }

    /**
     * Return a ContentMessage sent by the system.
     * @param content the String content
     * @return the ContentMessage message
     */
    public Message getSystemMessage(String content){
        return new SystemMessage(content);
    }

    /**
     * Return a new ItemRequestObject
     * @param content the String content
     * @param item the Item object to be approved/denied
     * @return the ItemRequest message
     */
    public Message getNewItemRequest(String content, Item item){
        return new NewItemRequest(content, item);
    }

    /**
     * Return a new FreezeRequest object
     * @param content the String content
     * @param username the String username of the user to be frozen
     * @return the FreezeRequest message
     */
    public Message getFreezeRequest(String content, String username){
        return new FreezeRequest(content, username);
    }

    /**
     * Return a new TradeRequest object
     * @param content is the content of the message
     * @param sender is the sender's username
     * @param userA username of one of the user that is involved in the trade
     * @param userB username of the other user that is involved in the trade
     * @param itemA userA's items that userA is lending in this trade
     * @param itemB userB's items that userB is lending in this trade
     * @param perm whether the trade is permanent or not
     * @return a new TradeRequest message
     */
    public Message getTradeRequest(String content, String sender, String userA, String userB,  List<Item> itemA,
                                   List<Item> itemB, boolean perm){
        return new TradeRequest(content, sender, userA, userB, itemA, itemB, perm);
    }

    /**
     * Return a new UnfreezeRequest object
     * @param content the String content
     * @param username the String username of the user who wants to be unfrozen
     * @return the UnfreezeRequest message
     */
    public Message getUnfreezeRequest(String content, String username){
        return new UnfreezeRequest(content, username);
    }

    /**
     * Return a new UnbanRequest object
     * @param content the String content
     * @param username the String username of the banned user who wants to be unbanned
     * @return the UnbanRequest message
     */
    public Message getUnbanRequest(String content, String username) {
        return new UnbanRequest(content, username);
    }

    /**
     * Return a new ReportRequest object
     * @param content the String content of why the message is reported
     * @param sender the String username of sender of the
     * @param contentReported the String content that is being reported
     * @param reportPerson the person reporting
     * @return the ReportRequest message
     */
    public Message getReportRequest(String content, String sender, String contentReported, String reportPerson) {
        return new ReportRequest(content, sender, contentReported, reportPerson);
    }

}

