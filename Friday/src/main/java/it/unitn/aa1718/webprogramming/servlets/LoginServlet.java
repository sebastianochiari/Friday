/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import it.unitn.aa1718.webprogramming.extra.*;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMyCookieDAOImpl;
import it.unitn.aa1718.webprogramming.friday.MyCookie;
import java.sql.Timestamp;

/**
 *
 * @author leo97
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        MyCookieDAO riverDAO = mySqlFactory.getMyCookieDAO();
        
        // List myCookies = null; da implementare, dovremmo salvarci tutti i cookie del DB
        MyCookie myCookie = null;
       
        MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();
        
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String ricordami = request.getParameter("ricordami");
        Library library = new Library();
        
        //elimina cookie scaduti
        myCookieDAO.deleteDBExpiredCookies();
        
        //associa cookie se esistente
        myCookie = myCookieDAO.getCookie(request, email);
        Long Deadline = (long)0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        if (myCookie == null) {
            
            int cookievalue = library.LastEntryTable("cookieID", "cookies");
            String cookiename = "FridayLogin";
            Cookie cookie = new Cookie(cookiename, Integer.toString(cookievalue));
            
            //ricordami per 3600 secondi se selezionato, altrimenti cookie valido per la sessione
            if(ricordami != null && ricordami.equals("on")){
                cookie.setMaxAge(3600);//se ricordami selezionato
                Deadline = timestamp.getTime()+ 60*60*1000;
            }
            else
                    cookie.setMaxAge(-1);//se ricordami non selezionato
            
            int LID = -1;
            myCookieDAO.createCookie(new MyCookie(cookievalue, LID, email, Deadline));
            response.addCookie(cookie);
            
            System.out.println("zao zao il nuovo tuo cookie è stato inserito ed è "+cookie.getName()+", "+cookie.getValue()+"");
            
        }
        else
            System.out.println("Bentornato amico! il tuo ID è "+myCookie.getCookieID()+"\n");

     
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
        processRequest(request, response);
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
