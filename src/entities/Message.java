package entities;

import java.io.Serializable;

public abstract class Message implements Serializable {
    private String content;
    private String senderUsername;
    /**
     * Class constructor
     * A message sent by the system with some content
     * @param content is the content of the message
     */
    public Message(String content){
        this.content = content;
        senderUsername = "System Messages";
    }

    /**
     * Class constructor
     * A message sent by an account with some content
     * @param content is the content of the message
     * @param username is the sender of this message's username
     */
    public Message(String content, String username){
        this.content = content;
        senderUsername = username;
    }

    /**
     * Setter of the content of the message
     * @param content the new content of the message
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Setter of the sender of the message
     * @param senderUsername the new sender's username
     */
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    /**
     * Getter of the content of the message
     * @return the content of the message
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter of the sender of the message
     * @return the sender of the message
     */
    public String getSender() {
        return senderUsername;
    }

    public boolean isSystemMessage() {
        return senderUsername.equals("System Messages");
    }

    /**
     * Returns a string representation of the message
     * @return the content of the message in a string representation
     */
    @Override
    public String toString() {
        return "From " + senderUsername+":\n "+content;
    }
}
