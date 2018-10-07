/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tommi
 */
public class ConnectionDB {
    
    protected Connection myConn = null;
    protected Statement statement = null;

    public ConnectionDB() {
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "root";
            myConn = (Connection) DriverManager.getConnection(dbURL, username, password);
            
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        } 
    
    }
    
    public Connection getConnection() {
        return myConn;
    }
    
    public void closeConnection() {
        try {
            if (this.myConn != null) {
                myConn.close();
            }
        } catch (Exception c) {
            c.printStackTrace();
        }
    }
    
    public Statement getStatement() {
        try {
            statement = myConn.createStatement();
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        return statement;
    }
    
    public void closeStatement() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException c) {
            c.printStackTrace();
        }
    }
}
