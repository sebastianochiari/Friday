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
            System.out.println(result.getString(colTmp));
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
    
    public boolean checkString(String str) {
    char ch;
    boolean capitalLetter = false;
    boolean lowerCaseFlag = false;
    
    if(str.length() < 6){
        System.out.println("AT LEAST 6 CHARACTERS!");
        return false;
    }
    else{
    boolean number = false;
    for(int i=0;i < str.length();i++) {
        ch = str.charAt(i);
        if( Character.isDigit(ch)) {
            System.out.println("THERE IS A NUMBER");
            number = true;
        }
        else if (Character.isUpperCase(ch)) {
            System.out.println("THERE IS An UPPERCASE");
            capitalLetter = true;
        }
        if(number && capitalLetter)
            return true;
    }
    return false;
    }
}
    
    
    
    
    
    
}