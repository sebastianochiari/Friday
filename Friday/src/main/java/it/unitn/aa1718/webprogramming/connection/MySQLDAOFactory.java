/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.connection;

import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.dao.entities.*;
import it.unitn.aa1718.webprogramming.friday.MyCookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author tommi
 */
public class MySQLDAOFactory extends DAOFactory {
 
    public static final String Driver = "com.mysql.cj.jdbc.Driver";
    
    public static final String DBUrl = "jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String User = "root";
    public static final String Pass = "root81097";    
    
    public static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(Driver);
            connection = DriverManager.getConnection(DBUrl, User, Pass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return connection;
    }
    
    public static String getDBUrl (){
        return DBUrl;
    }
    
    public static String getDBUser (){
        return User;
    }
    
    public static String getDBPass (){
        return Pass;
    }
    
    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAOImpl();
    }
    
    @Override
    public ProductDAO getProductDAO() {
        return new MySQLProductDAOImpl();
    }
    
    @Override
    public ProductCategoryDAO getProductCategoryDAO() {
        return new MySQLProductCategoryDAOImpl();
    }
    
    @Override
    public ShoppingListDAO getShoppingListDAO() {
        return new MySQLShoppingListDAOImpl();
    }
    
    @Override
    public ShoppingListCategoryDAO getShoppingListCategoryDAO() {
        return new MySQLShoppingListCategoryDAOImpl();
    }
    
    @Override
    public MyCookieDAO getMyCookieDAO() {
        return new MySQLMyCookieDAOImpl();
    }
    
    @Override
    public ProductListDAO getProductListDAO() {
        return new MySQLProductListDAOImpl();
    }
    
    @Override
    public SharingDAO getSharingDAO() {
        return new MySQLSharingDAOImpl();
    }
}
