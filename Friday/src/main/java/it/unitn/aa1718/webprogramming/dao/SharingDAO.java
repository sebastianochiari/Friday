/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.Sharing;
import java.util.List;

/**
 *
 * Interfaccia che permette la gestione e implementazione dei DAO per le liste condivise 
 */
public interface SharingDAO {
    
    /**
     * Metodo che ritorna tutte le liste condivise
     * @return list con tutte le liste condivise
     */
    public List getAllSharing();
    
    /**
     * Metodo che ritorna tutte le liste in base all'email passata
     * @param email stringa passata come parametro 
     * @return list con tutte le liste 
     */
    public List getAllListByEmail(String email);
    
    /**
     * Metodo che ritorna tutte le liste in base all'email
     * @param LID intero passato come parametro identificativo univoco per la lista
     * @return lista contenente tutte le liste associate alla email passata come parametro
     */
    public List getAllEmailsbyList(int LID);  
    
    /**
     * Metodo che ritorna tutte le persone con la quale è stata condivisa la lista con l'ID specifico
     * @param LID intero che identifica una lista specifica
     * @param email stringa che rappresenta l'email dell'utente
     * @return istanza di tipo sharing 
     */
    public Sharing getSharing(int LID, String email);  
    
    /**
     * Metodo che crea una lista condivisa
     * @param sharing
     * @return stringa che rappresenta l'email del creatore
     */
    public String createSharing(Sharing sharing);
    
    /**
     * Mmetodo che permette la modifica di una lista condivisa
     * @param sharing istanza della lista da modificare
     * @return booleano che rappresenta la modifica corretta o meno della modifica
     */
    public boolean updateSharing(Sharing sharing);
    
    /**
     * Metodo che elimina una lista condivisa
     * @param sharing istanza della lista di eliminare 
     * @return booleano che verifica la corretta eliminazione della lista
     */
    public boolean deleteSharing(Sharing sharing);
    
}
