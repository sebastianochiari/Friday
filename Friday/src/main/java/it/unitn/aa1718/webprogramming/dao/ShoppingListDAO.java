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
    
    public ShoppingList getAnonymusShoppingList(int CookieID);
    
    /**
     * Metodo che crea una lista della spesa ???
     * @param shoppingList
     * @return 
     */
    public String createShoppingList(ShoppingList shoppingList);
    
    /**
     * Metodo che permette la modifica della lista della spesa
     * @param shoppingList 
     * @return 
     */
    public boolean updateShoppingList(ShoppingList shoppingList);
    
    public boolean deleteExpiredShoppingLists();
    
    /**
     * Metodo che elimina la lista della spesa in base all'ID passato come parametro
     * @param LID intero che viene passato come parametro 
     * @return boolean uguale ad 1 se elimina la lista, false se non trova nulla
     */
    public boolean deleteShoppingList(int LID);
    
    /**
     * Metodo che permette la modifica di una lista della spesa ????
     * @param LID
     * @param email 
     */
    public void updateEmailShoppingList(int LID, String email);
    
    public List getShoppingListByUserIDOrCookieID(String email, int cookieID);
    
    public List getAllShoppingListEditable(String email);
    
    public ShoppingList getRandShoppingList(String email, int cookieID);
    
    
    
}
