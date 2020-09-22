package entities;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class User extends Account implements Serializable{
    private List<Message> messages = new ArrayList<>();
    private Status status = Status.UNFROZEN;
    private int tradePerWeek = 5;
    private int threshold = 1;
    private int limitOfIncompleteTrade = 5;


    /**
     * Class constructor.
     * Creates an User with the given username and password.
     * This User has the default values:
     * - Limit of 5 trades per week
     * - Must have 1 more lent than borrow in their trade history
     * - Limit of 5 incomplete trades at once
     * @param username is the username of this User
     * @param password is the password of this User
     */
    public User(String username, String password) {
        super(username, password);
    }

    /**
     * Getter of the threshold (how many more times must you lend items before you can borrow) value of this user
     * @return the threshold value
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Getter of the limit of imcomplete trades this user can have
     * @return the value of the limit of imcomplete trades
     */
    public int getLimitOfIncompleteTrade() {
        return limitOfIncompleteTrade;
    }

    /**
     * Getter of the limited of times the user can trade in a week of this account
     * @return the limited of times the user can trade of this account
     */
    public int getTradePerWeek(){
        return tradePerWeek;
    }

    /**
     * Getter for if the User is frozen
     * @return whether the User is frozen
     */
    public boolean getIsFrozen(){
        return status.equals(Status.FROZEN);
    }

    /**
     * Getter for if the User is banned
     * @return whether the User is banned
     */
    public boolean getIsBanned(){
        return status.equals(Status.BANNED);
    }

    /**
     * Getter of the messages of this account
     * @return the messages of this account
     */
    public List<Message> getMessages(){
        return messages;
    }


    /**
     * Setting the user to be frozen
     */
    public void setFrozen(){
        status = Status.FROZEN;
    }

    /**
     * Setting the user to be unfrozen
     */
    public void setUnfrozen(){
        status = Status.UNFROZEN;
    }

    /**
     * Setting the user to be banned
     */
    public void setBanned(){
        status = Status.BANNED;
    }

    /**
     * Add a message to the list of messages to this account
     * @param message the new message
     */
    public void addMessages(Message message){
        messages.add(message);
    }

    /**
     * Setting a list of message as the list of messages to this account
     * @param messages the list of messages
     */
    public void setMessages(List<Message> messages){
        this.messages = messages;
    }

    /**
     * Changing the threshold (how many more times must you lend items before you can borrow) that this user has
     * @param newThreshold the new theshold value
     */
    public void setThreshold(int newThreshold){
        threshold = newThreshold;
    }

    /**
     * Changing the limit of incomplete trades this user can have
     * @param newLimit the new limit
     */
    public void setLimitOfIncompleteTrade(int newLimit){
        limitOfIncompleteTrade = newLimit;
    }

    /**
     * Changing the limited of times that this user can trade in a week
     * @param tradePerWeek the new trade per week value
     */
    public void setTradePerWeek(int tradePerWeek){
        this.tradePerWeek = tradePerWeek;
    }

    /**
     * Returns all the information of this account information all at once in a string representation
     * @return the string representation of all information in this account
     */
    public String accountInfo(){
        String info = String.format("   ----- Account info for %s -----", this.toString());
        info = String.format("%s\n Current lend until borrow threshold: %d", info, this.threshold);
        switch (status){
            case BANNED:
                info = String.format("%s\n Status: BANNED", info);
                break;
            case UNFROZEN:
                info = String.format("%s\n Status: UNFROZEN", info);
                break;
            case FROZEN:
                info = String.format("%s\n Status: FROZEN", info);
                break;
        }
        info = String.format("%s\n Limit for trades per week: %d", info, this.tradePerWeek);
        info = String.format("%s\n Limit for incomplete trades per week: %d", info, this.limitOfIncompleteTrade);
        return info;
    }
}

