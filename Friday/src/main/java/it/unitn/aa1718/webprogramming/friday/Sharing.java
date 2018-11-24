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
public class Sharing {
    
    private int LID;
    private String email = null;
    
    public Sharing(String email, int LID) {
        super();
        this.LID = LID;
        this.email = email;
    }
    
    public int getLID() {           
        return LID;        
    }        
    
    public void setLID() {              
        this.LID = LID;       
    }
    
    public String getEmail() {           
        return email;        
    }        
    
    public void setEmail() {                
        this.email = email;        
    }
    
}
