/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.dao.MessageDAO;
import it.unitn.aa1718.webprogramming.friday.Message;
import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author leo97
 */
public class MySQLMessageDAOImpl implements MessageDAO{
    
    private static final String Create_Query = "INSERT INTO messages (messageID, sender, recipient, object, text) VALUES (?, ?, ?, ?, ?)";
    
    private static final String Read_Sender_Query = "SELECT messageID, sender, recipient, object, text FROM messages WHERE sender=?";
    
    private static final String Read_Recipient_Query = "SELECT messageID, sender, recipient, object, text FROM messages WHERE recipier=?";
    
    private static final String Read_Sender_And_Recipient_Query = "SELECT messageID, sender, recipient, object, text FROM messages WHERE sender=? AND recipier=?";

    private static final String Read_messageID_Query = "SELECT messageID, sender, recipient, object, text FROM messages WHERE messageID=?";
    
    private static final String Update_Query = "UPDATE messages SET messageID=?, sender=?, recipient=?, object=?, text=? WHERE messageID = ?";
    
    private static final String Delete_Query_By_Sender = "DELETE FROM messages WHERE sender = ?";
    
    private static final String Delete_Query_By_Recipient = "DELETE FROM messages WHERE recipient = ?";
    
    private static final String Delete_Query_By_Sender_And_Recipient = "DELETE FROM massages WHERE sender = ? AND recipient = ?";
            
    private static final String Delete_Query_By_MessageID = "DELETE FROM cookies WHERE messageID = ?";


    @Override
    public Vector<Message> getMessagesBySenderRecipient(String sender, String recipient) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Vector<Message> messages = new Vector();
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = null;
            if(sender == null){
                preparedStatement = connection.prepareStatement(Read_Recipient_Query);
                preparedStatement.setString(1, recipient);
            } else if (recipient == null){
                preparedStatement = connection.prepareStatement(Read_Sender_Query);
                preparedStatement.setString(1, sender);
            } else {
                preparedStatement = connection.prepareStatement(Read_Sender_And_Recipient_Query);
                preparedStatement.setString(1, sender);
                preparedStatement.setString(2, recipient);
            }
            
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while(result.next()){
                    Message message = new Message(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                    messages.add(message);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return messages;
    }

    @Override
    public Message getMessagesByID(int messageID) {
        
        Message message = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_messageID_Query);
            preparedStatement.setInt(1, messageID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            if(result.next()){
                    message = new Message(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return message;
    }

    @Override
    public void createMessage(Message message) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getMessageID());
            preparedStatement.setString(2, message.getSender());
            preparedStatement.setString(3, message.getRecipient());
            preparedStatement.setString(4, message.getObject());
            preparedStatement.setString(5, message.getText());
            preparedStatement.execute();
            result = preparedStatement.getGeneratedKeys();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    }

    @Override
    public void updateMessage(Message message) {
       Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, message.getMessageID());
            preparedStatement.setString(2, message.getSender());
            preparedStatement.setString(3, message.getRecipient());
            preparedStatement.setString(4, message.getObject());
            preparedStatement.setString(5, message.getText());
            preparedStatement.setInt(6, message.getMessageID());
            preparedStatement.execute();
            result = preparedStatement.getGeneratedKeys();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    }

    @Override
    public void deleteMessageByID(Message message) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query_By_MessageID);
            preparedStatement.setInt(1, message.getMessageID());
            preparedStatement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    }

    @Override
    public void deleteMessageBySenderRecipient(String sender, String recipient) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            if(sender == null){
                preparedStatement = conn.prepareStatement(Delete_Query_By_Recipient);
                preparedStatement.setString(1, recipient);
            } else if (recipient == null){
                preparedStatement = conn.prepareStatement(Delete_Query_By_Sender);
                preparedStatement.setString(1, sender);
            } else {
                preparedStatement = conn.prepareStatement(Delete_Query_By_Sender_And_Recipient);
                preparedStatement.setString(1, sender);
                preparedStatement.setString(2, recipient);
            }
            preparedStatement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    }
    
}
