/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.project.friday2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tommi
 */
public class FridayServletList extends HttpServlet {
/*
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
         
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        //String dbURL = "jdbc:mysql://localhost:3306/FridayDB?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String dbURL = "jdbc:mysql://10.196.178.237:3306/FridayDB?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "appa";
        Connection connection = (Connection) DriverManager.getConnection(dbURL,username, password);
     
        Statement statement = connection.createStatement();
        String command = null;
        
        // calcolo LID
        command = "select max(LID) as maxLID from lists";
        statement.execute(command);
        ResultSet risultato = statement.executeQuery(command);
        risultato.next();
        int LID = Integer.parseInt(risultato.getString("maxLID"))+1;
        statement.close();
        
        // query inserimento categoria prodotto
        statement = connection.createStatement();
        String name = request.getParameter("list");
        String description = request.getParameter("description");
        String photo = request.getParameter("listPhoto");
        String email = "britney.spears@gmail.com";
        command = null;
        
        // controllo presenza photo
        if (photo != null & !photo.isEmpty()){
            command = "insert into lists (LID, Name, Description, Image, LCID, List_Owner) values  ('"+LID+"', '"+name+"', '"+description+"', '"+photo+"', 1, '"+email+"')";
        } else {
            command = "insert into lists (LID, Name, Description, Image, LCID, List_Owner) values  ('"+LID+"', '"+name+"', '"+description+"', null, 1, '"+email+"')";
        }
        
        statement.executeUpdate(command);
        
        // chiusura connessione e statement
        try {
            if (connection != null) {
                connection.close();
                System.out.println("connessione zao zao");
            }
            if (statement != null) {
                statement.close();
                System.out.println("statement zao zao (ciao anche a te iconcina)");
            }
        }catch (SQLException sqlee) {
            sqlee.printStackTrace();
        }
       } catch(SQLException e){
        for (Throwable t : e)
            t.printStackTrace();
             }
    catch(ClassNotFoundException e){
        e.printStackTrace();
    }
     
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}