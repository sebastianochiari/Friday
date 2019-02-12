/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.*;
import java.util.List;

/**
 *
 * Interfaccia che permette la gestione e implementazione dei DAO per le categorie di prodotto
 */
public interface ProductCategoryDAO {
    
    /**
     * Metodo che ritorna tutte le categorie di prodotto
     * @return lista con tutte le categorie di prodotto
     */
    public List getAllProductCategories();
    
    /**
     * Metodo che ritorna tutti i prodotti in base all'email del creatore
     * @param Email stringa contenente l'email dell'utente
     * @return lista di prodotti trovati
     */
    public List getProductCategoriesByEmail(String Email);
    
    /**
     * Metodo che ritorna le categorie di prodotto in base al PCID
     * @param PCID intero che rappresenta le categorie di prodotto 
     * @return ritorna la categoria di prodotto trovata
     */
    public ProductCategory getProductCategory(int PCID);     
    
    /**
     * Metodo che permette la creazione di una categoria di prodotto
     * @param productCategory oggetto passato come parametro per la creazione della categoria di prodotto
     * @return stringa che rappresenta ?????
     */
    public String createProductCategory(ProductCategory productCategory);
    
    /**
     * Metodo che permette la modifica di una categoria di prodotto
     * @param productCategory oggetto passato come parametro
     * @return booleano settato a 1 se la modifica ha avuto successo
     */
    public boolean updateProductCategory(ProductCategory productCategory);
    
    /**
     * Metodo che elimina una categoria di prodotto
     * @param productCategory oggetto passato da eliminare
     * @return booleano che rappresenta il successo 1 oppure il fallimento 0 dell'eliminazione
     */
    public boolean deleteProductCategory(ProductCategory productCategory);
    
}
