/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe che si occupa della condivisione di un prodotto
 */
public class SharingProduct {
    
    private int PID;
    private String email = null;
    
    /**
     * Costruttore
     * @param email stringa che rappresenta l'utente con il quale si condivide il prodotto
     * @param PID intero univoco del prodotto da condividere
     */
    public SharingProduct(String email, int PID){
        super();
        this.PID = PID;
        this.email = email;
    }
    
    /**
     * Metodo che ritorna il PID del prodotto
     * @return intero rappresentante il PID del prodotto
     */
    public int getPID() {           
        return PID;        
    }        
    
    /**
     * Metodo che setta il PID del prodotto
     */
    public void setPID() {              
        this.PID = PID;       
    }
    
    /**
     * Metodo che ritorna l'email dell'utente con cui si condivide il prodotto
     * @return stringa
     */
    public String getEmail() {           
        return email;        
    }        
    
    /**
     * Metodo che setta l'email dell'utente con il quale si condivide il prodotto
     */
    public void setEmail() {                
        this.email = email;        
    }
    
}
