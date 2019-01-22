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
    int LID;
    String text = null;
    
    public Message(int messageID, int LID, String sender, String text) {
        super();
        this.messageID = messageID;
        this.sender = sender;
        this.LID = LID;
        this.text = text;
    }
    
    public String getSender() {        
        return sender;    
    }    
    public void setSender() {        
        this.sender = sender;    
    }
    public int getLID() {        
        return LID;    
    }    
    public void setLID() {        
        this.LID = LID;    
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
