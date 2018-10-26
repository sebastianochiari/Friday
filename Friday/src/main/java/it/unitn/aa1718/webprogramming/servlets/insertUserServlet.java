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

        // creazione di user
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
      
/*        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
    
        // new BufferedReader(new InputStreamReader(inputStream));
        bufferedReader =  request.getReader(); 

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

        // concateno $ come delimitatori, mi limito al penultimo, così non esco da |Stringa|
        String test = "%" + sb.toString() + "$"; 

        System.out.println("REQUEST BODY DEL FORM = TEST è :" +test); 
        
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<FINE TEST");
        //Name1=remo&Surname1=zao&exampleInputEmail1=remozao@gmail.com&exampleInputPassword1=ciaone1&exampleInputPassword1=ciaone1$
        name = test.substring(test.indexOf("=") + 1, test.indexOf("&"));
        System.out.println("NAME ESTRATTO CORRETTAMENTE è : " + name);
        
        String [] tok = test.split("&");
        for(int i=0; i<tok.length; i++){
            System.out.println("token VALE: " + tok[i] + "\n");
            tok[i] = tok[i] + "$";
        }
        
        surname = tok[1].substring(tok[1].indexOf("=") + 1, tok[1].indexOf("$"));
        originServlet = tok[2].substring(tok[2].indexOf("=") + 1, tok[2].indexOf("$"));
        registerForm = tok[3].substring(tok[3].indexOf("=") + 1, tok[3].indexOf("$"));
        
        email = tok[4].substring(tok[4].indexOf("=") + 1, tok[4].indexOf("$"));
        password = tok[5].substring(tok[5].indexOf("=") + 1, tok[5].indexOf("$"));
        passwordcheck = tok[6].substring(tok[6].indexOf("=") + 1, tok[6].indexOf("$"));
        avatar = tok[7].substring(tok[7].indexOf("=") + 1, tok[7].indexOf("$"));
        
*/

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
        
        boolean isOkay = library.checkString(password);
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
            
            
            
            //String salt = encrypt.getSalt(10); 
        //    String pswEncrypted = encrypt.setSecurePassword(password, email);
      
            System.out.println("la password è stata criptata correttamente. SONO IN INSERTUSERSERVLET ");
      
            
           
            
            
     /*       User user1 = new User(email, pswEncrypted, name, surname, library.ImageControl(avatar), false, false);

    
            // memorizzazione del nuovo user nel DB
            userDAO.createUser(user1);
        
            // recupero di tutti gli user del DB
            users = userDAO.getAllUsers();
*/
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
