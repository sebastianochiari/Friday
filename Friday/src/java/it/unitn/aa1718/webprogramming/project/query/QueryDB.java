/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.project.query;

import it.unitn.aa1718.webprogramming.project.connection.ConnectionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tommi
 */
public class QueryDB {
    
    protected ResultSet resultSet = null;
    
    public QueryDB(){
        
    }
    
    public boolean CheckName(String name, String table, ConnectionDB database, Connection connection, Statement statement) {
        
        String command = null;
        ResultSet risultato = null;
        boolean res = false;
        
        try {
            System.out.println("-------------"+name+"----------------------");
            command = "SELECT Name FROM "+table+" WHERE Name='"+name+"';";
            statement.execute(command);
            risultato = statement.executeQuery(command);
            risultato.next();
            statement.close();
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        if (risultato==null){
            res = false;
            
        } else {
            res = true;
        }
        
        System.out.println("-------------"+res+"----------------------");
        
        return res;
    }
    
}
