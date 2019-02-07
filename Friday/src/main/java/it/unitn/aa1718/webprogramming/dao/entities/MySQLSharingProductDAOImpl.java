/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.SharingProductDAO;
import it.unitn.aa1718.webprogramming.friday.SharingProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leo97
 */
public class MySQLSharingProductDAOImpl implements SharingProductDAO{
    
    private static final String Create_Query = "INSERT INTO sharing_product (email, PID) VALUES (?, ?)";
    
    private static final String Read_Query = "SELECT email, PID FROM sharing_product WHERE (email = ? and PID = ?)";
    
    private static final String Read_All_Emails_By_LID_Query = "SELECT * FROM sharing_product WHERE PID = ?";
    
    private static final String Read_All_LIDs_By_Email_Query = "SELECT email, PID FROM sharing_product WHERE email = ?";
    
    private static final String Read_All_Query = "SELECT email, PID FROM sharing_product";
        
    private static final String Update_Query = "UPDATE sharing_product SET email=?, PID=?  WHERE (email = ? and PID = ?)";

    private static final String Delete_Query = "DELETE FROM sharing_product WHERE (email = ? and PID = ?)";

    @Override
    public List getAllSharingProduct() {
        
        List sharingProducts = new ArrayList();
        SharingProduct sharingProduct = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharingProduct = new SharingProduct(result.getString(1), result.getInt(2) );
                sharingProducts.add(sharingProduct);
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
        
        return sharingProducts;
    }

    @Override
    public List getAllProductByEmail(String email) {
        
        List sharingProducts = new ArrayList();
        SharingProduct sharingProduct = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_LIDs_By_Email_Query);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharingProduct = new SharingProduct(result.getString(1), result.getInt(2));
                sharingProducts.add(sharingProduct);
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
        
        return sharingProducts;
    
    }

    @Override
    public List getAllEmailsbyPID(int PID) {
        
        List sharingProducts = new ArrayList();
        SharingProduct sharingProduct = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Emails_By_LID_Query);
            preparedStatement.setInt(1, PID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharingProduct = new SharingProduct(result.getString(1), result.getInt(2) );
                sharingProducts.add(sharingProduct);
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
        
        return sharingProducts;
        
        
    }

    @Override
    public SharingProduct getSharingProduct(int PID, String email) {
        
        SharingProduct sharingProduct = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, PID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharingProduct = new SharingProduct(result.getString(1), result.getInt(2) );
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
        
        return sharingProduct;
    }

    @Override
    public String createSharingProduct(SharingProduct sharingProduct) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sharingProduct.getEmail());
            preparedStatement.setInt(2, sharingProduct.getPID());
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
                connection.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        
        return null;    }

    @Override
    public boolean updateSharingProduct(SharingProduct sharingProduct) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Update_Query);
            preparedStatement.setString(1, sharingProduct.getEmail());
            preparedStatement.setInt(2, sharingProduct.getPID());
            preparedStatement.execute();
            
            return true;
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
        
        return false;
    }

    @Override
    public boolean deleteSharingProduct(SharingProduct sharingProduct) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Delete_Query);
            preparedStatement.setString(1, sharingProduct.getEmail());
            preparedStatement.setInt(2, sharingProduct.getPID());
            preparedStatement.execute();
            
            return true;
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
        
        return false;
    }
    
}
