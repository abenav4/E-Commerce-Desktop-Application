package use_cases;

import entities.TempTrade;
import entities.Trade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TradeManager {
    private Map<String, List<Trade>> tradeHistory;

    /**
     * Class constructor.
     * creates a TradeManager with a given HashMap of information on the trades in this system.
     * @param tradeHistory the trade history of all users in the system
     */
    public TradeManager( Map<String, List<Trade>> tradeHistory) {
        if(tradeHistory == null) this.tradeHistory = new HashMap<>();
        this.tradeHistory = tradeHistory;
    }

    /**
     * Getter of the trade history of a user. Trade history is the list of trades that the user is involved in the order
     * of recency
     * @param username the username of the user
     * @return the trade history of a user
     */
    public List<Trade> getTradeHistory(String username) {
        if(!tradeHistory.containsKey(username))return new ArrayList<>();
        return tradeHistory.get(username);
    }

    /**
     * Getter of the n most recent trade from the trade history of a user.
     * Trade history is the list of trades that the user is involved in
     * @param username the username of the user
     * @param num the amount of recent trade. If n is 3, the 3 most recent trade will be returned
     * @return the n most recent trade from the trade history of this user
     */
    public Trade[] getRecentTrade(String username, int num) {
        List<Trade> tradeHistory = getTradeHistory(username);
        //getting the n most recent trades
        int size = tradeHistory.size();
        Trade[] trades = new Trade[num];
        for(int i = 0; i<num; i++){
            if(size < i+1) break;
            trades[i] = tradeHistory.get(size - i - 1);
        }
        return trades;
    }

    /**
     * Getter of the number of times a user has borrowed
     * @param username the user's username
     * @return the number of times a user has borrowed
     */
    public int getBorrowedTimes(String username) {
        List<Trade> temp = getTradeHistory(username);
        int total = 0;
        for(Trade t: temp){
            if(t.isBorrowed(username))total++;
        }
        return total;
    }

    /**
     * Getter of the number of times a user has lend
     * @param username the user's username
     * @return the number of times a user has lend
     */
    public int getLendTimes(String username) {
        List<Trade> temp = getTradeHistory(username);
        int total = 0;
        for(Trade t: temp){
            if(t.isLent(username))total++;
        }
        return total;
    }

    /**
     * Getter of the usernames of the n most frequent trading partners of a user
     * @param username the user's username
     * @param num the amount of most frequent trading partners. If n is 3, the 3 frequent trading
     *          partners of a user will be returned
     * @return the username of the n most frequent trading partners
     */
    public String[] getFrequentTradingPartners(String username, int num) {
        List<Trade> l = getTradeHistory(username);
        TreeMap<Integer, ArrayList<String>> counter = new TreeMap<>();
        ArrayList<String> partners = new ArrayList<>();
        String[] tradingPartners = new String[num];
        //Getting the list of username of the trading partners
        for(Trade t: l){
            String partner = t.tradingPartner(username);
            if(partner == null)continue;
            partners.add(partner);
        }
        //counting and ordering the times the username of the trading partner occurs
        for(String u: partners) {
            int c = count(partners, u);
            if (counter.containsKey(c)) {
                ArrayList<String> list = counter.get(c);
                if (!list.contains(u)) list.add(u);
            } else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(u);
                counter.put(c, temp);
            }
        }
        //getting the n most frequent username in the list
        Set<Integer> keys = counter.descendingKeySet();
        for(Integer key: keys){
            ArrayList<String> p = counter.get(key);
            for(int i = 0; i< p.size(); i++){
                for(int j=0; j<num; j++){
                    if(tradingPartners[j] == (null)){
                        tradingPartners[j] = p.get(i);
                        break;
                    }
                    if(tradingPartners[j].equals(p.get(i)))break;
                }
                if(tradingPartners[num-1] != (null))return tradingPartners;
            }
            if(tradingPartners[num-1]!=(null))return tradingPartners;
        }
        return tradingPartners;
    }
    private int count(List<String> list, String item){
        //counting the number of times a username occured in the list
        int sum = 0;
        for(String u: list){
            if(u.equals(item))sum++;
        }
        return sum;
    }

    /**
     * Adding a trade to the system
     * @param trade the trade added to the system
     */
    public void addTrade(Trade trade){
        //Adding the trade to TraderA's history
        if(tradeHistory.containsKey(trade.getTraderA())) {
            List<Trade> temp = tradeHistory.get(trade.getTraderA());
            temp.add(trade);
            tradeHistory.put(trade.getTraderA(), temp);
        }
        else{
            List<Trade> temp = new ArrayList<>();
            temp.add(trade);
            tradeHistory.put(trade.getTraderA(), temp);
        }

        //Adding the trade to TraderB's history
        if(tradeHistory.containsKey(trade.getTraderB())) {
            List<Trade> temp = tradeHistory.get(trade.getTraderB());
            temp.add(trade);
            tradeHistory.put(trade.getTraderB(), temp);
        }
        else{
            List<Trade> temp = new ArrayList<>();
            temp.add(trade);
            tradeHistory.put(trade.getTraderB(), temp);
        }
    }

    /**
     * Getter for all the trades that are in the user's trade history that needs to be confirmed at this time.
     * Trade history is the list of trades that the user is involved in
     * @param username The user's username
     * @return a list of trades the user needs to confirm at this time
     */
    public List<Trade> tradesToConfirm(String username) {
        List<Trade> temp = getTradeHistory(username);
        List <Trade> trades = new ArrayList<>();
        for(Trade t: temp){
            if(t.needToConfirmMeetingOne(username))trades.add(t);
            else if(t instanceof TempTrade){
                if(((TempTrade)t).needToConfirmMeetingTwo(username))trades.add(t);
            }
        }
        return trades;
    }

    /**
     * Return the number of incomplete trades that are in the user's trade history.
     * Trade history is the list of trades that the user is involved in
     * @param username The user's username
     * @return the number of incompleted trade
     */
    public int getIncompleteTimes(String username) {
        List<Trade> temp = getTradeHistory(username);
        int sum = 0;
        for(Trade t: temp){
            if(!t.getCompleted())sum++;
        }
        return sum;
    }

    /**
     * Returns the number of trades that are in the user's trade history that are created in this week.
     * Trade history is the list of trades that the user is involved in
     * @param username The user's username
     * @return the number of trades created this week from this user
     */
    public int numberOfTradesCreatedThisWeek(String username) {
        List<Trade> temp = getTradeHistory(username);
        int sum = 0;

        //Getting the time frame of the current week
        LocalDateTime now = LocalDateTime.now();
        int n = (now.getDayOfWeek()).getValue();

        LocalDateTime start = now.minusDays(n - 1);
        start = start.withHour(0);
        start = start.withMinute(0);
        start = start.withSecond(0);

        LocalDateTime end = now.plusDays(7 - n);
        end = end.withHour(23);
        end = end.withMinute(59);
        end = end.withSecond(59);

        //Getting the number of trades created within the time frame
        for(Trade t: temp) {
            if (start.compareTo(t.getCreationDate()) <= 0 && end.compareTo(t.getCreationDate()) >= 0) {
                sum++;
            }
        }
        return sum;
    }


    /**
     * Confirm a trades this user have in their trade history with a status they entered
     * Trade history is the list of trades that the user is involved in
     * @param username the username of this user
     * @param trade The trades to be confirmed
     * @param status the confirmation status from the user
     */
    public void setConfirm(String username, Trade trade, boolean status) {
        List<Trade> temp = getTradeHistory(username);
        for(Trade t1: temp){
            if(t1.equals(trade)){
                t1.setConfirm(username, status);
                return;
            }
        }
    }

    /**
     * Removing a trade from the system
     * @param trade the trade to be removed from the system
     */
    public void removeTrade(Trade trade){
        if(tradeHistory.containsKey(trade.getTraderA())){
            tradeHistory.get(trade.getTraderA()).remove(trade);
        }
        if(tradeHistory.containsKey(trade.getTraderB())){
            tradeHistory.get(trade.getTraderB()).remove(trade);
        }
    }

    /**
     * Returns the list of all unstarted trades in a user's trade history
     * Trade history is the list of trades that the user is involved in
     * @param username the user's username
     * @return the list of all unstarted trades this user have
     */
    public List<Trade> getUnstartTrades(String username){
        List<Trade> tradeHistory = getTradeHistory(username);
        List<Trade> unstartTrades = new ArrayList<>();
        for(Trade t: tradeHistory){
            if(t.isUnstarted())unstartTrades.add(t);
        }
        return unstartTrades;
    }

    /**
     * Getter of the data stored in this manager
     * @return the data stored in this manager
     */
    public Map<String, List<Trade>> getTradeData(){
        return tradeHistory;
    }


}
