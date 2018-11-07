/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.ShoppingListCategoryDAO;
import it.unitn.aa1718.webprogramming.friday.ShoppingListCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leo97
 */
public class MySQLShoppingListCategoryDAOImpl implements ShoppingListCategoryDAO{
    
    private static final String Create_Query = "INSERT INTO list_categories (LCID, name, note, image, email) VALUES (?, ?, ?, ?, ?)";
    
    private static final String Read_Query = "SELECT LCID, name, note, image, email FROM list_categories WHERE LCID = ?";
    
    private static final String Read_Email_Query = "SELECT LCID, name, note, image, email FROM list_categories WHERE email = ?";
    
    private static final String Read_All_Query = "SELECT LCID, name, note, image, email FROM list_categories";
    
    private static final String Update_Query = "UPDATE list_categories SET (LCID=?, name=?, note=?, image=?, email=?) WHERE LCID = ?)";
    
    private static final String Delete_Query = "DELETE FROM list_categories WHERE LCID = ?";
    
    @Override
    public List getAllShoppingListCategories() {
        
        List shoppingListCategories = new ArrayList();
        ShoppingListCategory shoppingListCategory = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                shoppingListCategory = new ShoppingListCategory(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                shoppingListCategories.add(shoppingListCategory);
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
        
        return shoppingListCategories;
    }
    
    @Override
    public List getShoppingListCategoriesByEmail (String email) {
        
        List shoppingListCategories = new ArrayList();
        ShoppingListCategory shoppingListCategory = null;
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
                shoppingListCategory = new ShoppingListCategory(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                shoppingListCategories.add(shoppingListCategory);
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
        
        return shoppingListCategories;
    }
  
    @Override
    public ShoppingListCategory getShoppingListCategory(int LCID){
		
        ShoppingListCategory shoppingListCategory= null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Read_Query);
            preparedStatement.setInt(1, LCID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            if (result.next() && result != null) {
                shoppingListCategory = new ShoppingListCategory(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
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
 
        return shoppingListCategory;
    }
    
    @Override
    public String createShoppingListCategory(ShoppingListCategory shoppingListCategory) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, shoppingListCategory.getLCID());
            preparedStatement.setString(2, shoppingListCategory.getName());
            preparedStatement.setString(3, shoppingListCategory.getNote());
            preparedStatement.setString(4, shoppingListCategory.getImage());
            preparedStatement.setString(5, shoppingListCategory.getEmail());
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
    public boolean updateShoppingListCategory(ShoppingListCategory shoppingListCategory) {
		
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, shoppingListCategory.getLCID());
            preparedStatement.setString(2, shoppingListCategory.getName());
            preparedStatement.setString(3, shoppingListCategory.getNote());
            preparedStatement.setString(4, shoppingListCategory.getImage());
            preparedStatement.setString(5, shoppingListCategory.getEmail());
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
    public boolean deleteShoppingListCategory(ShoppingListCategory shoppingListCategory) {
	Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query);
            preparedStatement.setInt(1, shoppingListCategory.getLCID());
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
    
}
