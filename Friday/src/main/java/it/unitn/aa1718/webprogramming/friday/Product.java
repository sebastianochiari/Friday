/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 *
 * @author tommi
 */
public class Product {
    
    // dati prodotto
    private int PID;
    private String name = null;
    private String note = null;
    private String logo = null;
    private String photo = null;
    private int PCID;
    private String email = null;
    
    public Product(int PID, String name, String note, String logo, String photo, int PCID, String email) {
        super();
        this.PID = PID;
        this.name = name;
        this.note = note;
        this.logo = logo;
        this.photo = photo;
        this.PCID = PCID;
        this.email = email;
    }
    
    public int getPID() {           
        return PID;        
    }        
    
    public void setPID() {              
        this.PID = PID;       
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
    
    public String getPhoto() {           
        return photo;        
    }        
    
    public void setPhoto() {                
        this.photo = photo;        
    }
    
    public int getPCID() {           
        return PCID;        
    }        
    
    public void setPCID() {                
        this.PCID = PCID;        
    }
    
    public String getEmail() {           
        return email;        
    }        
    
    public void setEmail() {                
        this.email = email;        
    }
}
