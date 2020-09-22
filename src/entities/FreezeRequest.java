package entities;

import java.io.Serializable;

public class FreezeRequest extends Request implements Serializable {
    private String username;

    /**
     * Class constructor.
     * A message sent to the Admins from the system that request to freeze a User
     * @param content is the content of the message
     * @param username the user that might be frozen's username
     */
    public FreezeRequest(String content, String username) {
        super(content, new String[]{"Freeze", "Ignore"});
        this.username = username;
    }

    /**
     * Getter for the user that might need to be frozen's username
     * @return the user's username
     */
    public String getUser(){ return username; }


    /**
     * Returns a string representation of the message
     * @return the content and user of the message in a string representation
     */
    @Override
    public String toString() {
        return super.toString() + "\nThe User's username: \n"+username;
    }
}
