/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.connection;

import it.unitn.aa1718.webprogramming.dao.*;
/**
 *
 * @author tommi
 */
public abstract class DAOFactory {
    
    public abstract UserDAO getUserDAO();
    
    public abstract ProductDAO getProductDAO();
    
    public abstract ProductCategoryDAO getProductCategoryDAO();
    
    public abstract ShoppingListDAO getShoppingListDAO();
    
    public abstract ShoppingListCategoryDAO getShoppingListCategoryDAO();
    
    public abstract MyCookieDAO getMyCookieDAO();
    
    public abstract ProductListDAO getProductListDAO();
    
    public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }
}
