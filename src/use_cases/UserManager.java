package use_cases;

import entities.*;
import exceptions.*;
import java.util.List;
import java.util.Map;
import exceptions.InvalidUsernameException;

public class UserManager {
    private Map<String, User> allUsers;

    /**
     * Constructs a UserManager object
     * @param allUsers the hashmap of all user objects
     */
    public UserManager(Map<String, User> allUsers) {
        this.allUsers = allUsers;
    }

    /**
     * Allows a user to login. Only use with user login option in main menu!
     * If the username does not match up with password
     * @param username the username input
     * @param password the password input
     * @return True if user logged in, false if invalid login
     */
    public boolean login(String username, String password) {
        // check username
        if(this.allUsers.containsKey(username)) {
            // check password
            // if successful, return String username
            return password.equals(this.allUsers.get(username).getPassword());
        }
        return false;
    }

    /**
     * Attempts to add a new user to the HashMap of all users
     * All usernames are unique.
     * Returns the HashMap if the user successfully created, throw an error if user already exists.
     * @param username the new username
     * @param password the string password
     * @return true if user account successfully created, false if user already exists in system
     * @throws InvalidUsernameException if the username already exists in the system
     */
    public boolean createNewUser(String username, String password)
            throws InvalidUsernameException {
        if(this.allUsers.containsKey(username)) {
            throw new InvalidUsernameException();
        }
        if(!(username.length() < 3)) {
            this.allUsers.put(username, new entities.User(username, password));
            return true;
        }
        return false;
    }

    /**
     * Changes the password of a user
     * @param username String username
     * @param newPassword the new password
     */
    public void changePassword(String username, String newPassword) {
        this.allUsers.get(username).setPassword(newPassword);
    }

    /**
     * Returns the hashmap of all users
     * @return the hashmap of users
     */
    public Map<String, User> getUserData(){
        return this.allUsers;
    }

    /**
     * Checks to see if this username exists in the system of users and that the username is greater than 3 characters.
     * @param username The username to check
     * @return Returns true if the user does exist in the system and is greater than/equal to 3 characters
     */
    public boolean isValidUser(String username) {
        return this.allUsers.containsKey(username) && username.length() >= 3;
    }

    /**
     * Return all usernames in this system.
     * @return String of all usernames
     */
    @Override
    public String toString() {
        StringBuilder allUsers = new StringBuilder();
        for (String user: this.allUsers.keySet()) {
            allUsers.append(user).append(" ");
        }
        return allUsers.toString();
    }

    // GETTERS/SETTERS

    /**
     * Returns whether or not this user can trade.
     * @param user the user in question
     * @param borrowedTimes the num of times this user has borrowed
     * @param lendTimes the num of times this user has loaned
     * @param numIncomplete the num of incomplete trades this user has
     * @param numTradesMadeThisWeek the num of trade this user made this week
     * @return True if this user can trade, false if not
     * @throws UserFrozenException Throws if the user is already frozen
     */
    public boolean getCanTrade(String user, int borrowedTimes, int lendTimes,
                               int numIncomplete, int numTradesMadeThisWeek) throws UserFrozenException {
        if (this.allUsers.get(user).getIsFrozen()) {
            throw new UserFrozenException();
        }
        // check borrows, num of incomplete trades, num of trades made this week
        return (borrowedTimes - lendTimes) < this.allUsers.get(user).getThreshold() &&
                numIncomplete < this.allUsers.get(user).getLimitOfIncompleteTrade() &&
                numTradesMadeThisWeek < this.allUsers.get(user).getTradePerWeek();
    }

    /**
     * Variant of getCanTrade to check to see if the user can trade; will ignore borrows v. loans so that user can
     * can loan items
     * @param username user in question
     * @param numIncomplete the number of incomplete trades
     * @param numTradesMadeThisWeek the number of trade offers made this week
     * @return true if the user can trade, false if not
     * @throws UserFrozenException if the user is already frozen
     */
    public boolean getCanTradeIgnoreBorrowsLoans(String username, int numIncomplete, int numTradesMadeThisWeek)
            throws UserFrozenException {
        if (this.allUsers.get(username).getIsFrozen()) {
            throw new UserFrozenException();
        }
        return numIncomplete < this.allUsers.get(username).getLimitOfIncompleteTrade() &&
                numTradesMadeThisWeek < this.allUsers.get(username).getTradePerWeek();
    }

    /**
     * Return a certain user's messages.
     * @param username the user you want
     * @return list of this user's messages
     */
    public List<Message> getUserMessages(String username) {
        return this.allUsers.get(username).getMessages();
    }

    /**
     * Allows for setting of a user's messages
     * @param username the user in question
     * @param message the List of messages to set
     */
    public void setUserMessages(String username, List<Message> message) {
        this.allUsers.get(username).setMessages(message);
    }

    /**
     * Return this user's limit of incomplete trades
     * @param username the user in question
     * @return the max num of incomplete trades for this user
     */
    public int getUserIncompleteTrades(String username) {
        return this.allUsers.get(username).getLimitOfIncompleteTrade();
    }

    /**
     * Returns the max trades per week that a specified user can make.
     * @param username the String username
     * @return the number of trades this user can make
     */

    public int getTradesPerWeekForUser(String username) { return this.allUsers.get(username).getTradePerWeek(); }

    /**
     * Returns the account information of a selected user.
     * @param username the user in question
     * @return The string representation of this user's account
     */
    public String getUserInfo(String username) {
        return this.allUsers.get(username).accountInfo();
    }

    /**
     * Returns a user's frozen status
     * @param username the user in question
     * @return True if frozen, false if unfrozen.
     */
    public boolean getUserFrozenStatus(String username) {
        return this.allUsers.get(username).getIsFrozen();
    }

    /**
     * Returns whether or not this user is banned.
     * @param username the string username in question
     * @return True if banned, false if not
     */
    public boolean getUserIsBanned(String username) {
        return this.allUsers.get(username).getIsBanned();
    }

    /**
     * Return's a user's threshold
     * @param username the user in question
     * @return the threshold of borrows v. loans
     */
    public int getUserThreshold(String username) {
        return this.allUsers.get(username).getThreshold();
    }

    /**
     * Adds a message to a user's account
     * @param username the user to be accessed
     * @param message the message to add
     */
    public void addUserMessage(String username, Message message) { this.allUsers.get(username).addMessages(message); }

    // ADMIN METHODS

    /**
     * ADMIN ONLY
     * Sets a user account to banned
     * @param username the user in question
     */
    public void banUserAccount(String username) {
        this.allUsers.get(username).setBanned();
    }

    /**
     * ADMIN ONLY
     * Sets a user account's status to frozen.
     * @param username the user in question
     */
    public void freezeUserAccount(String username) {
        this.allUsers.get(username).setFrozen();
    }

    /**
     * ADMIN ONLY
     * Sets a user account's status to unfrozen
     * @param username the user in question
     */
    public void unFreezeUserAccount(String username) {
        this.allUsers.get(username).setUnfrozen();
    }

    /**
     * ADMIN ONLY
     * Allows an admin to set a new trades per week value for all users.
     * @param newTradesPerWeek the new trades per week
     */
    public void setWeeklyTrades(int newTradesPerWeek) {
        for (entities.User user: this.allUsers.values()) {
            user.setTradePerWeek(newTradesPerWeek);
        }
    }

    /**
     * ADMIN ONLY
     * Allows an admin to set a new weekly trades limit for one user
     * @param username the user to be accessed
     * @param newTradesPerWeek the new trades per week limit
     */
    public void setWeeklyTradesForOneUser(String username, int newTradesPerWeek) {
        this.allUsers.get(username).setTradePerWeek(newTradesPerWeek);
    }

    /**
     * ADMIN ONLY
     * Allows an admin to set a new limit of how many incomplete trades a user can have for all users.
     * @param newLimit the new incomplete trades limit to set all users to
     */
    public void setLimitOfIncompleteTrades(int newLimit) {
        for (entities.User user : this.allUsers.values()) {
            user.setLimitOfIncompleteTrade(newLimit);
        }
    }

    /**
     * ADMIN ONLY
     * @param username The user to be set
     * @param newLimit the new incomplete trades limit that one user will be set to
     */
    public void setLimitOfIncompleteTradesForOneUser(String username, int newLimit) {
        this.allUsers.get(username).setLimitOfIncompleteTrade(newLimit);
    }

    /**
     * ADMIN ONLY
     * Allows an admin to set a new borrow v. loan threshold for all users
     * @param newThreshold the new threshold to set
     */
    public void setNewThreshold(int newThreshold) {
        for(entities.User user : this.allUsers.values()) {
            user.setThreshold(newThreshold);
        }
    }

    /**
     * ADMIN ONLY
     * Allows an admin to set a new borrow v. loan threshold for a single user
     * @param username the user to be accessed
     * @param newThreshold the new borrow v. loan threshold
     */
    public void setNewThresholdForOneUser(String username, int newThreshold) {
        this.allUsers.get(username).setThreshold(newThreshold);
    }
}
