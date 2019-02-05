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
 * Interfaccia che permette la gestione e implementazione dei DAO per l'utente
 */
public interface UserDAO {
    
    /**
     * Metodo che effettua la query al database e ritorna tutti gli utenti presenti nella tabella user
     * @return 
     */
    public List getAllUsers();
    
    /**
     * Metodo che ritorna l'utente specifico in base all'email
     * @param email email passata come parametro per l'identificazione dell'utente da ritornare
     * @return ritorna un oggetto di tipo User con l'utente specifico in base ai parametri passati
     */
    public User getUser(String email);
    
    /** 
    * Metodo che permette la creazione dell'utente 
     * @param user oggetto di tipo user passato in input per la creazione di un utente con i parametri specifici
     * @return una stringa che WTF??????????????''
     */
    public String createUser(User user);
    
    /**
     * Metodo che permette la modifica delle informazioni dell'utente in base all'email
     * @param user oggetto user da modificare
     */
    public void updateUserByEmail(User user);
    
    /**
     * Metodo per modificare l'utente in base alla password 
     * @param user oggetto user da modificare
     */
    public void updateUserByPassword(User user);
    
    /**
     * Metodo che verifica la verifica dell'utente ???????
     * @param email 
     */
    public void confirmedUser(String email);
    
    /**
     * Metodo che permette l'eliminazione di un utente
     * @param user oggetto utente da eliminare
     */
    public void deleteUser(User user);
    
    /**
     * Metodo che permette il controllo dell'esistenza di un utente nel database 
     * @param email identificativo univoco dell'utente che permette il controllo nel database
     * @return ritorna un booleano: ritorna 1 se esise nel db
     */
    public Boolean checkUser (String email);
    
    /**
     * Metodo che permette il controllo del formato dell'email
     * @param email stringa email da controllare
     * @return email corretta ???????????????????????????
     */
    public Boolean checkEmail (String email);
    
    /**
     * Metodo che ritorna la password criptata dell'utente 
     * @param email stringa passata come parametro per effettuare la ricerca 
     * @return stringa che rappresenta la password presa dal db
     */
    public String getPasswordByUserEmail (String email);
  
}
