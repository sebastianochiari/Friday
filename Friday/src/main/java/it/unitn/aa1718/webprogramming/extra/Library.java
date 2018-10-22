/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.extra;

import it.unitn.aa1718.webprogramming.connection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.Cookie;

/**
 *
 * @author tommi
 */
public class Library {
    
    // metodo calcolo del PID dell'ultima entry della tabella prodotti
    public int LastEntryTable(String col, String table) {
        int tmp = 1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String command = null;
        String colTmp = "max"+col;
        try {
            command = "select max("+col+") as "+colTmp+" from "+table;
            
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();                    
            
            result.next();
            tmp = Integer.parseInt(result.getString(colTmp))+1;
            
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
        return tmp;
    }
    
    public String ImageControl(String image) {
        String tmp = null;
        
        if (image != null && !image.isEmpty()){
                tmp = image;
        }
        
        return tmp;
    }
    
    public void AddCookie(Cookie cookie){
 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String command = null;
        
        try {
            command = "INSERT INTO cookies (cookieID, LID, Email) VALUES ('"+cookie.getValue()+"', null, null)";
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.execute();                   
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }
    
    public void AddCookie(Cookie cookie, String email){
 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String command = null;
        
        try {
            command = "INSERT INTO cookies (cookieID, LID, Email) VALUES ('"+cookie.getValue()+"', null, '"+email+"')";
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.execute();                   
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }
    
    public boolean CheckCookie(Cookie cookie){
    
        boolean check = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String command = null;
        
        try {
            command = "SELECT * FROM cookies WHERE cookieID = '"+cookie.getValue()+"'";
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();                    
            if(result != null){
                check = true;
            }
            result.next();
            
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
        
        return check;
    }
}
