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
 * @author marta (remozao) iconcina zao
 */
public interface ProductCategoryDAO {
    
    public List getAllProductCategories();
    
    public List getProductCategoriesByEmail(String Email);
    
    public ProductCategory getProductCategory(int PCID);     
    
    public String createProductCategory(ProductCategory productCategory);
    
    public boolean updateProductCategory(ProductCategory productCategory);
    
    public boolean deleteProductCategory(ProductCategory productCategory);
    
}
