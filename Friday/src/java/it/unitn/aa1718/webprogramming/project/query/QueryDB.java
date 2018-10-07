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

/**
 *
 * @author tommi
 */
public class QueryDB {
    
    protected ResultSet resultSet = null;
    
    public QueryDB(){
        
    }
    
    public ResultSet Select(){
        
        resultSet = null;
        
        
        
        return resultSet;
    }
    
}
