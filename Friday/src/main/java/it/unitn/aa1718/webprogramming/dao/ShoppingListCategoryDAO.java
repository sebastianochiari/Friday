/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.ShoppingListCategory;
import java.util.List;

/**
 *
 * @author leo97
 */
public interface ShoppingListCategoryDAO {
    
    public List getAllShoppingListCategories();
    
    public ShoppingListCategory getShoppingListCategory(int LID);  
    
    public List getShoppingListCategoriesByEmail(String email);
    
    public String createShoppingListCategory(ShoppingListCategory shoppingListCategory);
    
    public boolean updateShoppingListCategory(ShoppingListCategory shoppingListCategory);
    
    public boolean deleteShoppingListCategory(ShoppingListCategory shoppingListCategory);
    
}
