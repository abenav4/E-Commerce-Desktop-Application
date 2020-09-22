package entities;

import java.io.Serializable;

public class UnbanRequest extends Request implements Serializable {
    /**
     * Class constructor.
     * A message sent to the Admins from a user that request to be unbanned from the system
     * @param content is the content of the message
     * @param username the username of the user that is requesting to be unbanned
     */
    public UnbanRequest(String content, String username){
        super(content, new String[]{"Unban", "Ignore"}, username);
    }

    /**
     * Getter of the username person requesting to be unbanned
     * @return the username person requesting to be unbanned
     */
    public String getUser(){ return super.getSender(); }

    /**
     * Returns a string representation of the message
     * @return the content and user of the message in a string representation
     */
    @Override
    public String toString() {
        return String.format("%s\nThe User's username: \n%s", super.toString(), getUser());
    }
}

