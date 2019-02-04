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
public class Sharing {
    
    private int LID;
    private String email = null;
    private boolean modify;
    private boolean addRemProd;
    private boolean deleteList;
    
    
    public Sharing(String email, int LID, boolean modify, boolean add, boolean delete) {
        super();
        this.LID = LID;
        this.email = email;
        this.modify = modify;
        this.addRemProd = add;
        this.deleteList = delete;
    }
    
    public int getLID() {           
        return LID;        
    }        
    
    public void setLID() {              
        this.LID = LID;       
    }
    
    public String getEmail() {           
        return email;        
    }        
    
    public void setEmail() {                
        this.email = email;        
    }
    
    public boolean getModify() {           
        return modify;        
    }        
    
    public void setModify() {                
        this.modify = modify;        
    }
    
    public boolean getAdd() {           
        return addRemProd;        
    }        
    
    public void setAdd() {                
        this.addRemProd = addRemProd;        
    }
    
    public boolean getDelete() {           
        return deleteList;        
    }        
    
    public void setDelete() {                
        this.deleteList = deleteList;        
    }
    
}
