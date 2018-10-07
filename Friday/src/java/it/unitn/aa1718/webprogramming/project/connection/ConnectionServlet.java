/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.project.connection;

import java.io.IOException;
import java.sql.Connection;
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
public class ConnectionServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
            try {
                ConnectionDB database = new ConnectionDB();
                Connection connection = database.getConnection();
                Statement statement = database.getStatement();
                String command = null;

//                query di base per testare la connessione
//                command = "select * from users";
//                statement.execute(command);
//                ResultSet risultato = statement.executeQuery(command);
//                while(risultato.next()) {
//                    String risultatoUser = risultato.getString("Email");
//                    System.out.println("--------------------------------------------");
//                    System.out.println(risultatoUser);
//                }
                
                database.closeConnection();
                connection.close();
                database.closeStatement();
                statement.close();
                
            } catch(SQLException e) {
                for (Throwable t : e)
                t.printStackTrace();
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
