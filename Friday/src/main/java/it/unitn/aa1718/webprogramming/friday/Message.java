/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;

/**
 * Classe che permette la gestione della messaggistica
 */
public class Message {
    
    UserDAO userDAO = new MySQLUserDAOImpl();
    
    int messageID;
    String sender = null;
    String name_sender = null;
    String surname_sender = null;
    String avatar = null;
    int LID;
    String text = null;
    
    /**
     * Costruttore
     * @param messageID identficativo univoco del messaggio
     * @param LID identificativo univoco per la lista alla quale è associato il messaggio
     * @param sender stringa che rappresenta il mittente
     * @param text stringa che contiene il testo del messaggio
     */
    
    public Message(int messageID, int LID, String sender, String text) {
        super();
        this.messageID = messageID;
        this.sender = sender;
        this.LID = LID;
        this.text = text;
        this.surname_sender = userDAO.getUser(sender).getSurname();
        this.name_sender = userDAO.getUser(sender).getName();
        this.avatar = userDAO.getUser(sender).getAvatar();
        
    }
    /**
     * Metodo che ritorna il mittente del messaggio
     * @return stringa che rappresenta il nome del mittente
     */
    public String getSender() {        
        return sender;    
    }    
    /**
     * Metodo per settare il mittente del messaggio
     */
    public void setSender(String sender) {        
        this.sender = sender;    
    }
    /**
     * Metodo che ritorna l'identificativo univoco della lista alla quale è associato
     * @return intero che rappresenta la lista associata
     */
    public int getLID() {        
        return LID;    
    }    
    /**
     * Metodo che setta la lista associata
     */
    public void setLID(int LID) {        
        this.LID = LID;    
    }
    /**
     * Metodo che ritorna il testo del messaggio 
     * @return stringa che rappresenta il testo del messaggio
     */
    public String getText() {        
        return text;    
    }    
    /**
     * Metodo che setta il testo del messaggio
     */
    public void setText(String Text) {        
        this.text = text;    
    }
    /**
     * Metodo che ritorna l'identificativo univoco del messaggio
     * @return intero che rappresenta l'ID univoco del messaggio
     */
    public int getMessageID() {        
        return messageID;    
    }    
    /**
     * Metodo che setta l'ID univoco del messaggio
     */
    public void setMessageID(int messageID) {        
        this.messageID = messageID;    
    }
    
    /**
     * Metodo che ritorna il mittente del messaggio
     * @return stringa che rappresenta il nome del mittente
     */
    public String getSenderName() {        
        return name_sender;    
    }    
    /**
     * Metodo per settare il mittente del messaggio
     */
    public void setSenderName(String name) {        
        this.name_sender = name;    
    }
    
       /**
     * Metodo che ritorna il mittente del messaggio
     * @return stringa che rappresenta il nome del mittente
     */
    public String getSenderSurnmae() {        
        return surname_sender;    
    }    
    /**
     * Metodo per settare il mittente del messaggio
     */
    public void setSenderSurname(String surname) {        
        this.surname_sender = surname;    
    }
    
    public String getAvatar() {        
        return surname_sender;    
    }    
    /**
     * Metodo per settare il mittente del messaggio
     */
    public void setAvatar(String avatar) {        
        this.avatar = avatar;    
    }
    
    
}
