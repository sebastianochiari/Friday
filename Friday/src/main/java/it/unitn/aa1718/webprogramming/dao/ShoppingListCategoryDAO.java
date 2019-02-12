/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.ShoppingListCategory;
import java.util.List;

/**
 *
 * Interfaccia che permette la gestione e implementazione dei DAO per le categorie di lista
 */
public interface ShoppingListCategoryDAO {
    
    /**
     * Metodo che ritorna tutte le categorie di lista della spesa
     * @return list contenente tutte le liste della spesa presenti nel db
     */
    public List getAllShoppingListCategories();
    
    /**
     * Metodo che ritorna le liste in base alle categorie di lista  
     * @param LCID intero che rappresenta la categoria di lista 
     * @return la lista in base alla categoria di lista della spesa
     */
    public ShoppingListCategory getShoppingListCategory(int LCID);  
    
    /**
     * Metodo che ritorna le categorie di lista in base all'email passata come parametro
     * @param email stringa che rappresenta l'email dell'utente 
     * @return list contenente l'insieme delle liste trovate
     */
    public List getShoppingListCategoriesByEmail(String email);
    
    /**
     * Metodo che crea la categoria di lista della spesa
     * @param shoppingListCategory oggetto list category passato in input
     * @return stringa che rappresenta l'ID della categoria di lista della spesa 
     */
    public String createShoppingListCategory(ShoppingListCategory shoppingListCategory);
    
    /**
     * Metodo che modifica la categoria di lista 
     * @param shoppingListCategory oggetto shopping list da modificare
     * @return booleano settato a 1 se la modifica ha avuto successo
     */
    public boolean updateShoppingListCategory(ShoppingListCategory shoppingListCategory);
    
    /**
     * Metodo che elimina una categoria di lista della spesa 
     * @param shoppingListCategory oggetto da eliminare
     * @return booleano settato a 1 se l'eliminazione ha avuto successo
     */
    public boolean deleteShoppingListCategory(ShoppingListCategory shoppingListCategory);
    
}
