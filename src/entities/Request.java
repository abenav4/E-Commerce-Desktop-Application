package entities;


import java.io.Serializable;

public abstract class Request extends Message implements Serializable {
    private String[] options;

    /**
     * Class contructor.
     * A message sent by the system with a content and some decisions a person can make based on the content
     * @param content is the content of the message
     * @param options the options that can be made
     */
    Request(String content, String[] options){
        super(content);
        this.options = options;
    }

    /**
     * Class contructor.
     * A message sent by an account with a content and some decisions a person can make based on the content
     * @param content is the content of the message
     * @param options the options that can be made
     * @param username is the sender's username
     */
    Request(String content, String[] options, String username){
        super(content, username);
        this.options = options;
    }

    /**
     * Returns the options a user have to this request
     * @return the string representation of the options a user have to this request
     */
    public String[] getOptions(){
        return options;
    }
}
