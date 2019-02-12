/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.extra.Library;
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
import it.unitn.aa1718.webprogramming.friday.User;
import java.sql.Timestamp;
import javax.servlet.http.HttpSession;

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
     * Metodo GET non implementato
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
     * Metodo POST della servlet: effettua il login, se ha successo setta un cookie per tener traccia dell'user
     * Se fallisce redireziona ad una pagina di errore predefinita.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //connessione
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        
        //struttura utenti
        UserDAO riverUserDAO = mySqlFactory.getUserDAO();
        UserDAO userDAO = new MySQLUserDAOImpl();
        List users = null;
        
        //struttura cookie
        MyCookieDAO riverCookieDAO = mySqlFactory.getMyCookieDAO();
        MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();
        MyCookie myCookie = null;
        
        //cose che servono
        DBSecurity encrypt = new DBSecurity();
        HttpSession session = request.getSession();
        Library library = new Library();
      
        //dati pagina jsp
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
        
//        System.out.println("email:" + email);
//        System.out.println("psw:" + password);
//        System.out.println("ricordami:" + ricordami);
//        System.out.println("typeError: " + typeError);
//        System.out.println("registerForm: " + registerForm);
//        System.out.println("EMIL + PASSWORD + ricordami : " + email + password);
        

        if(email.length()<200 && password.length()< 200){ 

            request.setAttribute("email", email);
            User tmpUser = userDAO.getUser(email);

            //ritorna false se non esiste una mail nel database 
            if (!userDAO.checkUser(email) || !tmpUser.getConfirmed()) {
                //System.out.println("questa email non esiste nel database");
                String error = "emailError";
                typeError = error;
                request.setAttribute("errorEmail", typeError);
                request.getRequestDispatcher(registerForm).forward(request, response);

            } else {

                String pswencrypted = encrypt.setSecurePassword(password, email);
                //System.out.println("LA PASSWORD CRIPTATA IN LOGINSERVLET è :" + pswencrypted);
                String dbpassword = userDAO.getPasswordByUserEmail(email);

                if (pswencrypted.equals(dbpassword)) {

                    //System.out.println("LE PASSWORD SONO CORRETTE!! SETTO I COOKIE E REDIREZIONO A INDEX.JSP");
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
                        //System.out.println("COOKIE ID = "+library.LastEntryTable("cookieID", "cookies")+"+ LID = "+LID+" EMAIL = "+email+" DEADLINE = "+Deadline);
                        MyCookie myNewCookie = new MyCookie(library.LastEntryTable("cookieID", "cookies"), LID, email, Deadline); 
                        myCookieDAO.createCookie(myNewCookie);
                        response.addCookie(cookie);
                        
                        session.setAttribute("cookieIDSession", myNewCookie.getCookieID());
                        System.out.println("il nuovo tuo cookie è stato inserito ed è "+cookie.getName()+", "+cookie.getValue()+"");

                        
                        (request.getSession()).setAttribute("emailSession", email);
                        (request.getSession()).setAttribute("nameUserSession", userDAO.getUser(email).getName());
                        
                        
                        
                    } else {
                        System.out.println("Bentornato amico! il tuo ID è "+ myCookie.getCookieID()+"\n");
                    }

                    response.sendRedirect("indexServlet");

                } else {

                    //System.out.println("PASSWORD DIVERSE !!!!!!!!!!");
                    //System.out.println("la password non corrisponde all'email inserita ");
                    String error = "errorPassword";
                    typeError = error;
                    request.setAttribute("errorPassword", typeError);
                    request.getRequestDispatcher(registerForm).forward(request, response);

                }

            }  
 
            
       } 
        /*
        
        VOGLIAMO TENERE L'ERRORE SE DURANTE IL LOGIN INSERISCO UN NOME DA 300 CARATTERI (TENERE L'ELSE) 
        OPPURE TORNO SULLA PAGINA DI LOGIN DICENDO " QUESTO USER NON ESISTE " (SENZA ELSE) ?????
        
        else {
            response.sendRedirect("error.jsp");
       }
        
        */


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










































