package use_cases;

import entities.*;

import java.time.LocalDateTime;
import java.util.List;

public class TradeRequestManager {
    private TradeRequest tradeRequest;
    private Trade trade;

    /**
     * a constructor for TradeRequestManager to edit the traderequet object
     * @param tradeRequest the traderequest that is been edit
     */
    public TradeRequestManager(TradeRequest tradeRequest) {
        this.tradeRequest = tradeRequest;
    }

    /**
     * a constructor of TradeRequestManager to generate the new tradeRequest object
     * @param content trade request message content
     * @param sender the message sender
     * @param userA userA
     * @param userB userB
     * @param itemA items belongs to userA
     * @param itemB items belongs to userB
     * @param perm perm or temp trade
     */
    public TradeRequestManager(String content, String sender, String userA, String userB,  List<Item> itemA,
                               List<Item> itemB, boolean perm){
        MessageBuilder temp = new MessageBuilder();
        this.tradeRequest = (TradeRequest) temp.getTradeRequest(content, sender, userA, userB, itemA, itemB, perm);
    }


    /**
     * method to set the date and place for the first time
     * @param date meeting date
     * @param place meeting place
     */
    public void setDateAndPlaceFirst(LocalDateTime date, String place ){
        tradeRequest.setDate(date);
        tradeRequest.setPlace(place);
    }

    /**
     * change the date and place of the traderequest object
     * @param user the user who is making the edit
     * @param date new date of the meeting
     * @param place new place of the meeting
     */
    public void setDateAndPlace(String user, LocalDateTime date, String place){
        if (user.equals(tradeRequest.getUserA())){
            tradeRequest.setDate(date);
            tradeRequest.setPlace(place);
            tradeRequest.setNumberOfEditA(tradeRequest.getNumberOfEditA() - 1);
            tradeRequest.setContent("Your trade request has been edited");
        }
        else{
            tradeRequest.setDate(date);
            tradeRequest.setPlace(place);
            tradeRequest.setNumberOfEditB(tradeRequest.getNumberOfEditB() - 1);
            tradeRequest.setContent("Your trade request has been edited");
        }
        tradeRequest.setSenderUsername(user);
    }

    /**
     * set the user's confirmation state in the traderequest obejct.
     * @return the trade object that store the involved users, item and meeting date, due date and place of the meeting
     */
    public Trade setConfirmation() {
        if (tradeRequest.isPerm()){
            this.trade = new PermTrade(tradeRequest.getUserA(), tradeRequest.getUserB(), tradeRequest.getItemA() ,
                    tradeRequest.getItemB(), tradeRequest.getDate());
            return this.trade;
        }
        else
            trade = new TempTrade(tradeRequest.getUserA(), tradeRequest.getUserB(), tradeRequest.getItemA(),
                    tradeRequest.getItemB(), tradeRequest.getDate(), tradeRequest.getDate().plusDays(30));
            return trade;
        // Once both user confirms, create a TemTrade, or entities.PermTrade based on t.isPerm
    }

    /**
     * return the tradrequest object
     * @return the traderequst object
     */
    public TradeRequest getTradeRequest() {
        return tradeRequest;
    }

    /**
     * check that if the user can edit or not
     * @param user the user who is editing
     * @return true if user can edit, false if user can not edit
     */
    public boolean canEdit (String user){
        if (user.equals(tradeRequest.getUserA())){
            return tradeRequest.getNumberOfEditA() > 0;
        }
         else {
            return tradeRequest.getNumberOfEditB() > 0;
        }
    }

    /**
     * returns the trade created after two user confirm
     * @return the trade created after two user confirm
     */
    public Trade getTrade() {
        return trade;
    }
}
