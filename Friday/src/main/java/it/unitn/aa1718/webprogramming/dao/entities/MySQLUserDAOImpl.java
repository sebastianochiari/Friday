/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.friday.*;
import it.unitn.aa1718.webprogramming.connection.*;
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
public class MySQLUserDAOImpl implements UserDAO {
    
    private static final String Create_Query = "INSERT INTO users (email, password, name, surname, avatar, admin, list_owner) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String Read_Query = "SELECT email, password, name, surname, avatar, admin, list_owner FROM users WHERE email = ?";
    
    private static final String Read_All_Query = "SELECT email, password, name, surname, avatar, admin, list_owner FROM users";
    
    private static final String Update_Query = "UPDATE users SET (email=?, password=?, name=?, surname=?, avatar=?, admin=?, list_owner=?) WHERE email = ?)";
    
    private static final String Delete_Query = "DELETE FROM users WHERE email = ?";
    
    @Override
    public List getAllUsers() {
        
        List users = new ArrayList();
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                user = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getBoolean(6), result.getBoolean(7));
                users.add(user);
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
        
        return users;
    }
    
    @Override
    public User getUser(String email) {
		
        User user= null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Read_Query);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            if (result.next() && result != null) {
                user = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getBoolean(6), result.getBoolean(7));
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
 
        return user;
    }
    
    @Override
    public String createUser(User user) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
                     
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Create_Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getAvatar());
            preparedStatement.setBoolean(6, user.getAdmin());
            preparedStatement.setBoolean(7, user.getListOwner());
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
    public boolean updateUser(User user) {
		
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getAvatar());
            preparedStatement.setBoolean(6, user.getAdmin());
            preparedStatement.setBoolean(7, user.getListOwner());
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
    public boolean deleteUser(User user) {
	Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query);
            preparedStatement.setString(1, user.getEmail());
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
    public boolean checkUser (String email) {
        
        boolean existance = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Read_Query);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            if (result != null) {
                existance = true;
            }
        } catch (SQLException e) {
            existance = true;
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
        return existance;
    }
}
