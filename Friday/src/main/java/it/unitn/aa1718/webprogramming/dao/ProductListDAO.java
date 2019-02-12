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
     * @return lista di prodotti 
     */
    public List getPIDsByLID(int LID);  
    
    /**
     * Metodo che ritorna il prodotto appartenente alla lista specificata in input
     * @param PID id del prodotto
     * @param LID id della lista
     * @return lista di prodotti corrispondenti ai criteri di ricerca
     */
    public ProductList getProductList(int PID, int LID);  
    
    /**
     * Metodo che crea una lista di prodotti
     * @param productList istanza di productlist
     * @return stringa che rappresenta il PID del prodotto creato
     */
    public String createProductList(ProductList productList);
    
    /**
     * Metodo che permette la modifica di un product list
     * @param productList
     * @return booleano che verifica la modifica corretta del product list
     */
    public boolean updateProductList(ProductList productList);
    
    /**
     * Metodo che permette l'eliminazione del product list passata in input
     * @param productList
     * @return booleano che verifica l'eliminazione corretta del product list passato in input
     */
    public boolean deleteProductList(ProductList productList);
    
}
