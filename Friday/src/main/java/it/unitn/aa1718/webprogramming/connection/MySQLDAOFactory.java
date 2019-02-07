/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
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
 * Classe DAO che permette la gestione dei DAO
 */



public class MySQLDAOFactory extends DAOFactory {
 
    public static final String Driver = "com.mysql.cj.jdbc.Driver";
    
    public static final String DBUrl = "jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String User = "root";
    public static final String Pass = "root81097";    
    
    /**
     * Metodo che crea la connessione con il db
     * @return ritorna un oggetto di tipo connection
     */
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
    
    /**
     * 
     * @return 
     */
    public static String getDBDriver (){
        return Driver;
    }
    
    /**
     * Metodo che ritorna una stringa contenente l'URL del database
     * @return string che contiene l'URL del db
     */
    public static String getDBUrl (){
        return DBUrl;
    }
    
    /**
     * Metodo che ritorna il nome dell'Utente
     * @return stringa con il nome dell'utente del db ???
     */
    public static String getDBUser (){
        return User;
    }
    
    /**
     * Metodo che ritorna la password del db
     * @return stringa contenente la password
     */
    public static String getDBPass (){
        return Pass;
    }
    
    /**
     * Metodo che ritorna il DAO dell'utente
     * @return oggetto di tipo UserDAO
     */
    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAOImpl();
    }
    
    /**
     * Metodo che ritorna il DAO del prodotto
     * @return oggetto di tipo ProductDAO
     */
    @Override
    public ProductDAO getProductDAO() {
        return new MySQLProductDAOImpl();
    }
    
    /**
     * Metodo che ritorna il DAO per la categoria di prodotto
     * @return oggetto di tipo ProductCategoryDAO
     */
    @Override
    public ProductCategoryDAO getProductCategoryDAO() {
        return new MySQLProductCategoryDAOImpl();
    }
    
    /**
     * Metodo che ritorna il DAO della lista della spesa
     * @return oggetto di tipo shoppingListDAO
     */
    @Override
    public ShoppingListDAO getShoppingListDAO() {
        return new MySQLShoppingListDAOImpl();
    }
    
    /**
     * Metodo che ritorna il DAO per la categoria di lista della spesa
     * @return oggetto di tipo shoppingListCategoryDAO
     */
    @Override
    public ShoppingListCategoryDAO getShoppingListCategoryDAO() {
        return new MySQLShoppingListCategoryDAOImpl();
    }
    
    /**
     * Mmetodo che ritorna il DAO del cookie
     * @return oggetto di tpo MyCookieDAO
     */
    @Override
    public MyCookieDAO getMyCookieDAO() {
        return new MySQLMyCookieDAOImpl();
    }
    
    /**
     * Metodo che ritorna il DAO della lista di prodotti 
     * @return oggetto di tipo ProductListDAO
     */
    @Override
    public ProductListDAO getProductListDAO() {
        return new MySQLProductListDAOImpl();
    }
    
    /**
     * Metodo che ritorna il DAO per le liste condivise ???
     * @return oggetto di tipo SharingDAO
     */
    @Override
    public SharingDAO getSharingDAO() {
        return new MySQLSharingDAOImpl();
    }
    
    /**
     * Metodo che ritorna il DAO del messaggio
     * @return oggetto di tipo MessageDAO
     */
    @Override
    public MessageDAO getMessageDAO() {
        return new MySQLMessageDAOImpl();
    }

    @Override
    public SharingProductDAO getSharingProductDAO() {
        return new MySQLSharingProductDAOImpl();
    }
}
