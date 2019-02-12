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
     * @param name stringa che rappresenta il nome della categoria di prodotto
     * @param note stringa per eventuali note
     * @param logo stringa che rappresenta un logo della categoria di prodotto
     * @param email stringa che rappresenta l'email del creatore della categoria di prodotto
     */
    public ProductCategory(int PCID, String name, String note, String logo, String email) {
        super();
        this.PCID = PCID;
        this.name = name;
        this.note = note;
        this.logo = logo;
        this.email = email;
    }
    
    /**
     * Metodo che ritorna l'ID univoco della categoria di prodotto 
     * @return intero che rappresenta la categoria di prodotto
     */
    public int getPCID() {           
        return PCID;        
    }        
    
    /**
     * Metodo che setta l'ID univoco per la categoria di prodotto
     */
    public void setPCID() {              
        this.PCID = PCID;       
    }
    
    /**
     * Metodo che ritorna il nome della categoria di prodotto
     * @return stringa rappresentante il nome della categoria
     */
    public String getName() {           
        return name;        
    }        
    
    /**
     * Metodo che setta il nome della categoria di prodotto
     */
    public void setName() {               
        this.name = name;        
    }
    
    /**
     * Metodo che ritorna le note della categoria di prodotto specifica
     * @return stringa con le note della categoria di prodotto specificata
     */
    public String getNote() {           
        return note;       
    }        
    
    /**
     * Metodo che setta le note per la categoria di prodotto
     */
    public void setNote() {                
        this.note = note;        
    }
   
    /**
     * Metodo che ritorna il logo della categoria di prodotto
     * @return stringa che rappresenta il logo 
     */
    public String getLogo() {           
        return logo;       
    }        
    
    /**
     * Metodo che setta il logo della categoria di prodotto
     */
    public void setLogo() {                
        this.logo = logo;        
    }
    
    /**
     * Metodo che ritorna l'email del creatore della categoria di prodotto
     * @return stringa con l'email del creatore
     */
    public String getEmail() {           
        return email;        
    }        
    
    /**
     * Metodo che setta l'email del creatore della categoria di prodotto
     */
    public void setEmail() {                
        this.email = email;        
    }
    
}
