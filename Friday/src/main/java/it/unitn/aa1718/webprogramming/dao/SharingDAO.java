/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.Sharing;
import java.util.List;

/**
 *
 * @author leo97
 */
public interface SharingDAO {
    
    public List getAllSharing();
    
    public List getAllListByEmail(String email);
    
    public List getAllEmailsbyList(int LID);  
    
    public Sharing getSharing(int LID, String email);  
    
    public String createSharing(Sharing sharing);
    
    public boolean updateSharing(Sharing sharing);
    
    public boolean deleteSharing(Sharing sharing);
    
}
