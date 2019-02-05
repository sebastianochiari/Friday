/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe per gestire i cookie 
 */
public class MyCookie {
    
    // dati utente
    private int cookieID;
    private int LID;
    private String email = null;
    private Long deadline;
    
    /**
     * Costruttore
     * @param cookieID ID univoco che rappresenta il cookie
     * @param LID ID univoco che identifica la lista alla quale fa riferimento
     * @param email email per identificare l'utente alla quale è associato
     * @param deadline validità del cookie e scadenza 
     */
    public MyCookie(int cookieID, int LID, String email, Long deadline) {
        super();
        this.cookieID = cookieID;
        this.LID = LID;
        this.email = email;
        this.deadline = deadline;
    }
    /**
     * Metodo che ritorna l'email alla quale è associato il cookie
     * @return stringa che rappresenta l'utente 
     */
    public String getEmail() {        
        return email;    
    }    
    /**
     * Metodo che setta l'email per il cookie
     */
    public void setEmail() {        
        this.email = email;    
    }
    /**
     * Metodo che ritorna l'identificativo univoco del cookie
     * @return intero che identifica il cookie in modo univoco
     */
    public int getCookieID() {        
        return cookieID;    
    }    
    /**
     * Metodo che setta l'identificativo univoco del cookie
     */
    public void setCookieID() {        
        this.cookieID = cookieID;    
    }
    /**
     * Metodo che ritorna l'identificativo univoco alla quale è associato il cookie con le liste
     * @return intero che rappresenta la lista alla quale è associato
     */
    public int getLID() {        
        return LID;    
    }    
    /**
     * Metodo che setta la lista alla quale è associato il cookie
     */
    public void setLID() {        
        this.LID = LID;    
    }
    /**
     * Metodo che ritorna la scadenza del cookie
     * @return Long ??? int che rappresenta la durata di validità del cookie
     */
    public Long getDeadline() {        
        return deadline;    
    }    
    /**
     * Metodo che setta la deadline del cookie 
     */
    public void setDeadline() {        
        this.deadline = deadline;    
    }
}

