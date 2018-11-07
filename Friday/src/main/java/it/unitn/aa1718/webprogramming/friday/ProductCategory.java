/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 *
 * @author marta
 */
public class ProductCategory {
    
    //dati categoria
    private int PCID;
    private String name = null;
    private String note = null;
    private String logo = null;
    private String email = null;
    
    public ProductCategory(int PCID, String name, String note, String logo, String email) {
        super();
        this.PCID = PCID;
        this.name = name;
        this.note = note;
        this.logo = logo;
        this.email = email;
    }
    
    public int getPCID() {           
        return PCID;        
    }        
    
    public void setPCID() {              
        this.PCID = PCID;       
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
   
    public String getLogo() {           
        return logo;       
    }        
    
    public void setLogo() {                
        this.logo = logo;        
    }
    
    public String getEmail() {           
        return email;        
    }        
    
    public void setEmail() {                
        this.email = email;        
    }
    
}
