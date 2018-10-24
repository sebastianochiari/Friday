/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marta
 */
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        
        DBSecurity encrypt = new DBSecurity();
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        UserDAO riverDAO = mySqlFactory.getUserDAO();
        List users = null;
        UserDAO userDAO = new MySQLUserDAOImpl();


        // creazione di user
        Library library = new Library();
      
        String email = null;
        String password = null;
      
        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
        bufferedReader =  request.getReader() ; //new BufferedReader(new InputStreamReader(inputStream));
        char[] charBuffer = new char[128];
        int bytesRead;
        while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
            sb.append(charBuffer, 0, bytesRead);
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                throw ex;
            }
         }
        String test = "%" + sb.toString() + "$"; //concateno \n\n come delimitatori, mi limito al penultimo, così non esco da |Stringa|
        System.out.println("REQUEST BODY DEL FORM = TEST è :" +test); 
        
        email = test.substring(test.indexOf("=") + 1, test.indexOf("&"));
        System.out.println("EMAIL ESTRATTO CORRETTAMENTE è " + email);
        String [] tok = test.split("&");
        
        for(int i=0; i<tok.length; i++){
            System.out.println("VALE: " + tok[i] + "\n"); 
            tok[i] = tok[i] + "$";
        }
        
        password = tok[1].substring(tok[1].indexOf("=") + 1, tok[1].indexOf("$"));
        System.out.println("email in LOGINSERVLET:" + email);
        System.out.println("psw in LOGINSERVLET: " + password);
        
        
        
        //String salt = encrypt.getSalt(10);
        
        
        
        String pswencrypted = encrypt.setSecurePassword(password, email);
        
        System.out.println("LA PASSWORD CRIPTATA IN LOGINSERVLET è :" + pswencrypted);
        
        Connection connection = MySQLDAOFactory.createConnection();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String  get_access = "SELECT password FROM users WHERE email = ?";
        String dbpassword = null;
        try {
            preparedStatement = connection.prepareStatement(get_access);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();               
         if (result.next() && result != null) {
             dbpassword = result.getString("password");
             System.out.println("IN LOGIN SERVLET, la password che ritorna dal database è : " + dbpassword);
        } else {
             if(result == null ){
                 System.out.println("PASSWORD NON ESISTE! ");
                 System.err.println("password inserita non è corretts, reinserire -- REDIREZIONA CON POPUP -- o errate ??' ");
             }
         }
         
         
         
         
          if(pswencrypted.hashCode() == dbpassword.hashCode()){
                System.out.println("LE PASSWORD SONO CORRETTE!! REDIREZIONO A INDEX.HTML");
                
                response.sendRedirect("index.html");
            }
        
         
         
         
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
