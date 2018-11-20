/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.friday.*;
import it.unitn.aa1718.webprogramming.dao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tommi
 */
public class MySQLProductDAOImpl implements ProductDAO {
    
    private static final String Create_Query = "INSERT INTO products (PID, name, note, logo, photo, PCID, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String Read_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE PID = ?";
    
    private static final String Read_Email_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE email = ? ORDER BY name";
    
    private static final String Read_PCID_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE PCID = ? ORDER BY name";
    
    private static final String Read_Name_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products WHERE Name LIKE ? ORDER BY name";
    
    private static final String Read_NameAndPCID_Query = "SELECT * FROM fridaydb.products WHERE ((Name LIKE ?) AND (PCID = ?)) ORDER BY Name;";
    
    private static final String Read_All_Query = "SELECT PID, name, note, logo, photo, PCID, email FROM products ORDER BY name";
    
    private static final String Read_All_Query_Order_By_PCID = "SELECT PID, name, note, logo, photo, PCID, email FROM products ORDER BY PCID, name";
    
    private static final String Update_Query = "UPDATE products SET (PID=?, name=?, note=?, logo=?, photo=?, PCID=?, email=?) WHERE PID = ?)";
    
    private static final String Delete_Query = "DELETE FROM prpducts WHERE PID = ?";
    
    @Override
    public List getAllProducts(String order) {
        
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            if(order == "per categoria")
                preparedStatement = connection.prepareStatement(Read_All_Query_Order_By_PCID);
            else
                preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }
    
    @Override
    public List getProductsByEmail (String email) {
        
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Email_Query);
            preparedStatement.setString(7, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }
    
    @Override
    public List getProductsByPCID (int PCID) {
        
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_PCID_Query);
            preparedStatement.setInt(6, PCID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }
    
    @Override
    public Product getProduct(int PID) {
		
        Product product= null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Read_Query);
            preparedStatement.setInt(1, PID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            if (result.next() && result != null) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
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
 
        return product;
    }
    
    @Override
    public String createProduct(Product product) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, product.getPID());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getNote());
            preparedStatement.setString(4, product.getLogo());
            preparedStatement.setString(5, product.getPhoto());
            preparedStatement.setInt(6, product.getPCID());
            preparedStatement.setString(7, product.getEmail());
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
    
    @Override
    public boolean updateProduct(Product product) {
		
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, product.getPID());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getNote());
            preparedStatement.setString(4, product.getLogo());
            preparedStatement.setString(5, product.getPhoto());
            preparedStatement.setInt(6, product.getPCID());
            preparedStatement.setString(7, product.getEmail());
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
    
    @Override
    public boolean deleteProduct(Product product) {
	Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query);
            preparedStatement.setInt(1, product.getPID());
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

    @Override
    public List getProductsByName(String name) {
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Name_Query);
            preparedStatement.setString(1, "%"+name+"%");
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }

    @Override
    public List getProductsByNameAndPCID(int PCID, String name) {
        List products = new ArrayList();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_NameAndPCID_Query);
            preparedStatement.setString(1, "%"+name+"%");
            preparedStatement.setInt(2, PCID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getString(7));
                products.add(product);
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
        
        return products;
    }

}
