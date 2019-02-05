/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe che rappresenta le categorie di lista presenti nel database
 */
public class ShoppingListCategory {
    
    //dati categoria di lista
    private int LCID;
    private String name = null;
    private String note = null;
    private String image = null;
    private String email = null;
    
    /**
     * Costruttore
     * @param LCID primary key della tabella categorie di liste. Identifica in modo univoco la categoria di lista
     * @param name nome della lista 
     * @param note sezione note per informazioni aggiuntive sulla categoria
     * @param image stringa che rappresenta il nome dell'immagina scelta dall'admin per rappresentare la cateogria di lsita
     * @param email email dell'admin creatore della categoria di lista
     */
    public ShoppingListCategory(int LCID, String name, String note, String image, String email) {
        super();
        this.LCID = LCID;
        this.name = name;
        this.note = note;
        this.image = image;
        this.email = email;
    }
    
    /**
     * Matodo che ritorna la primary key della categoria di lsita
     * @return intero che rappresenta la primary key
     */
    public int getLCID() {           
        return LCID;        
    }        
    /**
     * Metodo che setta l'ID della categoria di lista
     */
    public void setLCID() {              
        this.LCID = LCID;       
    }
    /**
     * Metodo che ritorna il nome della categoria di lista
     * @return stringa che rappresenta il nome della categoria
     */
    public String getName() {           
        return name;        
    }        
    /**
     * Metodo che setta il nome della categoria di lista
     */
    public void setName() {               
        this.name = name;        
    }
    /**
     * Metodo che ritorna le note aggiuntive per identificare la categoria di lista
     * @return stringa che rappresenta il testo delle note
     */
    public String getNote() {           
        return note;       
    }        
    /**
     * Metodo che setta le note per la categoria di lista
     */
    public void setNote() {                
        this.note = note;        
    }
    /**
    * Metodo che ritorna l'immagine scelta dall'admin per rappresentare la categoria di lista
    * @return stringa che rappresenta il nome dell'immagine
    */
    public String getImage() {           
        return image;       
    }        
    /**
     * Metodo che setta l'immagine della categoria di lista
     */
    public void setImage() {                
        this.image = image;        
    }
    /**
     * Metodo che ritorna l'email dell'admin che ha creato la categoria della lista
     * @return stringa che rappresenta l'email
     */
    public String getEmail() {           
        return email;        
    }        
    /**
     * Metodo che setta l'email per identificare il creatore della categoria
     */
    public void setEmail() {                
        this.email = email;        
    }
    
}
