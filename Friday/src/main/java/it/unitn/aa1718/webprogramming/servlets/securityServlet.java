/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tommi
 */
public class securityServlet extends HttpServlet {

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
            out.println("<title>Servlet securityServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet securityServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        UserDAO riverDAO = mySqlFactory.getUserDAO();
        List users = null;
        UserDAO userDAO = new MySQLUserDAOImpl();        
        Library library = new Library();
        DBSecurity encrypt = new DBSecurity();
        
        String email = (String) (request.getSession()).getAttribute("emailSession");
        String name = (String) (request.getSession()).getAttribute("nameUserSession");
        String surname = (String) (request.getSession()).getAttribute("surnameUserSession");
        String avatar = (String) (request.getSession()).getAttribute("avatarUserSession");
        boolean admin = (boolean) (request.getSession()).getAttribute("adminUserSession");
        boolean list_owner = (boolean) (request.getSession()).getAttribute("list_OwnerUserSession");
        String previousPassword = request.getParameter("previousPassword");
        String inputNewPassword = request.getParameter("inputNewPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String typeError = request.getParameter("typeError");
        String changePassword = request.getParameter("changePassword");
        
        String inputNewPasswordEncrypted = encrypt.setSecurePassword(inputNewPassword, email);
        String previousPasswordEncrypted = encrypt.setSecurePassword(previousPassword, email);
        
        boolean isOkay = encrypt.checkString(inputNewPassword);
        
        System.out.println(userDAO.getPasswordByUserEmail(email));
        System.out.println(previousPasswordEncrypted);
        
        if (!userDAO.getPasswordByUserEmail(email).equals(previousPasswordEncrypted)) {
            
            String error = "errorPreviousPassword";
            typeError = error;
            request.setAttribute("errorPreviousPassword", typeError);                
            request.getRequestDispatcher(changePassword).forward(request, response);
            
        } else if (!isOkay) {   

            String error = "errorInputPassword";
            typeError = error;
            request.setAttribute("errorInputPassword", typeError);                
            request.getRequestDispatcher(changePassword).forward(request, response);

        } else if (!inputNewPassword.equals(confirmPassword)) {       

            String error = "errorConfirmPassword";
            typeError = error;
            request.setAttribute("errorConfirmPassword", typeError);
            
            System.out.println( "-----Le password non coicidono");
            request.getRequestDispatcher(changePassword).forward(request, response);

        } else {
            
            User user1 = new User(email, inputNewPasswordEncrypted, name, surname, library.ImageControl(avatar), admin, list_owner);
            userDAO.updateUser(user1);
            
            System.out.println("ho cambiato la password correttamente");
            
            response.sendRedirect("myaccount.jsp");
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
