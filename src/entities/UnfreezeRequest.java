package entities;

import java.io.Serializable;

public class UnfreezeRequest extends Request implements Serializable {

    /**
     * Class constructor.
     * A message sent to a Admin from a User that request to unfreeze themselves
     * @param content is the content of the message
     * @param username is the user wanted to be unfrozen's username
     */
    public UnfreezeRequest(String content, String username) {
        super(content, new String[]{"Unfreeze", "Ignore"}, username);
    }

    /**
     * Getter for the user that wanted to be unfrozen's usename
     * @return the user's username
     */
    public String getUser(){ return super.getSender(); }

    /**
     * Returns a string representation of the message
     * @return the content and user of the message in a string representation
     */
    @Override
    public String toString() {
        return super.toString() + "\nThe User's username: \n"+super.getSender();
    }
}
