/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.dao.entities.*;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.friday.*;
import it.unitn.aa1718.webprogramming.extra.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;


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
     * Handles the HTTP <code>POST </code> method.
     * Metodo POST che registra l'utente nel database. 
     * Se la registrazione fallisce viene redirezionato ad una pagina di errore predefinita
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        UserDAO riverUserDAO = mySqlFactory.getUserDAO();
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
        String typeError = null;
        String registerForm = null;

        email = request.getParameter("email");
        password = request.getParameter("password");
        passwordcheck = request.getParameter("password1");
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        avatar = request.getParameter("avatar");
        typeError = request.getParameter("typeError");
        registerForm = request.getParameter("registerForm");
        
        if(email.length()<200 && password.length()< 200 && name.length()< 200 && surname.length()<500 && avatar.length()<200){ 

            String pswEncrypted = encrypt.setSecurePassword(password, email);
            HttpSession session = request.getSession();

            boolean isOkay = encrypt.checkString(password);

            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);

            if(!userDAO.checkEmail(email)){

                response.sendRedirect("index.jsp");

            } else if (userDAO.checkUser(email)) {

                String error = "emailError";
                typeError = error;
                request.setAttribute("errorEmail", typeError);
                request.getRequestDispatcher(registerForm).forward(request, response);

            } else if(!isOkay) {   

                String error = "errorPassword";
                typeError = error;
                request.setAttribute("errorPassword", typeError);                
                request.getRequestDispatcher(registerForm).forward(request, response);

            } else if (!password.equals(passwordcheck)) {       

                String error = "errorCheckPassword";
                typeError = error;
                request.setAttribute("errorCheckPassword", typeError);

                //System.out.println( "-----Le password non coicidono");
                //da sistemare il ritorno al insertUser.jsp
                request.getRequestDispatcher(registerForm).forward(request, response);
                //response.sendRedirect("insertUser.jsp");

             } else {

                User user1 = new User(email, pswEncrypted, name, surname, library.ImageControl(avatar), false, false, false);
                userDAO.createUser(user1);

                //aggiorno email cookie e lista se precedentemente era un cookie anonimo
                if(session.getAttribute("cookieIDSession") != null){

                    MyCookieDAO riverCookieDAO = mySqlFactory.getMyCookieDAO();
                    MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();

                    ShoppingListDAO riverShoppingListDAO = mySqlFactory.getShoppingListDAO();
                    ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();

                    int cookieID = Integer.parseInt((String)session.getAttribute("cookieIDSession"));
                    int cookieLID =  myCookieDAO.getLIDbyCookieID(cookieID);
                    System.out.println("cookie LID = "+cookieLID);
                    shoppingListDAO.updateEmailShoppingList(cookieLID, email);
                    myCookieDAO.updateEmailCookie(cookieID, email);

                }


                //dobbiamo trovare un host che funzioni
                try {
                    library.sendMail(email, name, surname);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                //System.out.println("la password è stata criptata correttamente. SONO IN INSERTUSERSERVLET ");
                // ritorno alla pagina iniziale
                response.sendRedirect("confirmRegistration.jsp");

            }
            
            
       } else {
            response.sendRedirect("error.jsp");
       }
        
        
    }
     

    /**
     * Handles the HTTP <code>GET</code> method.
     * Metodo GET non implementato.
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
