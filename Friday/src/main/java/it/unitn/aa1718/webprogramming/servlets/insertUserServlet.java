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
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insertUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertUserServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        UserDAO riverDAO = mySqlFactory.getUserDAO();
        List users = null;
        System.out.println("SONO IN POST METHOD!!!!!!!!!");
        UserDAO userDAO = new MySQLUserDAOImpl();
        
//        // cancellazione di user memorizzati sul DB
//        users = userDAO.getAllUsers();
//        for (Object u : users) {
//            userDAO.deleteUser((User) u);
//        }
        

        // creazione di user
        Library library = new Library();
      
        
      System.out.println("before DB SECURITY");
        DBSecurity encrypt = new DBSecurity();
        //private Random RANDOM = new SecureRandom();
      System.out.println("DOPO DB SECURITY");  
      
        String email = null;
        String password = null;
        String name = null;
        String surname = null;
        String avatar = null;
      
        
        
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
        String test = sb.toString() + "$\n"; //concateno \n\n come delimitatori, mi limito al penultimo, così non esco da |Stringa|

        System.out.println("REQUEST BODY DEL FORM = TEST è :" +test);
        
        
         email = test.substring(test.indexOf("=") + 1, test.indexOf("&"));
         String tmp = test.substring(test.indexOf("&") + 1, test.indexOf("\n")); //  tmp è :      exampleInputPassword1=*passwordScritta*\n
         System.out.println("TMP IS : " + tmp);
    //   password = tmp.substring(tmp.indexOf("=") , tmp.indexOf("\n"));
        
        System.out.println("Email estratta è :" + email);
        System.out.println("TMP estratta è :" + tmp);
        
        
        
         password = tmp.substring(tmp.indexOf("=") +1, tmp.indexOf("$"));
        
     /* 
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        Enumeration paramNames = request.getParameterNames();
        //We iterate over the list of parameter names
        while(paramNames.hasMoreElements()){
            String paramName = (String)paramNames.nextElement();
            if(paramNames.equals("email")){
               // email = request.getParameter(paramName);  
                email = request.getParameter("email");
            }
            if(paramNames.equals("password")){
                password = request.getParameter("password");
                //password = request.getParameter(paramName);
     
                
            }
         if(paramNames.equals("name")){
                out.println("name:   ");
                name = request.getParameter(paramName);
            }
            if(paramNames.equals("surname")){
                out.println("surname:   ");
                surname = request.getParameter(paramName);
            }
            if(paramNames.equals("avatar")){
                out.println("avatar:   ");
                avatar = request.getParameter(paramName);
            }
     
           out.println("<b>" + request.getParameter(paramName) + "</b>");
           out.println("<br/><br/>");
            
        } 
        
        out.println("</body>");
        out.println("</html>");
        out.close();  
  */
       System.out.println("before ENCRYPTION");
       System.out.println("EMAIL IS :    " + email);
       System.out.println("password IS :      " + password);
       
       
       
       
        //String salt = encrypt.getSalt(10); 
        String pswEncrypted = encrypt.setSecurePassword(password, email);
      
  System.out.println("DOPO ENCRYPTION");
        //User user1 = new User(email, pswEncrypted, name, surname, library.ImageControl(avatar), false, false); NAME CANNOT BE NULL
        User user1 = new User(email, pswEncrypted, "A", "A", library.ImageControl(avatar), false, false);
  
        // memorizzazione del nuovo user nel DB
        userDAO.createUser(user1);
        
        // recupero di tutti gli user del DB
        users = userDAO.getAllUsers();
      
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
        
        System.out.println("SONO IN GEEEEEEEEEEEEEEEETTTTTTTTT");
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
