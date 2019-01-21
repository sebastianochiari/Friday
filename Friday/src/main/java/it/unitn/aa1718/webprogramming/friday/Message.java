/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 *
 * @author leo97
 */
public class Message {
    
    int messageID;
    String sender = null;
    String recipient = null;
    String object = null;
    String text = null;
    
    public Message(int messageID, String sender, String recipient, String object, String text) {
        super();
        this.messageID = messageID;
        this.sender = sender;
        this.recipient = recipient;
        this.object = object;
        this.text = text;
    }
    
    public String getObject() {        
        return object;    
    }    
    public void setObject() {        
        this.object = object;    
    }
    public String getSender() {        
        return sender;    
    }    
    public void setSender() {        
        this.sender = sender;    
    }
    public String getRecipient() {        
        return recipient;    
    }    
    public void setRecipient() {        
        this.recipient = recipient;    
    }
    public String getText() {        
        return text;    
    }    
    public void setText() {        
        this.text = text;    
    }
    
    public int getMessageID() {        
        return messageID;    
    }    
    public void setMessageID() {        
        this.messageID = messageID;    
    }
    
}
