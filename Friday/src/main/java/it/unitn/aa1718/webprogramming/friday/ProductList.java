/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 *
 * @author leo97
 */
public class ProductList {
    
    int PID;
    int LID;
    int quantity;
    
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
