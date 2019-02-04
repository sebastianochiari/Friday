/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe che identifica la categoria alla quale appartiene il prodotto
 */
public class ProductCategory {
    
    //dati categoria
    private int PCID;
    private String name = null;
    private String note = null;
    private String logo = null;
    private String email = null;
    /**
     * Costruttore
     * @param PCID intero che identifica in modo univoco la categoria di prodotto
     * @param name
     * @param note
     * @param logo
     * @param email 
     */
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
