/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author marta
 */
public class MySQLProductCategoryDAOImpl implements ProductCategoryDAO {
    
    private static final String Create_Query = "INSERT INTO product_categories (PCID, name, note, logo, email) VALUES (?, ?, ?, ?, ?)";
    
    private static final String Read_Query = "SELECT PCID, name, note, logo, email FROM product_categories WHERE PCID = ?";
    
    private static final String Read_Email_Query = "SELECT PCID, name, note, logo, email FROM product_categories WHERE email = ?";
    
    private static final String Read_All_Query = "SELECT PCID, name, note, logo, email FROM product_categories";
    
    private static final String Update_Query = "UPDATE product_categories SET (PCID=?, name=?, note=?, logo=?, email=?) WHERE PCID = ?)";
    
    private static final String Delete_Query = "DELETE FROM product_categories WHERE PCID = ?";
    
    private static final String PCIDbyProductCatName = "SELECT PCID FROM product_categories WHERE Name = ? ";
    
    
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
    
    
    
    //funzione per prendere prodotti da db in base a input scritto da utente e categoria di prodotto selezionato
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
