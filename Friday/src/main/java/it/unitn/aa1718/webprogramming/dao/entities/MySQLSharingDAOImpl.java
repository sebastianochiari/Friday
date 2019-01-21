/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.friday.Sharing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author leo97
 */
public class MySQLSharingDAOImpl implements SharingDAO{
    
    private static final String Create_Query = "INSERT INTO sharing (email, LID, name, modify, add, delete) VALUES (?, ?, ?, ?, ?, ?)";
    
    private static final String Read_Query = "SELECT email, LID, name, modify, add, delete FROM sharing WHERE (email = ? and LID = ?)";
    
    private static final String Read_All_Emails_By_LID_Query = "SELECT email, LID, name, modify, add, delete FROM sharing WHERE LID = ?";
    
    private static final String Read_All_LIDs_By_Email_Query = "SELECT email, LID, name, modify, addRemProd, deleteList FROM sharing WHERE email = ?";
    
    private static final String Read_All_Query = "SELECT email, LID, name, modify, add, delete FROM sharing";
        
    private static final String Update_Query = "UPDATE sharing SET email=?, LID=?, name=?, modify=?, add=?, delete=? WHERE (email = ? and LID = ?)";
    
    private static final String Delete_Query = "DELETE FROM sharing WHERE (email = ? and LID = ?)";

    @Override
    public List getAllSharing() {
        
        List sharings = new ArrayList();
        Sharing sharing = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharing = new Sharing(result.getString(1), result.getInt(2),  result.getString(3), result.getBoolean(4),  result.getBoolean(5),  result.getBoolean(6) );
                sharings.add(sharing);
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
        
        return sharings;
        
    }

    @Override
    public List getAllListByEmail(String email) {
        
        List sharings = new ArrayList();
        Sharing sharing = null;
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
                sharing = new Sharing(result.getString(1), result.getInt(2),  result.getString(3), result.getBoolean(4),  result.getBoolean(5),  result.getBoolean(6) );
                sharings.add(sharing);
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
        
        return sharings;
    }

    @Override
    public List getAllEmailsbyList(int LID) {
        
        List sharings = new ArrayList();
        Sharing sharing = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Emails_By_LID_Query);
            preparedStatement.setInt(1, LID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharing = new Sharing(result.getString(1), result.getInt(2),  result.getString(3), result.getBoolean(4),  result.getBoolean(5),  result.getBoolean(6) );
                sharings.add(sharing);
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
        
        return sharings;
    }

    @Override
    public Sharing getSharing(int LID, String email) {
        
        Sharing sharing = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, LID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                sharing = new Sharing(result.getString(1), result.getInt(2),  result.getString(3), result.getBoolean(4),  result.getBoolean(5),  result.getBoolean(6) );
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
        
        return sharing;
    }

    @Override
    public String createSharing(Sharing sharing) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sharing.getEmail());
            preparedStatement.setInt(2, sharing.getLID());
            preparedStatement.setString(3, sharing.getName());
            preparedStatement.setBoolean(4, sharing.getModify());
            preparedStatement.setBoolean(5, sharing.getAdd());
            preparedStatement.setBoolean(6, sharing.getDelete());
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
        
        return null;
    }

    @Override
    public boolean updateSharing(Sharing sharing) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Update_Query);
            preparedStatement.setString(1, sharing.getEmail());
            preparedStatement.setInt(2, sharing.getLID());
            preparedStatement.setString(3, sharing.getName());
            preparedStatement.setBoolean(4, sharing.getModify());
            preparedStatement.setBoolean(5, sharing.getAdd());
            preparedStatement.setBoolean(6, sharing.getDelete());
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
    public boolean deleteSharing(Sharing sharing) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Delete_Query);
            preparedStatement.setString(1, sharing.getEmail());
            preparedStatement.setInt(2, sharing.getLID());
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
