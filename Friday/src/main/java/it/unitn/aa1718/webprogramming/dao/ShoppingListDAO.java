/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.ShoppingList;
import java.util.List;

/**
 *
 * Interfaccia che permette la gestione e implementazione dei DAO per le shopping list
 */
public interface ShoppingListDAO {
    
    /**
     * Metodo che ritorna tutte le liste della spesa
     * @return ritorna una lista che contiene tutte le liste conenute nel database
     */
    public List getAllShoppingLists();
    
    /**
     * Metodo che ritorna le liste della spesa 
     * @param LID intero che rappresenta la lista da ritornare 
     * @return la lista della spesa specifica in base all'ID, altrimenti null
     */
    public ShoppingList getShoppingList(int LID);  
    
    /**
     * Metodo che ritorna la lista della spesa in base all'email passata come parametro
     * @param list_owner stringa che rappresenta l'email, usata per identificare l'utente in modo univoco
     * @return ritorna una lista contenente le liste appartenenti all'utente specifico
     */
    public List getShoppingListsByOwner(String list_owner);
    
    /**
     * Metodo che ritorna le shopping list in base alla categoria
     * @param LCID intero che rappresenta la categoria di lista 
     * @return lista contenente tutte le liste della spesa 
     */
    public List getShoppingListsByCategory(int LCID);
    
    /**
     * Metodo che ritorna la lista della spesa dell'utente anonimo
     * @param CookieID intero che rappresenta il cookie dell'utente anonimo
     * @return la shopping list specifica dell'utente anonimo
     */
    public ShoppingList getAnonymusShoppingList(int CookieID);
    
    /**
     * Metodo che crea una lista della spesa 
     * @param shoppingList istanza della lista della spesa
     * @return l'ID della lista della spesa
     */
    public String createShoppingList(ShoppingList shoppingList);
    
    /**
     * Metodo che permette la modifica della lista della spesa
     * @param shoppingList istanza della lista della spesa da modificare
     * @return lista della spesa modificata
     */
    public boolean updateShoppingList(ShoppingList shoppingList);
    
    /**
     * Metodo che elimina la lista della spesa
     * @return booleano che determina l'eliminazione della lista della spesa
     */
    public boolean deleteExpiredShoppingLists();
    
    /**
     * Metodo che elimina la lista della spesa in base all'ID passato come parametro
     * @param LID intero che viene passato come parametro 
     * @return boolean uguale ad 1 se elimina la lista, false se non trova nulla
     */
    public boolean deleteShoppingList(int LID);
    
    /**
     * Metodo che permette la modifica dell'email di una lista della spesa 
     * @param LID intero che rappresenta l'ID univoco della lista
     * @param email stringa che rappresenta l'email da modificare
     */
    public void updateEmailShoppingList(int LID, String email);
    
    /**
     * Metodo che ritorna la lista della spesa in base all'email dell'utente o cookie dell'utente
     * @param email stringa che rappresenta l'email dell'utente
     * @param cookieID intero che rappresenta il cookie dell'utente
     * @return lista ritornata in base ai parametri passati in input
     */
    public List getShoppingListByUserIDOrCookieID(String email, int cookieID);
    
    /**
     * Metodo che ritorna tutte le liste della spesa 
     * @param email
     * @return 
     */
    public List getAllShoppingListEditable(String email);
    
    /**
     * Metodo che ritorna le liste della spesa 
     * @param email
     * @param cookieID
     * @return 
     */
    public ShoppingList getRandShoppingList(String email, int cookieID);
    
    
    
}
