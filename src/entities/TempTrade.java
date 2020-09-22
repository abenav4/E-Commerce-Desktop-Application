package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class TempTrade extends Trade implements Serializable {

    private final LocalDateTime finishDate;

    /**
     * Creates a trade with an item that both the seller wants to sell
     * and the buyer wants to buy.
     * The buyer creates the trade with the item that they want to sell.
     * @param traderA takes in a entities.User that wants to create the entities.Trade.
     * @param traderB takes in a entities.User that wants to borrow the item based on the trade.
     * @param userAItemsToTrade takes in items that want to be traded to userB.
     * @param userBItemsToTrade takes in items that want to be traded to userA.
     * @param startDate is a LocalDateTime that follows a specific date format.
     * @param finishDate is a LocalDateTime that indicates the end of a temporary trade.
     */
    public TempTrade(String traderA,
                     String traderB,
                     List<Item> userAItemsToTrade,
                     List<Item> userBItemsToTrade,
                     LocalDateTime startDate,
                     LocalDateTime finishDate) {
        super(traderA, traderB, userAItemsToTrade, userBItemsToTrade, startDate);
        this.finishDate = finishDate;
    }
    /**
     * This method provides you with the number of days left after a trade has been processed.
     * @return an integer that indicates the number of days left in the trade.
     */
    public int daysLeft() {
        int daysInYear = 365;
        return (daysInYear - getStartDate().getDayOfYear()) - (finishDate.getDayOfYear());
    }

    /**
     * Returns a boolean that determines whether the user has confirmed a meeting for the trade or not.
     * Even after the first meeting has been confirmed already.
     * If true is returned then the user has confirmed the trade, if false then the user has not
     * confirmed the meeting.
     *
     * @param traderName takes in a String that determines if they are part of the trade.
     * @return a boolean that determines if the trade has been confirmed by the user.
     */
    public boolean needToConfirmMeetingTwo(String traderName) {
        boolean meetingCompleted = false;
        boolean confirmTimes = getTraderAConfirmTimes() == 1 && getTraderBConfirmTimes() == 1,
                endTimePast = (LocalDateTime.now()).compareTo(finishDate) >= 0;
        if (traderName.equals(getTraderA())) {
            if (!getFailed() && confirmTimes && endTimePast) {
                meetingCompleted = true;
            }
        } else if (traderName.equals(getTraderB())) {
            if (!getFailed() && confirmTimes && endTimePast) {
                meetingCompleted = true;
            }
        }
        return meetingCompleted;
    }

    /**
     * Determines whether a trade is completed based on the number of times
     * both traders confirm the TradeRequest details. In this class, both traders need
     * to confirm twice.
     * @return a boolean on whether a trade is completed or not.
     */
    @Override
    public boolean getCompleted() {
        return getTraderBConfirmTimes() == 2 && getTraderAConfirmTimes() == 2 && !getFailed();
    }

    /**
     * Confirms a meeting for the user if they are part of a trade.
     * If they are not part of a trade then an exception is thrown!
     *
     * @param traderName takes in a String that determines if they are part of the trade.
     * @param confirmation takes a boolean that determines if they confirmed the meeting.
     */
    @Override
    public void setConfirm(String traderName, boolean confirmation) {
        if (traderName.equals(getTraderA())) {
            if (needToConfirmMeetingOne(traderName) || needToConfirmMeetingTwo(traderName)) {
                addTraderAConfirmTimes();
                if (!confirmation) {
                    setFailed(true);
                }
            }
        } else if (traderName.equals(getTraderB())) {
            if (needToConfirmMeetingOne(traderName) || needToConfirmMeetingTwo(traderName)) {
                addTraderBConfirmTimes();
                if (!confirmation) {
                    setFailed(true);
                }
            }
        }
    }

    /**
     * String representation of TempTrade
     * @return a string that represents tempTrade
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + "The Object(s) involved in trade had been returned on" + finishDate;
    }

    @Override
    public boolean equals(Trade trade) {
        if(!super.equals(trade))return false;
        if(! (trade instanceof TempTrade)) return false;
        return finishDate.equals(((TempTrade) trade).finishDate);
    }
}
