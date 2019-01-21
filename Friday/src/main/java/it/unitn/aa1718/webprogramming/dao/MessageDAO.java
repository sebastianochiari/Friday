/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.Message;
import java.util.Vector;

/**
 *
 * @author leo97
 */
public interface MessageDAO {
    
    public Vector<Message> getMessagesBySenderRecipient(String sender, String recipient);
    
    public Message getMessagesByID(int messageID);
    
    public void createMessage(Message message);
    
    public void updateMessage(Message message);
    
    public void deleteMessageByID(Message message);
    
    public void deleteMessageBySenderRecipient(String sender, String recipient);
    
}
