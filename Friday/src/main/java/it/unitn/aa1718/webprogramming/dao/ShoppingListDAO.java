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
    
    public List getAllShoppingLists();
    
    public ShoppingList getShoppingList(int LID);  
    
    public List getShoppingListsByOwner(String list_owner);
    
    public List getShoppingListsByCategory(int LCID);
    
    public String createShoppingList(ShoppingList shoppingList);
    
    public boolean updateShoppingList(ShoppingList shoppingList);
    
    public boolean deleteShoppingList(ShoppingList shoppingList);
    
}
