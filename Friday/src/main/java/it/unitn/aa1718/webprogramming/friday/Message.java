/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe che permette la gestione della messaggistica
 */
public class Message {
    
    int messageID;
    String sender = null;
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
    public void setSender() {        
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
    public void setLID() {        
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
    public void setText() {        
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
    public void setMessageID() {        
        this.messageID = messageID;    
    }
    
}
