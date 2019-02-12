/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe per gestire le condivisioni delle liste tra utenti
 */
public class Sharing {
    
    private int LID;
    private String email = null;
    private boolean modify;
    private boolean addRemProd;
    private boolean deleteList;
    
    /**
     * Costruttore 
     * @param email stringa che rappresenta l'email dell'utente con cui si condivide la lista
     * @param LID ID che identifica la lista
     * @param modify booleano che gestisce la modifica della lista 
     * @param add booleano che gestisce l'aggiunta della lista
     * @param delete booleano che gestisce l'eliminazione della lista
     */
    
    public Sharing(String email, int LID, boolean modify, boolean add, boolean delete) {
        super();
        this.LID = LID;
        this.email = email;
        this.modify = modify;
        this.addRemProd = add;
        this.deleteList = delete;
    }
    
    /**
     * Metodo che ritorna l'ID univoco della lista
     * @return intero che rappresentao l'ID della lista
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
     * Metodo che ritorna l'email dell'utente con cui si ha condiviso la lista
     * @return stringa che rappresenta l'email 
     */
    public String getEmail() {           
        return email;        
    }        
    
    /**
     * Metodo che setta l'email dell'utente con cui condividere la lista 
     */
    public void setEmail() {                
        this.email = email;        
    }
    
    /**
     * Metodo che ritorna un booleano per la modifica o meno della lista
     * @return booleano che se settato ad 1 specifica la modifica della lista
     */
    public boolean getModify() {           
        return modify;        
    }        
    
    /**
     * Metodo che setta il valore ad 1 in caso di modifica della lista
     */
    public void setModify() {                
        this.modify = modify;        
    }
    
    /**
     * Metodo che ritorna un booleano per l'aggiunta della lista da condividere
     * @return booleano
     */
    public boolean getAdd() {           
        return addRemProd;        
    }        
    
    /**
     * Metodo che setta l'aggiunta o meno della lista da condividere
     */
    public void setAdd() {                
        this.addRemProd = addRemProd;        
    }
    
    /**
     * Metodo che ritorna un valore per l'eliminazione della lista condivisa
     * @return booleano che se settato ad 1 determina l'eliminazione della lista
     */
    public boolean getDelete() {           
        return deleteList;        
    }        
    
    /**
     * Metodo che setta l'eliminazione della lista condivisa
     */
    public void setDelete() {                
        this.deleteList = deleteList;        
    }
    
}
