/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.*;
import java.util.List;

/**
 *
 * @author tommi
 */
public interface UserDAO {
    
    public List getAllUsers();
    
    public User getUser(String email);
    
    public String createUser(User user);
    
    public void updateUserByEmail(User user);
    
    public void updateUserByPassword(User user);
    
    public void confirmedUser(String email);
    
    public void deleteUser(User user);
    
    public Boolean checkUser (String email);
    
    public Boolean checkEmail (String email);
    
    public String getPasswordByUserEmail (String email);
  
}
