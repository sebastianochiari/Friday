/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMyCookieDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.MyCookie;
import it.unitn.aa1718.webprogramming.friday.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class indexServlet extends HttpServlet {

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
            out.println("<title>Servlet indexServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet indexServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Metodo GET che si occupa della gestione della home-page index.jsp
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
            Cookie[] cookies = request.getCookies();

            DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
            UserDAO riverUserDAO = mySqlFactory.getUserDAO();
            UserDAO userDAO = new MySQLUserDAOImpl();
            Library library = new Library();
            boolean boolEmailSession = false;
            
            MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();

            // setto banner cookie
            if((request.getSession()).getAttribute("cookieIDSession") == null) {
                (request.getSession()).setAttribute("bannerCookie", true);
            } else {
                (request.getSession()).setAttribute("bannerCookie", false);
            }
            
            //elimino cookie scaduti
            List<MyCookie> cookieScaduti = myCookieDAO.deleteDBExpiredCookies();
                for(int i=0; i<cookieScaduti.size(); i++){
                    myCookieDAO.deleteCookieByCookieID(cookieScaduti.get(i).getCookieID());
                }
            
            List<MyCookie> myCookie = myCookieDAO.getAllCookie();
            
            //System.out.println("cookie DB  size = -"+myCookie.size()+"-");
            for(int i=0; i<myCookie.size(); i++){
                //System.out.println("cookie DB  VALUE = -"+myCookie.get(i).getCookieID()+"-");
            }
            

            if(cookies != null){

               for(int j=0; j<myCookie.size(); j++) {
                   
                   //System.out.println("cookie DB  VALUE = -"+myCookie.get(j).getCookieID()+"-");

                    for(int i=0; i<cookies.length; i++){
                        
                        //System.out.println("cookie BROWSER  VALUE = -"+cookies[i].getValue()+"- NAME = "+cookies[i].getName());
                        
                        if((cookies[i].getValue()).compareTo(Integer.toString(myCookie.get(j).getCookieID())) == 0){
                            
                           //System.out.println("SCELTO QUESTOOOOO = "+cookies[i].getValue());


                            String emailSession = myCookie.get(j).getEmail();

                            if (emailSession ==  null || !userDAO.getUser(emailSession).getConfirmed()){


                                boolEmailSession = false;
                            } else {
                                (request.getSession()).setAttribute("emailSession", emailSession);

                                boolEmailSession = true;
                            }

                            (request.getSession()).setAttribute("boolEmailSessionScriptlet", boolEmailSession);
                            (request.getSession()).setAttribute("cookieIDSession", myCookie.get(j).getCookieID());
                            (request.getSession()).setAttribute("deadlineSession", myCookie.get(j).getDeadline());
                            (request.getSession()).setAttribute("LIDSession", myCookie.get(j).getLID());

                        }
                    }
                }
               
                User user = userDAO.getUser((String)(request.getSession()).getAttribute("emailSession"));
                
                if((request.getSession()).getAttribute("emailSession") != null){
                    (request.getSession()).setAttribute("nameUserSession", user.getName());
                    (request.getSession()).setAttribute("surnameUserSession", user.getSurname());
                    (request.getSession()).setAttribute("avatarUserSession", user.getAvatar());
                    (request.getSession()).setAttribute("adminUserSession", user.getAdmin());
                    (request.getSession()).setAttribute("list_OwnerUserSession", user.getListOwner());
                    (request.getSession()).setAttribute("confirmedUserSession", user.getConfirmed());
                }


            }
            
            library.recuperoListeUtenteloggato(request, response);
            library.createListIndex(request);
            library.createProductCategory(request);
            library.createShoppingListCategory(request);
            library.createAutocomplete(request.getSession());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }                  

    /**
     * Handles the HTTP <code>POST</code> method.
     * Metodo POST non implementato
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
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
