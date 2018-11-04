/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMyCookieDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.MyCookie;
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
        
        String typeChange = request.getParameter("typeChange");
        
        String email = (String) (request.getSession()).getAttribute("emailSession");
        String password = userDAO.getPasswordByUserEmail(email);
        String name = (String) (request.getSession()).getAttribute("nameUserSession");
        String surname = (String) (request.getSession()).getAttribute("surnameUserSession");
        String avatar = (String) (request.getSession()).getAttribute("avatarUserSession");
        boolean admin = (boolean) (request.getSession()).getAttribute("adminUserSession");
        boolean list_owner = (boolean) (request.getSession()).getAttribute("list_OwnerUserSession");
        
        switch (typeChange) {
            case "password": changePassword(request, response, encrypt, library, userDAO, email, name, surname, avatar, admin, list_owner); break;
            case "email": changeEmail(request, response, encrypt, library, userDAO, password, name, surname, avatar, admin, list_owner); break;
            //case "personal": changePersonal(request, response, encrypt, library, userDAO, email, name, surname, avatar, admin, list_owner); break;
        }
        
    }
    
    protected void changePassword (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String email, String name, String surname, String avatar, boolean admin, boolean list_owner) throws ServletException, IOException {
        
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
            userDAO.updateUserByEmail(user1);
            
            System.out.println("ho cambiato la password correttamente");
            
            response.sendRedirect("myaccount.jsp");
        }
    }
    
    protected void changeEmail (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String password, String name, String surname, String avatar, boolean admin, boolean list_owner) throws ServletException, IOException {
        
        String inputNewEmail = request.getParameter("inputNewEmail");
        String confirmEmail = request.getParameter("confirmEmail");
        String typeError = request.getParameter("typeError");
        String changeEmail = request.getParameter("changeEmail");
        request.setAttribute("inputEmail", inputNewEmail);
        MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();
        
        if (userDAO.checkUser(inputNewEmail)) {
            
            String error = "errorInputEmail";
            typeError = error;
            request.setAttribute("errorInputEmail", typeError);
            request.setAttribute("inputEmail", inputNewEmail);
            request.getRequestDispatcher(changeEmail).forward(request, response);
            
        } else if (!inputNewEmail.equals(confirmEmail)) {       

            String error = "errorConfirmEmail";
            typeError = error;
            request.setAttribute("errorConfirmEmail", typeError);
            
            System.out.println( "-----Le email non coicidono");
            request.getRequestDispatcher(changeEmail).forward(request, response);

        } else {
            
            Long deadlineSession = Long.parseLong((String) request.getSession().getAttribute("deadlineSession"));
            int CookieIDSession = Integer.parseInt((String) request.getSession().getAttribute("cookieIDSession"));
            int LIDSession = -1;
            //LIDSession = Integer.parseInt((String) request.getSession().getAttribute("LIDSession"));
            
            // fare update sul campo LID della tabella cookies genera errore sql perchè è una foreign key e non si può cambiare così a caso. 
            // un'opzione potrebbe essere che quando si fa l'update della tabella per cambiare l'email si ignora il campo LID nella query di update.
            MyCookie myCookieTmp = new MyCookie(CookieIDSession, LIDSession, inputNewEmail, deadlineSession);
            
            (request.getSession()).setAttribute("emailSession", inputNewEmail);
            
            myCookieDAO.updateCookie(myCookieTmp);
            
            User user1 = new User(inputNewEmail, password, name, surname, library.ImageControl(avatar), admin, list_owner);
            userDAO.updateUserByPassword(user1);
            
            //bisogna sistemare anche la tabella cookie e quindi forse aggiornarla?
            
            System.out.println("ho cambiato la email correttamente");
            
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
