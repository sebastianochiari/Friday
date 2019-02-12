/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe per la gestione dei Prodotti in una lista
 */
public class ProductList {
    
    int PID;
    int LID;
    int quantity;
    
    /**
     * Costruttore
     * @param PID intero che rappresenta l'ID del prodotto
     * @param LID intero che rappresenta l'ID della lista
     * @param quantity intero che rappresenta la quantità di prodotto presente nella lista
     */
    public ProductList(int PID, int LID, int quantity) {
        super();
        this.PID = PID;
        this.LID = LID;
        this.quantity = quantity;
    }
    
    /**
     * Metodo che ritorna il PID del prodotto specifico
     * @return intero che rappresenta l'ID del prodotto
     */
    public int getPID() {        
        return PID;    
    }    
    
    /**
     * Metodo che setta l'ID del prodotto 
     */
    public void setPID() {        
        this.PID = PID;    
    }
    
    /**
     * Metodo che ritorna l'ID della lista a cui si sta facendo riferimento
     * @return intero che rappresenta l'ID della lista
     */
    public int getLID() {        
        return LID;    
    }    
    
    /**
     * Metodo che setta l'ID della lista
     */
    public void setLID() {        
        this.LID = LID;    
    }
    
    /**
     * Metodo che ritorna la quantità di prodotto presente nella lista
     * @return intero che rappresenta la quantità di prodotto presente
     */
    public int getQuantity() {        
        return quantity;    
    }    
    
    /**
     * Metodo che setta la quantità di prodotto presente nella lista
     */
    public void setQuantity() {        
        this.quantity = quantity;    
    }
    
}
