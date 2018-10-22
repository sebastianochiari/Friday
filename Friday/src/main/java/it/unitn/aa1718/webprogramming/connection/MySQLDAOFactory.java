/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.connection;

import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.dao.entities.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tommi
 */
public class MySQLDAOFactory extends DAOFactory {
 
    public static final String Driver = "com.mysql.cj.jdbc.Driver";
    
    public static final String DBUrl = "jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String User = "root";
    //public static final String Pass = "appa";
    public static final String Pass = "root";
    
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
    
    public static Connection getConnection (UserDAO userDao){
        Connection connection = null;
        
        return connection;
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
}
