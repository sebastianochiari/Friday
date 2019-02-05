/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.Message;
import java.util.Vector;

/**
 *
 * Interfaccia che permette la gestione e implementazione dei DAO per la messaggistica
 */
public interface MessageDAO {
    
    /**
     * Metodo che ritorna i messaggi in base all'ID della lista alla quale sono associati
     * @param LID intero associato alla lista
     * @return vettore con tutti i risultati trovati
     */
    public Vector<Message> getMessagesByLID(int LID);
    
    /**
     * Metodo che ritorna i messaggi in base all'ID
     * @param messageID intero che rappresenta il messaggio da ritornare
     * @return oggetto di tipo messaggio 
     */
    public Message getMessagesByID(int messageID);
    
    /**
     * Metodo che permette la creazione di un messaggio 
     * @param message oggetto da creare????
     */
    public void createMessage(Message message);
    
    /**
     * Metodo che permette la modifica di un messaggio
     * @param message oggetto da modificare
     */
    public void updateMessage(Message message);
    
    /**
     * Metodo che elimina i messaggi in base all'ID 
     * @param message messaggio da eliminare
     */
    public void deleteMessageByID(Message message);
    
    /**
     * Metodo che elimina i messaggi in base all'ID della lista alla quale sono associati
     * @param LID intero che rappresenta la lista
     */
    public void deleteMessageByLID(int LID);
    
}
