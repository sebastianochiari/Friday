/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.project.friday2;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import java.sql.Connection;

/**
 *
 * @author tommi
 */
 
public class ConnectionDB {
    private Connection connection;
    //private String dbURL= "jdbc:mysql://localhost:3306/country?autoReconnect=true&useSSL=false";
    private String dbURL= "jdbc:mysql://10.196.178.237:3306/FridayDB?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "tommi";
    private String password = "appa"; // I edited my password here. 
     
     
 
 
 
    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }
 
 
 
    public void setUsername(String username) {
        this.username = username;
    }
 
 
 
 
    public void setPassword(String password) {
        this.password = password;
    }
 
 
    public boolean isAvailable() {
        return this.connection == null ? false : true;
    }
     
     
    public static void main(String[] args) {
        ConnectionDB connector = new ConnectionDB();
        Connection conn = connector.openDB();
        if (conn == null){
            System.out.print("Error"); // This prints out to my console. 
            return;
        }
 
    }
 
//    public ResultSet executeQuery(String query, String[] parms) 
//            throws SQLException {
//        Statement statement = this.connection.createStatement();
//        ResultSet users = statement.executeQuery(query);
//        return users;
//    }
    
//    public static ResultSet executeQuery(String query, Connection con)
//    {
//        try
//        {
//            Statement stmt = con.createStatement();
//            ResultSet results = stmt.executeQuery(query);
//            return results;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }
 
     
    public int executeUpdate(String query, String [] parms) 
            throws SQLException {
        Statement statement = this.connection.createStatement();
        int count = statement.executeUpdate(query);
        return count;
    }
 
 
 
    public Connection openDB() {
        try{
            //Connection connection;
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(dbURL,username, password);
            System.out.print("THIS WORKS "); // this prints out to my console. 
           } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
                 }
           catch(ClassNotFoundException e){
               System.out.println("<br>Can't load JDBC driver"); 
        }
        return connection;
    }
 
 

//    public ResultSet executeQuery(String query) throws SQLException {
//        // TODO Auto-generated method stub
//        return null;
//    }
 
 
 
    public int executeUpdate(String query) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }
 
 
 
     
 
    public void printTrace(SQLException ex) {
        for (Throwable t : ex) {
            t.printStackTrace(System.out);  // stack trace to console
        }
    }
 
     
     
}
