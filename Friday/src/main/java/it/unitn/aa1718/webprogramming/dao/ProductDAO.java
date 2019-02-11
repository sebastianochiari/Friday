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
 * Interfaccia che permette la gestione e implementazione dei DAO per i prodotti
 */
public interface ProductDAO {
    
    /**
     * Metodo che ritorna tutti i prodotti 
     * @return lsita con tutti i prodotti
     */
    public List getAllProducts(String Email);
    
    /**
     * Metodo che ritorna i prodotti in base all'email 
     * @param email stringa che rappresenta una delle email di un utente
     * @return list di prodotti creati dall'email passata in input
     */
    public List getProductsByEmail(String Email);
    
    /**
     * Metodo che ritorna i prodotti in base al product category ID passato
     * @param PCID intero che rappresenta la categoria di prodotto specificata
     * @return list di prodotti
     */
    public List getProductsByPCID(int PCID, String Email);
    
    /**
     * Metodo che ritorna i prodotti in base alla ricerca per nome
     * @param name stringa che identifica il nome
     * @param perPCID booleano che specifica se la ricerca va fatta per PCID
     * @return list contenente i prodotti trovati
     */
    public List getProductsByName(String name, boolean perPCID, String Email);
    
    /**
     * Metodo che ritorna i prodotti in base al nome e PCID
     * @param PCID intero che identifica il product category ID
     * @param name stringa che rappresenta il nome del prodotto da cercare
     * @return lista contenente i risultati trovati tra i prodotti
     */
    public List getProductsByNameAndPCID(int PCID, String name, String Email);
    
    /**
     * Metodo che ritorna un prodotto in base al suo ID univoco
     * @param PID intero che rappresenta il prodotto
     * @return oggetto che rappresenta il prodotto
     */
    public Product getProduct(int PID, String Email);     
    
    /**
     * Metodo che crea un prodotto 
     * @param product oggetto passato per creare il prodotto
     * @return stringa contenente l'ID del prodotto ????????
     */
    public String createProduct(Product product);
    
    /**
     * Metodo che modifica i campi del prodotto
     * @param product oggetto con i relativi campi da modificare
     * @return boolean settato a 1 se la modifica ha avuto successo
     */
    public boolean updateProduct(Product product);
    
    /**
     * Metodo che elimina un prodotto
     * @param product oggetto da eliminare
     * @return boolean settato a 1 se l'eliminazione ha avuto successo
     */
    public boolean deleteProduct(Product product);
}
