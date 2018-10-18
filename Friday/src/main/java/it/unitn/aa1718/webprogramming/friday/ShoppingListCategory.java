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
public class ShoppingListCategory {
    
    //dati categoria di lista
    private int LCID;
    private String name = null;
    private String note = null;
    private String image = null;
    private String email = null;
    
    public ShoppingListCategory(int LCID, String name, String note, String image, String email) {
        super();
        this.LCID = LCID;
        this.name = name;
        this.note = note;
        this.image = image;
        this.email = email;
    }
    
    public int getLCID() {           
        return LCID;        
    }        
    
    public void setLCID() {              
        this.LCID = LCID;       
    }
    
    public String getName() {           
        return name;        
    }        
    
    public void setName() {               
        this.name = name;        
    }
    
    public String getNote() {           
        return note;       
    }        
    
    public void setNote() {                
        this.note = note;        
    }
   
    public String getImage() {           
        return image;       
    }        
    
    public void setImage() {                
        this.image = image;        
    }
    
    public String getEmail() {           
        return email;        
    }        
    
    public void setEmail() {                
        this.email = email;        
    }
    
}
