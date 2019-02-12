/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.ProductListDAO;
import it.unitn.aa1718.webprogramming.friday.ProductList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO che permette la gestione delle liste dei prodotti
 */
public class MySQLProductListDAOImpl implements ProductListDAO{
    
    private static final String Create_Query = "INSERT INTO product_lists (PID, LID, quantity) VALUES (?, ?, ?)";
    
    private static final String Read_Query = "SELECT PID, LID, quantity FROM product_lists WHERE (PID = ? and LID = ?)";
    
    private static final String Read_PIDsbyLID_Query = "SELECT PID, LID, quantity FROM product_lists WHERE LID = ?";
    
    private static final String Read_All_Query = "SELECT PID, LID, quantity FROM product_lists";
        
    private static final String Update_Query = "UPDATE product_lists SET PID=?, LID=?, quantity=? WHERE (PID = ? and LID = ?)";
    
    private static final String Delete_Query = "DELETE FROM product_lists WHERE (PID = ? and LID = ?)";

    /**
     * Metodo che ritorna tutte le liste dei prodotti 
     * @return list che contiene tutte le liste di prodotti
     */
    @Override
    public List getAllProductLists() {
        
        List productLists = new ArrayList();
        ProductList productList = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                productList = new ProductList(result.getInt(1), result.getInt(2), result.getInt(3));
                productLists.add(productList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        
        return productLists;
    }

    /**
     * Metodo che ritorna tutti gli ID dei prodotti, in base all'ID della lista passata
     * @param LID intero che identifica la lista specifica in cui cercare
     * @return lista di prodotti 
     */
    @Override
    public List getPIDsByLID(int LID) {
        
        List productLists = new ArrayList();
        ProductList productList = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_PIDsbyLID_Query);
            preparedStatement.setInt(1, LID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                productList = new ProductList(result.getInt(1), result.getInt(2), result.getInt(3));
                productLists.add(productList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        
        return productLists;
        
    }

    /**
     * Metodo che ritorna il prodotto appartenente alla lista specificata in input
     * @param PID id del prodotto
     * @param LID id della lista
     * @return lista di prodotti corrispondenti ai criteri di ricerca
     */
    @Override
    public ProductList getProductList(int PID, int LID) {
        
        ProductList productList = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Query);
            preparedStatement.setInt(1, PID);
            preparedStatement.setInt(2, LID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                productList = new ProductList(result.getInt(1), result.getInt(2), result.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        
        return productList;
    }

    /**
     * Metodo che crea una lista di prodotti
     * @param productList istanza di productlist
     * @return stringa che rappresenta il PID del prodotto creato
     */
    @Override
    public String createProductList(ProductList productList) {
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
                     
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, productList.getPID());
            preparedStatement.setInt(2, productList.getLID());
            preparedStatement.setInt(3, productList.getQuantity());
            preparedStatement.execute();
            result = preparedStatement.getGeneratedKeys();
 
            if (result.next() && result != null) {
                return result.getString(1);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
 
        return null;
    }

    /**
     * Metodo che permette la modifica di un product list
     * @param productList
     * @return booleano che verifica la modifica corretta del product list
     */
    @Override
    public boolean updateProductList(ProductList productList) {
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, productList.getPID());
            preparedStatement.setInt(2, productList.getLID());
            preparedStatement.setInt(3, productList.getQuantity());
            preparedStatement.setInt(4, productList.getPID());
            preparedStatement.setInt(5, productList.getLID());
            preparedStatement.execute();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        
        return false;
    }

    /**
     * Metodo che permette l'eliminazione del product list passata in input
     * @param productList
     * @return booleano che verifica l'eliminazione corretta del product list passato in input
     */
    @Override
    public boolean deleteProductList(ProductList productList) {
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query);
            preparedStatement.setInt(1, productList.getPID());
            preparedStatement.setInt(2, productList.getLID());
            preparedStatement.execute();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        
        return false;
    }
    
}
