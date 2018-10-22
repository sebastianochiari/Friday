/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import it.unitn.aa1718.webprogramming.extra.*;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String ricordami = request.getParameter("ricordami");
        Library library = new Library();
        
     
        Cookie[] cookies = request.getCookies();
        Cookie myCookie = null;
        int lengthArrayCookies = 0;
        
        if(cookies != null){
            while (lengthArrayCookies < cookies.length && myCookie == null) {
                if((cookies[lengthArrayCookies].getName()).equals("FridayLogin")){
                    myCookie = cookies[lengthArrayCookies];
                }
                lengthArrayCookies++;
            }
        }
        else{
            System.out.println("cookies null");
        }
        
        if (myCookie == null) {
            
            int cookievalue = library.LastEntryTable("cookieID", "cookies");
            String cookiename = "FridayLogin";
         
            myCookie = new Cookie(cookiename, Integer.toString(cookievalue));
            myCookie.setMaxAge(-1); //Valid only for the current browsing session
            
            if(ricordami.equals("on")){
                library.AddCookie(myCookie, email);
            }
            else{
                library.AddCookie(myCookie);
            }
            response.addCookie(myCookie);
            
            System.out.println("zao zao il nuovo tuo cookie è stato inserito ed è "+myCookie.getName()+", "+myCookie.getValue()+"");
            
        } else {
            if(library.CheckCookie(myCookie)){
                System.out.println("zao zao il tuo cookie c'era già ed è "+myCookie.getName()+", "+myCookie.getValue()+"\n");
            }
            else{
                System.out.println("wtf ci ai akeratih bravo se vuoi ti assumiamo\n");
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
