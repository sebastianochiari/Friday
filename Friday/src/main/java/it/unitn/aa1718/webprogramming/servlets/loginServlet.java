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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMyCookieDAOImpl;
import it.unitn.aa1718.webprogramming.friday.MyCookie;
import java.sql.Timestamp;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marta & leo97
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
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
        UserDAO riverUserDAO = mySqlFactory.getUserDAO();
        MyCookieDAO riverCookieDAO = mySqlFactory.getMyCookieDAO();
        HttpSession session = request.getSession();

        // List myCookies = null; da implementare, dovremmo salvarci tutti i cookie del DB
        MyCookie myCookie = null;
        MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();


        List users = null;
        UserDAO userDAO = new MySQLUserDAOImpl();
        Library library = new Library();
      
        String email = null;
        String password = null;
        String ricordami = null;
        
        String typeError = null;
        String registerForm = null;
        email = request.getParameter("email");
        password = request.getParameter("password");
        ricordami = request.getParameter("ricordami");
        typeError = request.getParameter("typeError");
        registerForm = request.getParameter("registerForm");

        System.out.println("email:" + email);
        System.out.println("psw:" + password);
        System.out.println("ricordami:" + ricordami);
        
        System.out.println("typeError: " + typeError);
        System.out.println("registerForm: " + registerForm);
        
        System.out.println("EMIL + PASSWORD + ricordami : " + email + password);
        
        request.setAttribute("email", email);
        
        //ritorna false se non esiste una mail nel database 
        if (!userDAO.checkUser(email)) {
            System.out.println("questa email non esiste nel database");
            String error = "emailError";
            typeError = error;
            request.setAttribute("errorEmail", typeError);
            request.getRequestDispatcher(registerForm).forward(request, response);
            
        } else {

            // questa parte è da fare con un metodo di userDAO, come checkUser
                
            String pswencrypted = encrypt.setSecurePassword(password, email);
            System.out.println("LA PASSWORD CRIPTATA IN LOGINSERVLET è :" + pswencrypted);
        
            String dbpassword = userDAO.getPasswordByUserEmail(email);
        
            if (pswencrypted.equals(dbpassword)) {

                System.out.println("LE PASSWORD SONO CORRETTE!! SETTO I COOKIE E REDIREZIONO A INDEX.JSP");
                //elimina cookie scaduti
                myCookieDAO.deleteDBExpiredCookies();

                //associa cookie se esistente
                myCookie = myCookieDAO.getCookie(request, email);
                Long Deadline = (long)0;
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                if (myCookie == null) {

                    Cookie cookie = new Cookie("FridayLogin", Integer.toString(library.LastEntryTable("cookieID", "cookies")));
                    //ricordami per 3600 secondi se selezionato, altrimenti cookie valido per la sessione

                    if(ricordami != null && ricordami.equals("on")) {

                        cookie.setMaxAge(3600); //se ricordami selezionato, vale per un'ora
                        Deadline = timestamp.getTime()+ 60*60*1000;

                    } else {

                        cookie.setMaxAge(-1); //se ricordami non selezionato, vale per la sessione
                        Deadline = timestamp.getTime();
                    }

                    int LID = -1;
                    System.out.println("COOKIE ID = "+library.LastEntryTable("cookieID", "cookies")+"+ LID = "+LID+" EMAIL = "+email+" DEADLINE = "+Deadline);
                    MyCookie myNewCookie = new MyCookie(library.LastEntryTable("cookieID", "cookies"), LID, email, Deadline); 
                    myCookieDAO.createCookie(myNewCookie);
                    response.addCookie(cookie);
                    session.setAttribute("cookieIDSession", myNewCookie.getCookieID());
                    System.out.println("zao zao il nuovo tuo cookie è stato inserito ed è "+cookie.getName()+", "+cookie.getValue()+"");

                } else {
                    System.out.println("Bentornato amico! il tuo ID è "+myCookie.getCookieID()+"\n");
                }

                response.sendRedirect("index.jsp");
         
            } else {

                System.out.println("PASSWORD DIVERSE !!!!!!!!!!");
                System.out.println("la password non corrisponde all'email inserita ");
                String error = "errorPassword";
                typeError = error;
                request.setAttribute("errorPassword", typeError);
                request.getRequestDispatcher(registerForm).forward(request, response);
            
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










































