/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.ShoppingList;
import java.util.List;

/**
 *
 * @author tommi
 */
public interface ShoppingListDAO {
    
    public List getAllList();
    
    public List getListByOwner(String list_owner);
    
    public ShoppingList getShoppingListCategories(int LCID);     
    
    public String createShoppingListCategories(ShoppingList productCategory);
    
    public boolean updateShoppingListCategories(ShoppingList productCategory);
    
    public boolean deleteShoppingListCategories(ShoppingList productCategory);
    
}
