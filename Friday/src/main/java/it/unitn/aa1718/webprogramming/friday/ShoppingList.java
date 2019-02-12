/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe che identifica la lista 
 */
public class ShoppingList {
    
    //dati lista
    private int LID;
    private String name = null;
    private String note = null;
    private String image = null;
    private int LCID;
    private String list_owner = null;
    private int cookieID;
    /**
     * Costruttore 
     * @param LID Intero che rappresenta la primary key. Identifica in modo univoco la lista
     * @param name stringa che rappresenta il nome della lista 
     * @param note stringa per eventuali note aggiuntive 
     * @param image stringa che identifica il nome dell'immagine scelta per rappresentare la lista
     * @param LCID intero che rappresenta la categoria di lista alla quale appartiene la lista
     * @param list_owner stringa che rappresenta il nome dell'utente possessore della lista
     * @param cookieID intero che rappresenta il cookie associato all'utente  
     */
    public ShoppingList(int LID, String name, String note, String image, int LCID, String list_owner, int cookieID) {
        super();
        this.LID = LID;
        this.name = name;
        this.note = note;
        this.image = image;
        this.LCID = LCID;
        this.list_owner = list_owner;
        this.cookieID = cookieID;
    }
    /**
     * Metodo che ritorna l'identificativo univoco della lista
     * @return l'intero che rappresenta l'ID della lista
     */
    public int getLID() {           
        return LID;        
    }        
    /**
     * Metodo che setta l'ID della lista
     */
    public void setLID() {              
        this.LID = LID;       
    }
    /**
     * Metodo che ritorna il nome della  lista
     * @return stringa che rappresenta il nome  della lista
     */
    public String getName() {           
        return name;        
    }        
    /**
     * Metodo che setta il nome della lista 
     */
    public void setName() {               
        this.name = name;        
    }
    /**
     * Metodo che ritorna le note della lista
     * @return stringa che rappresenta le note della lista
     */
    public String getNote() {           
        return note;       
    }        
    /**
     * Metodo che setta le note della lista
     */
    public void setNote() {                
        this.note = note;        
    }
   /**
    * Metodo che ritorna l'immagine scelta per la lista
    * @return stringa che identifica il nome dell'immagine scelta per la lista
    */
    public String getImage() {           
        return image;       
    }        
    /**
     * Metodo che setta l'immagine della lista
     */
    public void setImage() {                
        this.image = image;        
    }
    /**
     * Metodo che ritorna l'ID della categoria di lista a cui appartiene la lista
     * @return intero che rappresenta la categoria di lista a cui appartiene
     */
    public int getLCID() {           
        return LCID;        
    }        
    /**
     * Metodo che setta la categoria di lista a cui appartiene la lista
     */
    public void setLCID() {              
        this.LCID = LCID;       
    }
    /**
     * Metodo che ritorna una stringa che rappresenta il list Owner della lista
     * @return stringa che rappresenta il possessore della lista
     */
    public String getListOwner() {           
        return list_owner;        
    }        
    /**
     * Metodo che setta l'owner della lista
     */
    public void setListOwner() {                
        this.list_owner = list_owner;        
    }
    /**
     * Metodo che ritorna un intero che rappresenta il cookie  dell'utente
     * @return 
     */
    public int getCookieID() {           
        return cookieID;        
    }        
    /**
     *  Metodo che setta il cookie dell'utente
     */
    public void setCookieID() {              
        this.cookieID = cookieID;       
    }
    
}
