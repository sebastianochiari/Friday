/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.SharingProductDAO;
import it.unitn.aa1718.webprogramming.friday.SharingProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO che permette la gestione di prodotti condivisi
 */
public class MySQLSharingProductDAOImpl implements SharingProductDAO{
    
    private static final String Create_Query = "INSERT INTO sharing_products (email, PID) VALUES (?, ?)";
    
    private static final String Read_Query = "SELECT email, PID FROM sharing_products WHERE (email = ? and PID = ?)";
    
    private static final String Read_All_Emails_By_PID_Query = "SELECT * FROM sharing_products WHERE PID = ?";
    
    private static final String Read_All_PIDs_By_Email_Query = "SELECT email, PID FROM sharing_products WHERE email = ?";
    
    private static final String Read_All_Query = "SELECT email, PID FROM sharing_products";
        
    private static final String Update_Query = "UPDATE sharing_products SET email=?, PID=?  WHERE (email = ? and PID = ?)";

    private static final String Delete_Query = "DELETE FROM sharing_products WHERE (email = ? and PID = ?)";

    /**
     * Metodo che ritorna tutti i prodotti condivisi
     * @return lista di prodotti condivisi
     */
    @Override
    public List getAllSharingProduct() {
        
        List sharingProducts = new ArrayList();
        SharingProduct sharingProduct = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharingProduct = new SharingProduct(result.getString(1), result.getInt(2) );
                sharingProducts.add(sharingProduct);
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
        
        return sharingProducts;
    }

    /**
     * Metodo che ritorna tutti i prodotti condivisi dell'utente specifico
     * @param email identificativo univoco dell'utente
     * @return lista di prodotti che corrispondono ai criteri di ricerca
     */
    @Override
    public List getAllProductByEmail(String email) {
        
        List sharingProducts = new ArrayList();
        SharingProduct sharingProduct = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_PIDs_By_Email_Query);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharingProduct = new SharingProduct(result.getString(1), result.getInt(2));
                sharingProducts.add(sharingProduct);
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
        
        return sharingProducts;
    
    }

    /**
     * Metodo che ritorna tutte le email in base all'ID del prodotto specificato in input
     * @param PID id del prodotto
     * @return lista di emails trovate
     */
    @Override
    public List getAllEmailsbyPID(int PID) {
        
        List sharingProducts = new ArrayList();
        SharingProduct sharingProduct = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Emails_By_PID_Query);
            preparedStatement.setInt(1, PID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharingProduct = new SharingProduct(result.getString(1), result.getInt(2) );
                sharingProducts.add(sharingProduct);
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
        
        return sharingProducts;
        
        
    }

    /**
     * Metodo che ritorna i prodotti condivisi in base all'ID del prodotto e l'email
     * @param PID id univoco del prodotto
     * @param email email dell'utente
     * @return lista che corrisponde ai criteri di ricerca
     */
    @Override
    public SharingProduct getSharingProduct(int PID, String email) {
        
        SharingProduct sharingProduct = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, PID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharingProduct = new SharingProduct(result.getString(1), result.getInt(2) );
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
        
        return sharingProduct;
    }

    /**
     * Metodo che permette la creazione di prodotti condivisi
     * @param sharingProduct istanza per la creazione del prodotto
     * @return stringa che rappresenta l'email dell'utente creatore
     */
    @Override
    public String createSharingProduct(SharingProduct sharingProduct) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sharingProduct.getEmail());
            preparedStatement.setInt(2, sharingProduct.getPID());
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
     * Metodo che permette la modifica di un prodotto condiviso
     * @param sharingProduct prodotto da modificare
     * @return booleano che verifica la modifica effettuata o no del prodotto
     */
    @Override
    public boolean updateSharingProduct(SharingProduct sharingProduct) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Update_Query);
            preparedStatement.setString(1, sharingProduct.getEmail());
            preparedStatement.setInt(2, sharingProduct.getPID());
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
     * Metodo che permette l'eliminazione di un prodotto condiviso 
     * @param sharingProduct prodotto da eliminare
     * @return booleano che verifica l'eliminazione corretta o no del prodotto
     */
    @Override
    public boolean deleteSharingProduct(SharingProduct sharingProduct) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Delete_Query);
            preparedStatement.setString(1, sharingProduct.getEmail());
            preparedStatement.setInt(2, sharingProduct.getPID());
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
