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
public interface ProductDAO {
    
    public List getAllProducts();
    
    public List getProductsByEmail(String Email);
    
    public List getProductsByPCID(int PCID);
    
    public Product getProducts(int PID);     
    
    public String createProducts(Product product);
    
    public boolean updateProducts(Product product);
    
    public boolean deleteProducts(Product product);
}
