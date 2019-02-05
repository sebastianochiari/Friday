/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
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
 * Classe DAO che permette la gestione dei messaggi 
 */
public class MySQLMessageDAOImpl implements MessageDAO{
    
    private static final String Create_Query = "INSERT INTO messages (messageID, LID, sender, text) VALUES (?, ?, ?, ?)";
    
    private static final String Read_LID_Query = "SELECT messageID, LID, sender, text FROM messages WHERE LID=?";
   
    private static final String Read_messageID_Query = "SELECT messageID, LID, sender, text FROM messages WHERE messageID=?";
    
    private static final String Update_Query = "UPDATE messages SET messageID=?, LID=?, sender=?, text=? WHERE messageID = ?";
        
    private static final String Delete_Query_By_LID = "DELETE FROM messages WHERE LID = ?";
                
    private static final String Delete_Query_By_MessageID = "DELETE FROM cookies WHERE messageID = ?";

    /**
     * Metodo che ritorna i messaggi in base all'ID della lista alla quale sono associati
     * @param LID intero associato alla lista
     * @return vettore con tutti i risultati trovati
     */
    @Override
    public Vector<Message> getMessagesByLID(int LID) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Vector<Message> messages = new Vector();
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = null;
            preparedStatement = connection.prepareStatement(Read_LID_Query);
            preparedStatement.setInt(1, LID);

            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while(result.next()){
                    Message message = new Message(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4));
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

    /**
     * Metodo che ritorna i messaggi in base all'ID
     * @param messageID intero che rappresenta il messaggio da ritornare
     * @return oggetto di tipo messaggio 
     */
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
                    message = new Message(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4));
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

    /**
     * Metodo che permette la creazione di un messaggio 
     * @param message oggetto da creare????
     */
    @Override
    public void createMessage(Message message) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getMessageID());
            preparedStatement.setInt(2, message.getLID());
            preparedStatement.setString(3, message.getSender());
            preparedStatement.setString(4, message.getText());
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

    /**
     * Metodo che permette la modifica di un messaggio
     * @param message oggetto da modificare
     */
    @Override
    public void updateMessage(Message message) {
       Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, message.getMessageID());
            preparedStatement.setInt(2, message.getLID());
            preparedStatement.setString(3, message.getSender());
            preparedStatement.setString(4, message.getText());
            preparedStatement.setInt(5, message.getMessageID());
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

    /**
     * Metodo che elimina i messaggi in base all'ID 
     * @param message messaggio da eliminare
     */
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

    /**
     * Metodo che elimina i messaggi in base all'ID della lista alla quale sono associati
     * @param LID intero che rappresenta la lista
     */
    @Override
    public void deleteMessageByLID(int LID) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query_By_LID);
            preparedStatement.setInt(1, LID);
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
