/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe che permette la gestione del prodotto
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
   /**
    * Costruttore
    * @param PID ID univoco che identifica il prodotto 
    * @param name stringa che rappresenta il nome del prodotto
    * @param note stringa per note aggiuntive del prodotto
    * @param logo stringa per il logo del prodotto
    * @param photo stringa per la foto del prodotto
    * @param PCID intero che rappresenta il product category alla quale appartiene il prodotto 
    * @param email email del creatore del prodotto 
    */
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
    /**
     * Metodo che ritorna l'identificativo univoco del prodotto
     * @return intero che identifica il prodotto
     */
    public int getPID() {           
        return PID;        
    }        
    /**
     * Metodo che setta l'ID univoco per il prodotto
     */
    public void setPID() {              
        this.PID = PID;       
    }
    /**
     * Metodo che ritorna il nome del prodotto
     * @return stringa che rappresenta il prodotto
     */
    public String getName() {           
        return name;        
    }        
    /**
     * Metodo che setta il nome del prodotto 
     */
    public void setName() {               
        this.name = name;        
    }
    /**
     * Metodo che ritorna le note del prodotto
     * @return stringa che rappresenta le note del prodotto
     */
    public String getNote() {           
        return note;       
    }        
    /**
     * Metodo che setta le note aggiuntive del prodotto
     */
    public void setNote() {                
        this.note = note;        
    }
    /**
     * Metodo che ritorna il logo del prodotto
     * @return stringa che rappresenta il logo del prodotto
     */
    public String getLogo() {           
        return logo;       
    }        
    /**
     * Metodo che setta il logo del prodotto 
     */
    public void setLogo() {                
        this.logo = logo;        
    }
    /**
     * Metodo che ritorna la foto del prodotto 
     * @return stringa che rappresenta il nome della foto del prodotto
     */
    public String getPhoto() {           
        return photo;        
    }        
    /**
     * Metodo che setta la foto del prodotto 
     */
    public void setPhoto() {                
        this.photo = photo;        
    }
    /**
     * Metodo che ritorna il product category del prodotto
     * @return intero che rappresenta la categoria del prodotto
     */
    public int getPCID() {           
        return PCID;        
    }        
    /**
     * Metodo che setta la categoria del prodotto 
     */
    public void setPCID() {                
        this.PCID = PCID;        
    }
    /**
     * Metodo che ritorna l'email del creatore del prodotto
     * @return stringa che rappresenta l'email
     */
    public String getEmail() {           
        return email;        
    }        
    /**
     * Metodo che setta l'email del creatore del prodotto
     */
    public void setEmail() {                
        this.email = email;        
    }
}
