/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */

package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.friday.*;
import it.unitn.aa1718.webprogramming.dao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DAO che permette la gestione dei prodotti
 */
public class MySQLProductDAOImpl implements ProductDAO {
    
    private static final String Create_Query = "INSERT INTO products (PID, name, note, logo, photo, PCID, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String Read_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE PID = ? AND PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE email = ? GROUP BY PID ORDER BY PID)) ORDER BY name";
    
    private static final String Read_Email_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE email = ? AND PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE email = ? GROUP BY PID ORDER BY PID))  ORDER BY name";
    
    private static final String Read_PCID_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE PCID = ? AND PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE email = ? GROUP BY PID ORDER BY PID)) ORDER BY name";
    
    private static final String Read_Name_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE Name LIKE ? AND PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE email = ? GROUP BY PID ORDER BY PID)) ORDER BY name";
    
    private static final String Read_Name_Query_Order_By_PCID = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE Name LIKE ? AND PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE email = ? GROUP BY PID ORDER BY PID)) ORDER BY PCID, name";
    
    private static final String Read_NameAndPCID_Query = "SELECT * FROM fridaydb.products WHERE ((Name LIKE ?) AND (PCID = ?) AND PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE email = ? GROUP BY PID ORDER BY PID))) ORDER BY Name;";
    
    private static final String Read_All_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE email = ? GROUP BY PID ORDER BY PID)) ORDER BY name";
    
    private static final String Read_All_Query_Order_By_PCID = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE AND PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE PID NOT IN (SELECT PID FROM fridaydb.sharing_products WHERE email = ? GROUP BY PID ORDER BY PID)) ORDER BY PCID, name";
    
    private static final String Update_Query = "UPDATE products SET (PID=?, name=?, note=?, logo=?, photo=?, PCID=?, email=?) WHERE PID = ?)";
    
    private static final String Delete_Query = "DELETE FROM prpducts WHERE PID = ?";
    
    /**
     * Metodo che ritorna tutti i prodotti 
     * @return lsita con tutti i prodotti
     */
    @Override
    public List getAllProducts(String Email) {
        
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.setString(1, Email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }
    
    /**
     * Metodo che ritorna i prodotti in base all'email 
     * @param email stringa che rappresenta una delle email di un utente
     * @return list di prodotti creati dall'email passata in input
     */
    @Override
    public List getProductsByEmail (String email) {
        
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Email_Query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }
    
    /**
     * Metodo che ritorna i prodotti in base al product category ID passato
     * @param PCID intero che rappresenta la categoria di prodotto specificata
     * @return list di prodotti
     */
    @Override
    public List getProductsByPCID (int PCID, String Email) {
        
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_PCID_Query);
            preparedStatement.setInt(1, PCID);
            preparedStatement.setString(2, Email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }
    
    /**
     * Metodo che ritorna un prodotto in base al suo ID univoco
     * @param PID intero che rappresenta il prodotto
     * @return oggetto che rappresenta il prodotto
     */
    @Override
    public Product getProduct(int PID, String Email) {
		
        Product product= null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Read_Query);
            preparedStatement.setInt(1, PID);
            preparedStatement.setString(2, Email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            if (result.next() && result != null) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
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
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
 
        return product;
    }
    
    /**
     * Metodo che crea un prodotto 
     * @param product oggetto passato per creare il prodotto
     * @return stringa contenente l'ID del prodotto ????????
     */
    @Override
    public String createProduct(Product product) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, product.getPID());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getNote());
            preparedStatement.setString(4, product.getLogo());
            preparedStatement.setString(5, product.getPhoto());
            preparedStatement.setInt(6, product.getPCID());
            preparedStatement.setString(7, product.getEmail());
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
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
 
        return null;
    }
    
    /**
     * Metodo che modifica i campi del prodotto
     * @param product oggetto con i relativi campi da modificare
     * @return boolean settato a 1 se la modifica ha avuto successo
     */
    @Override
    public boolean updateProduct(Product product) {
		
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, product.getPID());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getNote());
            preparedStatement.setString(4, product.getLogo());
            preparedStatement.setString(5, product.getPhoto());
            preparedStatement.setInt(6, product.getPCID());
            preparedStatement.setString(7, product.getEmail());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return false;
    }
    
    /**
     * Metodo che elimina un prodotto
     * @param product oggetto da eliminare
     * @return boolean settato a 1 se l'eliminazione ha avuto successo
     */
    @Override
    public boolean deleteProduct(Product product) {
	Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query);
            preparedStatement.setInt(1, product.getPID());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        
        return false;
    }

    /**
     * Metodo che ritorna i prodotti in base alla ricerca per nome
     * @param name stringa che identifica il nome
     * @param perPCID booleano che specifica se la ricerca va fatta per PCID
     * @return list contenente i prodotti trovati
     */
    @Override
    public List getProductsByName(String name, boolean perPCID, String Email) {
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            
            if(perPCID)
                preparedStatement = connection.prepareStatement(Read_Name_Query_Order_By_PCID);
            else
                preparedStatement = connection.prepareStatement(Read_Name_Query);
            
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, Email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }

    /**
     * Metodo che ritorna i prodotti in base al nome e PCID
     * @param PCID intero che identifica il product category ID
     * @param name stringa che rappresenta il nome del prodotto da cercare
     * @return lista contenente i risultati trovati tra i prodotti
     */
    @Override
    public List getProductsByNameAndPCID(int PCID, String name, String Email) {
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
       
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_NameAndPCID_Query);
            preparedStatement.setString(1, "%"+name+"%");
            preparedStatement.setInt(2, PCID);
            preparedStatement.setString(3, Email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }
  
}
