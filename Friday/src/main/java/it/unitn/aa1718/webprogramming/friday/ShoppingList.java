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
public class ShoppingList {
    
    //dati lista
    private int LID;
    private String name = null;
    private String note = null;
    private String image = null;
    private int LCID;
    private String list_owner = null;
    
    public ShoppingList(int LID, String name, String note, String image, int LCID, String list_owner) {
        super();
        this.LID = LID;
        this.name = name;
        this.note = note;
        this.image = image;
        this.LCID = LCID;
        this.list_owner = list_owner;
    }
    
    public int getLID() {           
        return LID;        
    }        
    
    public void setLID() {              
        this.LID = LID;       
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
    
    public int getLCID() {           
        return LCID;        
    }        
    
    public void setLCID() {              
        this.LCID = LCID;       
    }
    
    public String getListOwner() {           
        return list_owner;        
    }        
    
    public void setListOwner() {                
        this.list_owner = list_owner;        
    }
    
}
