/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.SharingProduct;
import java.util.List;

/**
 *
 * @author leo97
 */
public interface SharingProductDAO {
    
    public List getAllSharingProduct();
    
    public List getAllProductByEmail(String email);
    
    public List getAllEmailsbyPID(int PID);
    
    public SharingProduct getSharingProduct(int PID, String email);  
    
    public String createSharingProduct(SharingProduct sharing);
    
    public boolean updateSharingProduct(SharingProduct sharing);
    
    public boolean deleteSharingProduct(SharingProduct sharing);
    
}
