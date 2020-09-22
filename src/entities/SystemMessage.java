package entities;

import java.io.Serializable;

public class SystemMessage extends Message implements Serializable {
    /**
     * Class constructor
     * A message sent by the system with only information/a content
     * @param content is the content of the message
     */
    public SystemMessage(String content){
        super(content);
    }

}
