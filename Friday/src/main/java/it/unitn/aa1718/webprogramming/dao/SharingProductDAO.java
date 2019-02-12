/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.SharingProduct;
import java.util.List;

/**
 * Interfaccia che permette la gestione e implementazione dei DAO per i prodotti condivisi
 */
public interface SharingProductDAO {
    
    /**
     * Metodo che ritorna tutti i prodotti condivisi
     * @return lista di prodotti condivisi
     */
    public List getAllSharingProduct();
    
    /**
     * Metodo che ritorna tutti i prodotti condivisi dell'utente specifico
     * @param email identificativo univoco dell'utente
     * @return lista di prodotti che corrispondono ai criteri di ricerca
     */
    public List getAllProductByEmail(String email);
    
    /**
     * Metodo che ritorna tutte le email in base all'ID del prodotto specificato in input
     * @param PID id del prodotto
     * @return lista di emails trovate
     */
    public List getAllEmailsbyPID(int PID);
    
    /**
     * Metodo che ritorna i prodotti condivisi in base all'ID del prodotto e l'email
     * @param PID id univoco del prodotto
     * @param email email dell'utente
     * @return lista che corrisponde ai criteri di ricerca
     */
    public SharingProduct getSharingProduct(int PID, String email);  
    
    /**
     * Metodo che permette la creazione di prodotti condivisi
     * @param sharing istanza per la creazione del prodotto
     * @return stringa che rappresenta l'email dell'utente creatore
     */
    public String createSharingProduct(SharingProduct sharing);
    
    /**
     * Metodo che permette la modifica di un prodotto condiviso
     * @param sharing prodotto da modificare
     * @return booleano che verifica la modifica effettuata o no del prodotto
     */
    public boolean updateSharingProduct(SharingProduct sharing);
    
    /**
     * Metodo che permette l'eliminazione di un prodotto condiviso 
     * @param sharingProduct prodotto da eliminare
     * @return booleano che verifica l'eliminazione corretta o no del prodotto
     */
    public boolean deleteSharingProduct(SharingProduct sharing);
    
}
