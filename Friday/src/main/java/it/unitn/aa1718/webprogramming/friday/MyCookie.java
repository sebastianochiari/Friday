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
public class MyCookie {
    
    // dati utente
    private int cookieID;
    private int LID;
    private String email = null;
    private Long deadline;
    
    public MyCookie(int cookieID, int LID, String email, Long deadline) {
        super();
        this.cookieID = cookieID;
        this.LID = LID;
        this.email = email;
        this.deadline = deadline;
    }
    
    public String getEmail() {        
        return email;    
    }    
    public void setEmail() {        
        this.email = email;    
    }
    public int getCookieID() {        
        return cookieID;    
    }    
    public void setCookieID() {        
        this.cookieID = cookieID;    
    }
    public int getLID() {        
        return LID;    
    }    
    public void setLID() {        
        this.LID = LID;    
    }
    public Long getDeadline() {        
        return deadline;    
    }    
    public void setDeadline() {        
        this.deadline = deadline;    
    }
}

