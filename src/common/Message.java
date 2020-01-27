/*
 * Classe Message
 */
package common;

import java.io.Serializable;

/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-11-18
 */
public class Message implements Serializable{
    
    private String sender;
    private final String content;
        
    public Message(String sender, String content)
    {
        this.sender = sender;
        this.content = content;
    }
    
    @Override
    public String toString()
    {
        return sender + " : " + content;
    }
    
    public void setId(String sender)
    {
        this.sender = sender;
    }
    
    public String getContent()
    {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
}
