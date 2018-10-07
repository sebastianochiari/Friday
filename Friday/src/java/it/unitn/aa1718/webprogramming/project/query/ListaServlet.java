/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.project.query;

import it.unitn.aa1718.webprogramming.project.connection.ConnectionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class ListaServlet extends HttpServlet {

    protected ConnectionDB database = null;
    protected Connection connection = null;
    protected Statement statement = null;
    protected String command = null;
    protected int LID = 1;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            database = new ConnectionDB();
            connection = database.getConnection();
            statement = database.getStatement();
            
            // calcolo del LID dell'ultima entry della tabella liste
            LID = LastEntry();
            
            // query inserimento lista
            ProductInsert(request);
            
            // chisura connessione
            database.closeConnection();
            connection.close();
            database.closeStatement();
            statement.close();
            
           } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
                 }
    }
    
    // metodo calcolo del LID dell'ultima entry della tabella liste
    public int LastEntry(){
        int tmp = 1;
        try {
            command = "select max(LID) as maxLID from lists";
            statement.execute(command);
            ResultSet risultato = statement.executeQuery(command);
            risultato.next();
            System.out.println(risultato.getString("maxLID"));
            tmp = Integer.parseInt(risultato.getString("maxLID"))+1;
            statement.close();
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        return tmp;
    }
    
    // metodo per inserimento della lista nel database
    public void ProductInsert(HttpServletRequest request){
        try {
            statement = connection.createStatement();
            String name = request.getParameter("list");
            String description = request.getParameter("description");
            String photo = request.getParameter("listPhoto");
            String email = "britney.spears@gmail.com";
            command = PhotoControl(name, description, photo, email);
        
            statement.executeUpdate(command);
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
    }
    
    // controllo presenza fotografia
    public String PhotoControl(String name, String description, String photo, String email){
        if (photo != null & !photo.isEmpty()){
            command = "insert into lists (LID, Name, Description, Image, LCID, List_Owner) values  ('"+LID+"', '"+name+"', '"+description+"', '"+photo+"', 1, '"+email+"')";
        } else {
            command = "insert into lists (LID, Name, Description, Image, LCID, List_Owner) values  ('"+LID+"', '"+name+"', '"+description+"', null, 1, '"+email+"')";
        }
        
        return command;
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
