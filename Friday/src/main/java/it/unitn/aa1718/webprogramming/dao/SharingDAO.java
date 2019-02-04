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
     * @return list con tutte le liste condivise ???
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
     * @param LID
     * @param email
     * @return 
     */
    public Sharing getSharing(int LID, String email);  
    
    /**
     * Metodo che ????
     * @param sharing
     * @return 
     */
    public String createSharing(Sharing sharing);
    
    /**
     * Mmetodo che ??????????
     * @param sharing
     * @return 
     */
    public boolean updateSharing(Sharing sharing);
    
    /**
     * Metodo che ????????
     * @param sharing
     * @return 
     */
    public boolean deleteSharing(Sharing sharing);
    
}
