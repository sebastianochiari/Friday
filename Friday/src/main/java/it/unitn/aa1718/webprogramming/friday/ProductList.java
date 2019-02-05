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
     * @param PID intero che rappresenta 
     * @param LID 
     * @param quantity 
     */
    public ProductList(int PID, int LID, int quantity) {
        super();
        this.PID = PID;
        this.LID = LID;
        this.quantity = quantity;
    }
    
    public int getPID() {        
        return PID;    
    }    
    public void setPID() {        
        this.PID = PID;    
    }
    
    public int getLID() {        
        return LID;    
    }    
    public void setLID() {        
        this.LID = LID;    
    }
    
    public int getQuantity() {        
        return quantity;    
    }    
    public void setQuantity() {        
        this.quantity = quantity;    
    }
    
}
