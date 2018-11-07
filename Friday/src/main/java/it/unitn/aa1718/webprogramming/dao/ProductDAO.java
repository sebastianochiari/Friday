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
    
    public List getProductsByName(String name);
    
    public List getProductsByNameAndPCID(int PCID, String name);
    
    public Product getProduct(int PID);     
    
    public String createProduct(Product product);
    
    public boolean updateProduct(Product product);
    
    public boolean deleteProduct(Product product);
}
