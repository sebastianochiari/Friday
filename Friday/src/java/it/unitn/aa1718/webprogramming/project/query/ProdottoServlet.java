/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.project.query;
import it.unitn.aa1718.webprogramming.project.connection.*;

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
public class ProdottoServlet extends HttpServlet {
    
    protected ConnectionDB database = null;
    protected Connection connection = null;
    protected Statement statement = null;
    protected String command = null;
    protected int PID = 1;

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
            
            // calcolo del PID dell'ultima entry della tabella prodotti
            PID = LastEntry();
            
            // query inserimento prodotto
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
    
    // metodo calcolo del PID dell'ultima entry della tabella prodotti
    public int LastEntry(){
        int tmp = 1;
        try {
            command = "select max(PID) as maxPID from products";
            statement.execute(command);
            ResultSet risultato = statement.executeQuery(command);
            risultato.next();
            System.out.println(risultato.getString("maxPID"));
            tmp = Integer.parseInt(risultato.getString("maxPID"))+1;
            statement.close();
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
            }
        return tmp;
    }
    
    // metodo per inserimento del prodotto nel database
    
    public void ProductInsert(HttpServletRequest request){
        try {
            statement = connection.createStatement();
            String name = request.getParameter("product");
            String description = request.getParameter("description");
            String productCategory = request.getParameter("productCategory");
            String logo = request.getParameter("productLogo");
            String photo = request.getParameter("productPhoto");
            String email = "britney.spears@gmail.com";
            command = PhotoLogoControl(name, description, productCategory, logo, photo, email);
        
            statement.executeUpdate(command);
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
            }
    }
    
    // controllo presenza logo e fotografia
    
    public String PhotoLogoControl(String name, String description, String productCategory, String logo, String photo, String email){
        if (logo != null && !logo.isEmpty() && photo != null && !photo.isEmpty()){
                command = "insert into products (PID, Name, Note, Logo, Photo, PCID, Email) values  ('"+PID+"', '"+name+"', '"+description+"', '"+logo+"', '"+photo+"', '1', '"+email+"')";
            } else if((logo != null && !logo.isEmpty()) && !(photo != null && !photo.isEmpty())){
                command = "insert into products (PID, Name, Note, Logo, Photo, PCID, Email) values  ('"+PID+"', '"+name+"', '"+description+"', '"+logo+"', null, '1', '"+email+"')";
            } else if(!(logo != null && !logo.isEmpty()) && (photo != null && !photo.isEmpty())){
                command = "insert into products (PID, Name, Note, Logo, Photo, PCID, Email) values  ('"+PID+"', '"+name+"', '"+description+"', null, '"+photo+"', '1', '"+email+"')";
            } else {
                command = "insert into products (PID, Name, Note, Logo, Photo, PCID, Email) values  ('"+PID+"', '"+name+"', '"+description+"', null, null, '1', '"+email+"')";
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
