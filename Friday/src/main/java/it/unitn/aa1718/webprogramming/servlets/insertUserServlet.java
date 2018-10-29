/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.dao.entities.*;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.friday.*;
import it.unitn.aa1718.webprogramming.extra.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Scanner;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyleConstants;

/**
 *
 * @author tommi
 */
public class insertUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        UserDAO riverDAO = mySqlFactory.getUserDAO();
        List users = null;
        UserDAO userDAO = new MySQLUserDAOImpl();        
        Library library = new Library();
        DBSecurity encrypt = new DBSecurity();
      
        String email = null;
        String password = null;
        String passwordcheck = null;
        String name = null;
        String surname = null;
        String avatar = null;
        String originServlet = null;
        String registerForm = null;
 
        /*   HttpSession session = request.getSession();
          if (session.getAttribute("email") != null) {
              response.setStatus(500);
          }
        */
         email = request.getParameter("email");
         password = request.getParameter("password");
         passwordcheck = request.getParameter("password1");
         name = request.getParameter("name");
         surname = request.getParameter("surname");
         avatar = request.getParameter("avatar");
         registerForm = request.getParameter("registerForm");
         originServlet = request.getParameter("originServlet");
         String pswEncrypted = encrypt.setSecurePassword(password, email);
        
          //  session.setAttribute("ruolo", rud.getRuoloById(currentUser.getRuoloId()).getRuolo());
             // session.removeAttribute("signupErrorMessage");
             // request.getRequestDispatcher("/JSP/successpage.jsp").forward(request, response);
             
        System.out.println("surname:" + surname);
        System.out.println("originServlet: " + originServlet);
        System.out.println("registerForm: " + registerForm);
        System.out.println("email:" + email);
        System.out.println("psw: " + password);
        System.out.println("pswcheck: " + passwordcheck);
        System.out.println("avatar: " + avatar);
        
        boolean isOkay = encrypt.checkString(password);
        if (userDAO.checkUser(email)) {
            request.setAttribute("originServlet", originServlet);
            System.out.println("IN USERDAO.CHECKUSER ???");
            System.out.println("PRIMA DISPATCHER");
            request.getRequestDispatcher(registerForm).forward(request, response);
            System.out.println("DOPO DISPATCHER");
        } else if(!isOkay) { 
            System.out.println("IN !ISOKAY ???");                 
            request.getRequestDispatcher(registerForm).forward(request, response);

        } else {
       
             if (!password.equals(passwordcheck)) {
             System.out.println( "-----Le password non coicidono");
           response.sendRedirect("insertUser.jsp");
         } else {
             User user1 = new User(email, pswEncrypted, name, surname, library.ImageControl(avatar), false, false);
             userDAO.createUser(user1);
            
            System.out.println("la password è stata criptata correttamente. SONO IN INSERTUSERSERVLET ");
            
            // ritorno alla pagina iniziale
            response.sendRedirect("index.jsp");
        
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
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
