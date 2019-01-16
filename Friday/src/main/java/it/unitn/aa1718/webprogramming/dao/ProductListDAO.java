/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.ProductList;
import java.util.List;

/**
 *
 * @author leo97
 */
public interface ProductListDAO {
    
    public List getAllProductLists();
    
    public List getPIDsByLID(int LID);  
    
    public ProductList getProductList(int PID, int LID);  
    
    public String createProductList(ProductList productList);
    
    public boolean updateProductList(ProductList productList);
    
    public boolean deleteProductList(ProductList productList);
    
}
