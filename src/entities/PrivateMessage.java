package entities;

import java.io.Serializable;

public class PrivateMessage extends Message implements Serializable {
    /**
     * Class constructor
     * A message sent by an account with only information/a content
     * @param content is the content of the message
     * @param username is the sender of this message's username
     */
    public PrivateMessage(String content, String username){
        super(content, username);
    }

}

