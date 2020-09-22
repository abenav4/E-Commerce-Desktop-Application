package entities;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private String username, password;

    /**
     * Class constructor.
     * Creates an Account with a given username and password
     * @param username is the username of this account
     * @param password is the password of this account
     */
    Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Getter of the username of this account
     * @return the username of this account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter of the password of this account
     * @return the password of this account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setting a new password to this account
     * @param newPassword the new password for the account
     */
    public void setPassword(String newPassword){
        password = newPassword;
    }

    /**
     * Indicates whether two Account are "equal". Two Account are equal iff
     * both have the same username
     * @param accInfo is the Account that is being compared with
     * @return Whether the two Account are equal.
     */
    public boolean equals(Account accInfo) {
        return  accInfo.getUsername().equals(username);
    }

    /**
     * Returns a hash code value for this account.
     * @return A hash code value for this account.
     */
    @Override
    public int hashCode() {
        return username.hashCode();
    }

    /**
     * Returns a string representation of the account. Which is just the username of this Account
     * @return a string representation of the account.
     */
    @Override
    public String toString() {
        return username;
    }
}
