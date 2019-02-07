/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.connection;

import it.unitn.aa1718.webprogramming.dao.*;
/**
 * Classe astratta che permette la gestione dei DAO 
 */
public abstract class DAOFactory {
    
    /**
     * Metodo che ritorna il DAO dell'utente
     * @return oggetto di tipo UserDAO
     */
    public abstract UserDAO getUserDAO();
    
    /**
     * Metodo che ritorna il DAO del prodotto
     * @return oggetto di tipo ProductDAO
     */
    public abstract ProductDAO getProductDAO();
    
    /**
     * Metodo che ritorna il DAO per la categoria di prodotto
     * @return oggetto di tipo ProductCategoryDAO
     */
    public abstract ProductCategoryDAO getProductCategoryDAO();
    
    /**
     * Metodo che ritorna il DAO della lista della spesa
     * @return oggetto di tipo shoppingListDAO
     */
    public abstract ShoppingListDAO getShoppingListDAO();
    
    /**
     * Metodo che ritorna il DAO per la categoria di lista della spesa
     * @return oggetto di tipo shoppingListCategoryDAO
     */
    public abstract ShoppingListCategoryDAO getShoppingListCategoryDAO();
    
    /**
     * Mmetodo che ritorna il DAO del cookie
     * @return oggetto di tpo MyCookieDAO
     */
    public abstract MyCookieDAO getMyCookieDAO();
    
    /**
     * Metodo che ritorna il DAO della lista di prodotti 
     * @return oggetto di tipo ProductListDAO
     */
    public abstract ProductListDAO getProductListDAO();
    
    /**
     * Metodo che ritorna il DAO per le liste condivise ???
     * @return oggetto di tipo SharingDAO
     */
    public abstract SharingDAO getSharingDAO();
    
    public abstract SharingProductDAO getSharingProductDAO();
    
    /**
     * Metodo che ritorna il DAO del messaggio
     * @return oggetto di tipo MessageDAO
     */
    public abstract MessageDAO getMessageDAO();
    
    
    public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }
}
