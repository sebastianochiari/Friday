/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */

package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.friday.Sharing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
/**
 * Classe DAO che permette la gestione della condivisione delle liste
 */
public class MySQLSharingDAOImpl implements SharingDAO{
    
    private static final String Create_Query = "INSERT INTO sharing (email, LID, modify, addRemProd, deleteList) VALUES (?, ?, ?, ?, ?)";
    
    private static final String Read_Query = "SELECT email, LID, modify, addRemProd, deleteList FROM sharing WHERE (email = ? and LID = ?)";
    
    private static final String Read_All_Emails_By_LID_Query = "SELECT * FROM sharing WHERE LID = ?";
    
    private static final String Read_All_LIDs_By_Email_Query = "SELECT email, LID, modify, addRemProd, deleteList FROM sharing WHERE email = ?";
    
    private static final String Read_All_Query = "SELECT email, LID, modify, addRemProd, deleteList FROM sharing";
        
    private static final String Update_Query = "UPDATE sharing SET email=?, LID=?, modify=?, addRemProd=?, deleteList=? WHERE (email = ? and LID = ?)";
    
    private static final String Delete_Query = "DELETE FROM sharing WHERE (email = ? and LID = ?)";

    /**
     * Metodo che ritorna tutte le liste condivise
     * @return list con tutte le liste condivise
     */
    @Override
    public List getAllSharing() {
        
        List sharings = new ArrayList();
        Sharing sharing = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharing = new Sharing(result.getString(1), result.getInt(2), result.getBoolean(3),  result.getBoolean(4),  result.getBoolean(5) );
                sharings.add(sharing);
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
        
        return sharings;
        
    }

    /**
     * Metodo che ritorna tutte le liste in base all'email passata
     * @param email stringa passata come parametro 
     * @return list con tutte le liste 
     */
    @Override
    public List getAllListByEmail(String email) {
        
        List sharings = new ArrayList();
        Sharing sharing = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_LIDs_By_Email_Query);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharing = new Sharing(result.getString(1), result.getInt(2), result.getBoolean(3),  result.getBoolean(4),  result.getBoolean(5));
                sharings.add(sharing);
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
        
        return sharings;
    }

    /**
     * Metodo che ritorna tutte le liste in base all'email
     * @param LID intero passato come parametro identificativo univoco per la lista
     * @return lista contenente tutte le liste associate alla email passata come parametro
     */
    @Override
    public List getAllEmailsbyList(int LID) {
        
        List sharings = new ArrayList();
        Sharing sharing = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Emails_By_LID_Query);
            preparedStatement.setInt(1, LID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharing = new Sharing(result.getString(1), result.getInt(2), result.getBoolean(3),  result.getBoolean(4),  result.getBoolean(5) );
                sharings.add(sharing);
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
        
        return sharings;
    }

    /**
     * Metodo che ritorna tutte le persone con la quale è stata condivisa la lista con l'ID specifico
     * @param LID intero che identifica una lista specifica
     * @param email stringa che rappresenta l'email dell'utente
     * @return istanza di tipo sharing 
     */
    @Override
    public Sharing getSharing(int LID, String email) {
        
        Sharing sharing = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, LID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharing = new Sharing(result.getString(1), result.getInt(2), result.getBoolean(3),  result.getBoolean(4),  result.getBoolean(5) );
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
        
        return sharing;
    }

    /**
     * Metodo che crea una lista condivisa
     * @param sharing
     * @return stringa che rappresenta l'email del creatore
     */
    @Override
    public String createSharing(Sharing sharing) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sharing.getEmail());
            preparedStatement.setInt(2, sharing.getLID());
            preparedStatement.setBoolean(3, sharing.getModify());
            preparedStatement.setBoolean(4, sharing.getAdd());
            preparedStatement.setBoolean(5, sharing.getDelete());
            preparedStatement.execute();
            result = preparedStatement.getGeneratedKeys();
            
            if (result.next() && result != null) {
                return result.getString(1);
            } else {
                return null;
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
        
        return null;
    }

    /**
     * Mmetodo che permette la modifica di una lista condivisa
     * @param sharing istanza della lista da modificare
     * @return booleano che rappresenta la modifica corretta o meno della modifica
     */
    @Override
    public boolean updateSharing(Sharing sharing) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Update_Query);
            preparedStatement.setString(1, sharing.getEmail());
            preparedStatement.setInt(2, sharing.getLID());
            preparedStatement.setBoolean(3, sharing.getModify());
            preparedStatement.setBoolean(4, sharing.getAdd());
            preparedStatement.setBoolean(5, sharing.getDelete());
            preparedStatement.execute();
            
            return true;
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
        
        return false;
    }

    /**
     * Metodo che elimina una lista condivisa
     * @param sharing istanza della lista di eliminare 
     * @return booleano che verifica la corretta eliminazione della lista
     */
    @Override
    public boolean deleteSharing(Sharing sharing) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Delete_Query);
            preparedStatement.setString(1, sharing.getEmail());
            preparedStatement.setInt(2, sharing.getLID());
            preparedStatement.execute();
            
            return true;
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
        
        return false;
        
    }
}
