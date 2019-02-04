/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.ShoppingListDAO;
import it.unitn.aa1718.webprogramming.friday.ShoppingList;
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
public class MySQLShoppingListDAOImpl implements ShoppingListDAO{
    
    private static final String Create_Query = "INSERT INTO lists (LID, name, note, image, LCID, list_owner, cookieID )VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String Create_Query_Anonymous = "INSERT INTO lists (LID, name, note, image, LCID, list_owner, cookieID )VALUES (?, ?, ?, ?, ?, null, ?)";
    
    private static final String Read_Query = "SELECT LID, name, note, image, LCID, list_owner, cookieID FROM lists WHERE LID = ?";

    private static final String Read_CookieID_Query = "SELECT LID, name, note, image, LCID, list_owner, cookieID FROM lists WHERE cookieID = ?";
    
    private static final String Read_Email_Query = "SELECT LID, name, note, image, LCID, list_owner, cookieID FROM lists WHERE list_owner = ?";
    
    private static final String Read_LCID_Query = "SELECT LID, name, note, image, LCID, list_owner, cookieID FROM lists WHERE LCID = ?";
    
    private static final String Read_All_Query = "SELECT LID, name, note, image, LCID, list_owner, cookieID FROM lists";
    
    private static final String Update_Query = "UPDATE lists SET LID=?, name=?, note=?, image=?, LCID=?, list_owner=?, cookieID=? WHERE LID = ?";
    
    private static final String Update_Email_Query = "UPDATE lists SET list_owner = ? WHERE LID = ?";
    
    private static final String Delete_Query = "DELETE FROM lists WHERE LID = ?";
    
    private static final String Delete_Expired_Query = "DELETE FROM lists WHERE list_owner IS NULL AND cookieID IS NULL";
    
    @Override
    public List getAllShoppingLists() {
        
        List shoppingLists = new ArrayList();
        ShoppingList shoppingList = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                shoppingList = new ShoppingList(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getString(6), result.getInt(7));
                shoppingLists.add(shoppingList);
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
        
        return shoppingLists;
    }
    
    @Override
    public List getShoppingListsByOwner (String email) {
        
        List shoppingLists = new ArrayList();
        ShoppingList shoppingList = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Email_Query);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                shoppingList = new ShoppingList(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getString(6), result.getInt(7));
                shoppingLists.add(shoppingList);
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
        
        return shoppingLists;
    }
    
    @Override
    public List getShoppingListsByCategory(int LCID){
    
        List shoppingLists = new ArrayList();
        ShoppingList shoppingList = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_LCID_Query);
            preparedStatement.setInt(5, LCID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                shoppingList = new ShoppingList(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getString(6),result.getInt(7));
                shoppingLists.add(shoppingList);
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
        
        return shoppingLists;
    
    }
    
    @Override
    public ShoppingList getAnonymusShoppingList(int CookieID){
        
        ShoppingList shoppingList = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Read_CookieID_Query);
            preparedStatement.setInt(1, CookieID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            if (result.next() && result != null) {
                shoppingList = new ShoppingList(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getString(6), result.getInt(7));
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
 
        return shoppingList;
        
    }
  
    @Override
    public ShoppingList getShoppingList(int LID){
		
        ShoppingList shoppingList= null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Read_Query);
            preparedStatement.setInt(1, LID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            if (result.next() && result != null) {
                shoppingList = new ShoppingList(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getString(6), result.getInt(7));
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
 
        return shoppingList;
    }
    
    @Override
    public String createShoppingList(ShoppingList shoppingList) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        System.out.println("LID = "+shoppingList.getLID()+" name = "+shoppingList.getName()+" note = "+shoppingList.getNote()+" list owner = "+shoppingList.getListOwner()+" LCID = "+shoppingList.getLCID()+" COOKIEID = "+shoppingList.getCookieID());
        try {      
            
            conn = MySQLDAOFactory.createConnection();
            if(shoppingList.getListOwner() != null){
                preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(6, shoppingList.getListOwner());
                preparedStatement.setInt(7, shoppingList.getCookieID());
            }
            else{
                preparedStatement = conn.prepareStatement(Create_Query_Anonymous, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(6, shoppingList.getCookieID());
            }
            
            preparedStatement.setInt(1, shoppingList.getLID());
            preparedStatement.setString(2, shoppingList.getName());
            preparedStatement.setString(3, shoppingList.getNote());
            preparedStatement.setString(4, shoppingList.getImage());
            preparedStatement.setInt(5, shoppingList.getLCID());    
            
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
    public boolean updateShoppingList(ShoppingList shoppingList) {
		
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, shoppingList.getLID());
            preparedStatement.setString(2, shoppingList.getName());
            preparedStatement.setString(3, shoppingList.getNote());
            preparedStatement.setString(4, shoppingList.getImage());
            preparedStatement.setInt(5, shoppingList.getLCID());
            preparedStatement.setString(6, shoppingList.getListOwner());
            preparedStatement.setInt(7, shoppingList.getCookieID());
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
    public boolean deleteShoppingList(ShoppingList shoppingList) {
	Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query);
            preparedStatement.setInt(1, shoppingList.getLID());
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
    public boolean deleteExpiredShoppingLists(){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Expired_Query);
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
    public void updateEmailShoppingList(int LID, String email) {
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Email_Query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, LID);
            preparedStatement.execute();
            
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
        
    }
    
}
