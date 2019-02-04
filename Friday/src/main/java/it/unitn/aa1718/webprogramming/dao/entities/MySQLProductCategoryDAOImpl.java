/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.friday.ProductCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO che permette la gestione di categorie di prodotto
 */
public class MySQLProductCategoryDAOImpl implements ProductCategoryDAO {
    
    private static final String Create_Query = "INSERT INTO product_categories (PCID, name, note, logo, email) VALUES (?, ?, ?, ?, ?)";
    
    private static final String Read_Query = "SELECT PCID, name, note, logo, email FROM product_categories WHERE PCID = ?";
    
    private static final String Read_Email_Query = "SELECT PCID, name, note, logo, email FROM product_categories WHERE email = ?";
    
    private static final String Read_All_Query = "SELECT PCID, name, note, logo, email FROM product_categories";
    
    private static final String Update_Query = "UPDATE product_categories SET (PCID=?, name=?, note=?, logo=?, email=?) WHERE PCID = ?)";
    
    private static final String Delete_Query = "DELETE FROM product_categories WHERE PCID = ?";
    
    private static final String PCIDbyProductCatName = "SELECT PCID FROM product_categories WHERE Name = ? ";
    
    
    /**
     * Metodo che ritorna tutte le categorie di prodotto
     * @return lista con tutte le categorie di prodotto
     */
    @Override
    public List getAllProductCategories() {
        
        List productCategories = new ArrayList();
        ProductCategory productCategory = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                productCategory = new ProductCategory(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                productCategories.add(productCategory);
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
        
        return productCategories;
    }
    
    /**
     * Metodo che ritorna tutti i prodotti in base all'email del creatore
     * @param email stringa contenente l'email dell'utente
     * @return lista di prodotti trovati
     */
    @Override
    public List getProductCategoriesByEmail (String email) {
        
        List productCategories = new ArrayList();
        ProductCategory productCategory = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Email_Query);
            preparedStatement.setString(5, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                productCategory = new ProductCategory(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                productCategories.add(productCategory);
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
        
        return productCategories;
    }
    
    /**
     * Metodo che ritorna le categorie di prodotto in base al PCID
     * @param PCID intero che rappresenta le categorie di prodotto 
     * @return ritorna la categoria di prodotto trovata
     */
    @Override
    public ProductCategory getProductCategory(int PCID) {
		
        ProductCategory productCategory= null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Read_Query);
            preparedStatement.setInt(1, PCID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            if (result.next() && result != null) {
                productCategory = new ProductCategory(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
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
 
        return productCategory;
    }
    
    /**
     * Metodo che permette la creazione di una categoria di prodotto
     * @param productCategory oggetto passato come parametro per la creazione della categoria di prodotto
     * @return stringa che rappresenta ?????
     */
    @Override
    public String createProductCategory(ProductCategory productCategory) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, productCategory.getPCID());
            preparedStatement.setString(2, productCategory.getName());
            preparedStatement.setString(3, productCategory.getNote());
            preparedStatement.setString(4, productCategory.getLogo());
            preparedStatement.setString(5, productCategory.getEmail());
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
     * Metodo che permette la modifica di una categoria di prodotto
     * @param productCategory oggetto passato come parametro
     * @return booleano settato a 1 se la modifica ha avuto successo
     */
    @Override
    public boolean updateProductCategory(ProductCategory productCategory) {
		
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, productCategory.getPCID());
            preparedStatement.setString(2, productCategory.getName());
            preparedStatement.setString(3, productCategory.getNote());
            preparedStatement.setString(4, productCategory.getLogo());
            preparedStatement.setString(5, productCategory.getEmail());
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
     * Metodo che elimina una categoria di prodotto
     * @param productCategory oggetto passato da eliminare
     * @return booleano che rappresenta il successo 1 oppure il fallimento 0 dell'eliminazione
     */
    @Override
    public boolean deleteProductCategory(ProductCategory productCategory) {
	Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query);
            preparedStatement.setInt(1, productCategory.getPCID());
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
     * Metodo che ritorna i prodotti in base all'input scritto dall'utente e categoria di prodotto selezionata
     * @param pcategory stringa che rappresenta la categoria di prodotto
     * @return intero che rappresenta il product category ID trovato
     */
    public int getPCIDbyCategoryName (String pcategory){
        int PCID = -1;
       
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(PCIDbyProductCatName);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            result.next();
            while (result.next()) {
          //      PCID = new ProductCategory(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
            
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
        
        return PCID;
    }

  
    
}
