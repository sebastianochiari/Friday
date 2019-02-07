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
public class SharingProduct {
    
    private int PID;
    private String email = null;
    
    public SharingProduct(String email, int PID){
        super();
        this.PID = PID;
        this.email = email;
    }
    
    public int getPID() {           
        return PID;        
    }        
    
    public void setPID() {              
        this.PID = PID;       
    }
    
    public String getEmail() {           
        return email;        
    }        
    
    public void setEmail() {                
        this.email = email;        
    }
    
}
