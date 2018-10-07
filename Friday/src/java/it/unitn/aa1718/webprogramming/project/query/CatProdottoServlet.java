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
public class CatProdottoServlet extends HttpServlet {

    protected ConnectionDB database = null;
    protected Connection connection = null;
    protected Statement statement = null;
    protected String command = null;
    protected int PCID = 1;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            database = new ConnectionDB();
            connection = database.getConnection();
            statement = database.getStatement();
            
            // calcolo del PCID dell'ultima entry della tabella prodotti
            PCID = LastEntry();
            
            // query inserimento categoria prodotto
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
    
    // metodo calcolo del PCID dell'ultima entry della tabella prodotti
    public int LastEntry(){
        int tmp = 1;
        try {
            command = "select max(PCID) as maxPCID from product_categories";
            statement.execute(command);
            ResultSet risultato = statement.executeQuery(command);
            risultato.next();
            System.out.println(risultato.getString("maxPCID"));
            tmp = Integer.parseInt(risultato.getString("maxPCID"))+1;
            statement.close();
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        return tmp;
    }
    
    // metodo per inserimento della categoria di prodotto nel database
    public void ProductInsert(HttpServletRequest request){
        try {
            statement = connection.createStatement();
            String productCategoryName = request.getParameter("productCategoryName");
            String description = request.getParameter("description");
            String CategoryLogo = request.getParameter("categoryLogo");
            String email = "britney.spears@gmail.com";
            command = LogoControl(productCategoryName, description, CategoryLogo, email);
        
            statement.executeUpdate(command);
        } catch(SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
    }
    
    // controllo presenza logo
    public String LogoControl(String productCategoryName, String description, String CategoryLogo, String email){
        if (CategoryLogo != null & !CategoryLogo.isEmpty()){
            command = "insert into product_categories (PCID, Name, Description, Logo, Email) values  ('"+PCID+"', '"+productCategoryName+"', '"+description+"', '"+CategoryLogo+"', '"+email+"')";
        } else {
            command = "insert into product_categories (PCID, Name, Description,Logo, Email) values  ('"+PCID+"', '"+productCategoryName+"', '"+description+"', null, '"+email+"')";
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
