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
        
        String emailSession = (String) (request.getSession()).getAttribute("emailSession");
        String dbpassword = userDAO.getPasswordByUserEmail(emailSession);
        // forse per recuperare tutti i dati qua sotto dell'utente loggate aveva più senso fare come per la password invece che far passare tutto tramite la sessione. 
        String name = (String) (request.getSession()).getAttribute("nameUserSession");
        String surname = (String) (request.getSession()).getAttribute("surnameUserSession");
        String avatar = (String) (request.getSession()).getAttribute("avatarUserSession");
        boolean admin = (boolean) (request.getSession()).getAttribute("adminUserSession");
        boolean list_owner = (boolean) (request.getSession()).getAttribute("list_OwnerUserSession");
        
        switch (typeChange) {
            case "password": changePassword(request, response, encrypt, library, userDAO, emailSession, name, surname, avatar, admin, list_owner); break;
            case "email": changeEmail(request, response, encrypt, library, userDAO, dbpassword, name, surname, avatar, admin, list_owner); break;
            case "personal": changePersonal(request, response, encrypt, library, userDAO, emailSession, dbpassword, name, surname, avatar, admin, list_owner); break;
            default: response.sendRedirect("myaccount.jsp");
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
        
        String errorPresentPassword = "errorPresentPassword";
        request.setAttribute("errorPresentPassword", errorPresentPassword);
        
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
            
            request.setAttribute("errorPresentPassword", null);
            
            User user1 = new User(email, inputNewPasswordEncrypted, name, surname, library.ImageControl(avatar), admin, list_owner);
            userDAO.updateUserByEmail(user1);
            
            System.out.println("ho cambiato la password correttamente");
            
            response.sendRedirect("myaccount.jsp");
            
            
        }
    }
    
    protected void changeEmail (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String dbpassword, String name, String surname, String avatar, boolean admin, boolean list_owner) throws ServletException, IOException {
        
        String oldEmail = request.getParameter("oldEmail");
        String inputNewEmail = request.getParameter("inputNewEmail");
        String confirmEmail = request.getParameter("confirmEmail");
        String typeError = request.getParameter("typeError");
        String changeEmail = request.getParameter("changeEmail");
        String password = request.getParameter("password");
        dbpassword = userDAO.getPasswordByUserEmail(oldEmail);
        request.setAttribute("inputEmail", inputNewEmail);
        MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();
        
        String errorPresentEmail = "errorPresentEmail";        
        request.setAttribute("errorPresentEmail", errorPresentEmail);
        
        if (!userDAO.checkUser(oldEmail)) {
            System.out.println("questa email non esiste nel database");
            String error = "errorOldEmail";
            typeError = error;
            request.setAttribute("errorOldEmail", typeError);
            request.getRequestDispatcher(changeEmail).forward(request, response);
            
        } else if(!userDAO.checkEmail(inputNewEmail)){
            System.out.println("IL FORMATO DI QUESTA EMAIL NON è CORRETTO ");
            String error = "errorOldEmail";
            typeError = error;
            request.setAttribute("errorOldEmail", typeError);
            request.getRequestDispatcher(changeEmail).forward(request, response);
        }
        else if (userDAO.checkUser(inputNewEmail)) {
            
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
            
            String pswencrypted = encrypt.setSecurePassword(password, oldEmail);
            
            if (pswencrypted.equals(dbpassword)) {
                
                request.setAttribute("errorPresentEmail", null);
                
                String newpswencrypted = encrypt.setSecurePassword(password, inputNewEmail);

                (request.getSession()).setAttribute("emailSession", inputNewEmail);

                User userPassword = new User(inputNewEmail, dbpassword, name, surname, library.ImageControl(avatar), admin, list_owner);
                userDAO.updateUserByPassword(userPassword);
                User userEmail = new User(inputNewEmail, newpswencrypted, name, surname, library.ImageControl(avatar), admin, list_owner);
                userDAO.updateUserByEmail(userEmail);

                //bisogna sistemare anche la tabella cookie e quindi forse aggiornarla?

                System.out.println("ho cambiato la email correttamente");
                
                response.sendRedirect("myaccount.jsp");
            
            } else {
            
                System.out.println("PASSWORD DIVERSE !!!!!!!!!!");
                System.out.println("la password non corrisponde all'email inserita ");
                String error = "errorPassword";
                typeError = error;
                request.setAttribute("errorPassword", typeError);
                request.getRequestDispatcher(changeEmail).forward(request, response);
            
            }
            
        }
        
    }
        
    protected void changePersonal (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String email, String dbpassword, String name, String surname, String avatar, boolean admin, boolean list_owner) throws ServletException, IOException {
        
        String newName = request.getParameter("newName");
        String newSurname = request.getParameter("newSurname");
        String newAvatar = request.getParameter("newAvatar");
        String typeError = request.getParameter("typeError");
        String changePersonal = request.getParameter("changePersonal");
        
        String errorPresentPersonal = "errorPresentPersonal";
        request.setAttribute("errorPresentPersonal", errorPresentPersonal);
        
        if (newName.isEmpty()) {
            String error = "nameError";
            typeError = error;
            request.setAttribute("nameError", typeError);
            request.getRequestDispatcher(changePersonal).forward(request, response);
        } else if (newSurname.isEmpty()) {
            String error = "surnameError";
            typeError = error;
            request.setAttribute("surnameError", typeError);
            request.getRequestDispatcher(changePersonal).forward(request, response);
        } else {
            // se avatar è null che si fa? bisogna per forza sceglierne uno oppure si può anche lasciarlo vuoto?
            (request.getSession()).setAttribute("nameUserSession", newName);
            (request.getSession()).setAttribute("surnameUserSession", newSurname);
            (request.getSession()).setAttribute("avatarUserSession", newAvatar);
            request.setAttribute("errorPresentPersonal", null);
            
            User user1 = new User(email, dbpassword, newName, newSurname, library.ImageControl(newAvatar), admin, list_owner);
            userDAO.updateUserByEmail(user1);
            
            System.out.println("name e surname e avatar aggiornati.");
            
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
