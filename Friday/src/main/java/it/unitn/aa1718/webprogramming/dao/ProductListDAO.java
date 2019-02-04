/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.ProductList;
import java.util.List;

/**
 *
 * Interfaccia che permette la gestione e implementazione dei DAO per i prodotti presenti in una lista
 */
public interface ProductListDAO {
    
    /**
     * Metodo che ritorna tutte le liste dei prodotti 
     * @return list che contiene tutte le liste di prodotti
     */
    public List getAllProductLists();
    
    /**
     * Metodo che ritorna tutti gli ID dei prodotti, in base all'ID della lista passata
     * @param LID intero che identifica la lista specifica in cui cercare
     * @return lista di interi 
     */
    public List getPIDsByLID(int LID);  
    
    /**
     * Metodo che ritorna il 
     * @param PID
     * @param LID
     * @return 
     */
    public ProductList getProductList(int PID, int LID);  
    
    /**
     * Metodo che crea 
     * @param productList
     * @return 
     */
    public String createProductList(ProductList productList);
    
    /**
     * 
     * @param productList
     * @return 
     */
    public boolean updateProductList(ProductList productList);
    
    /**
     * 
     * @param productList
     * @return 
     */
    public boolean deleteProductList(ProductList productList);
    
}
