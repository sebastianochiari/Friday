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
public class User {
    
    // dati utente
    private String email = null;
    private String password = null;
    private String name = null;
    private String surname = null;
    private String avatar = null;
    private boolean admin;
    private boolean list_owner;
    private int sharing_list[] = null;
    
    public User(String email, String password, String name, String surname, String avatar, boolean admin, boolean list_owner) {
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
        this.admin = admin;
        this.list_owner = list_owner;
        this.sharing_list = new int[100];
    }
    
    public String getEmail() {        
        return email;    
    }    
    public void setEmail() {        
        this.email = email;    
    }
    public String getPassword() {        
        return password;    
    }    
    public void setPassword() {        
        this.password = password;    
    }
    public String getName() {        
        return name;    
    }    
    public void setName() {        
        this.name = name;    
    }
    public String getSurname() {        
        return surname;    
    }    
    public void setSurname() {        
        this.surname = surname;    
    }
    public String getAvatar() {        
        return avatar;    
    }    
    public void setAvatar() {        
        this.avatar = avatar;    
    }
    public boolean getAdmin() {        
        return admin;    
    }    
    public void setAdmin() {        
        this.admin = admin;    
    }
    public boolean getListOwner() {        
        return list_owner;    
    }    
    public void setListOwner() {        
        this.list_owner = list_owner;    
    }
    public int[] getSharingList() {        
        return sharing_list;    
    }    
    public void setSharingList() {        
        this.sharing_list = sharing_list;    
    }
    
}
