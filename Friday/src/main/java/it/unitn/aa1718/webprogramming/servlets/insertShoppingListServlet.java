/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.dao.ShoppingListDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.ShoppingList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unitn.aa1718.webprogramming.friday.MyCookie;
import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMyCookieDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.friday.User;
import java.sql.Timestamp;
import javax.servlet.http.Cookie;


public class insertShoppingListServlet extends HttpServlet {

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
            out.println("<title>Servlet insertShoppingListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertShoppingListServlet at " + request.getContextPath() + "</h1>");
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
        ShoppingListDAO riverDAO = mySqlFactory.getShoppingListDAO();
        List shoppingLists = null;
        ShoppingList shoppingList = null;
        ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
        
        HttpSession session = request.getSession();
        Library library = new Library();

        // creazione di shoppingList
        int LID = library.LastEntryTable("LID", "lists");
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        String image = request.getParameter("image");
        int LCID = Integer.parseInt(request.getParameter("LCID"));
        String list_owner = (String)session.getAttribute("emailSession");
        int cookieID = -1;
        
        
        if(name.length()< 200 && note.length()< 200 && image.length()<500){ 

            //associo cookie anonimo se non loggato
            if(list_owner == null){

                MyCookieDAO riverCookieDAO = mySqlFactory.getMyCookieDAO();
                MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();

                //cancello eventuali cookie scaduti
                myCookieDAO.deleteDBExpiredCookies();

                //Creo cookie
                Cookie cookie = new Cookie("FridayAnonymous", Integer.toString(library.LastEntryTable("cookieID", "cookies")));
                cookie.setMaxAge(-1);
                cookieID = Integer.parseInt((String)cookie.getValue());

                Long Deadline = (new Timestamp(System.currentTimeMillis())).getTime();

                myCookieDAO.createCookie(new MyCookie(library.LastEntryTable("cookieID", "cookies"), LID, list_owner, Deadline));
                //System.out.println("zao zao il nuovo tuo cookie anonimo è stato inserito ed è "+cookie.getName()+", "+cookie.getValue()+"");
                session.setAttribute("cookieIDSession", Integer.parseInt(cookie.getValue()));
                response.addCookie(cookie);

                shoppingList = new ShoppingList(LID, name, note, library.ImageControl(image), LCID, list_owner, cookieID);
                shoppingListDAO.createShoppingList(shoppingList);

                //aggiungo LID al cookie anonimo
                myCookieDAO.updateLIDCookie(cookieID, LID);

            } else {

                cookieID = Integer.parseInt((String)session.getAttribute("cookieIDSession"));
                shoppingList = new ShoppingList(LID, name, note, library.ImageControl(image), LCID, list_owner, cookieID);
                shoppingListDAO.createShoppingList(shoppingList);
                
                UserDAO userDAO = new MySQLUserDAOImpl();
                User user = userDAO.getUser(list_owner);
                String email = list_owner;
                String password = user.getPassword();
                String userName = user.getName();
                String surname = user.getSurname();
                String avatar = user.getAvatar();
                boolean admin = user.getAdmin();
                boolean isListOwner = user.getListOwner();
                boolean confirmed = user.getConfirmed();
                
                if (!isListOwner) {
                    isListOwner = true;
                }
                
                user = new User(email, password, userName, surname, avatar, admin, isListOwner, confirmed);
                
                userDAO.updateUserByEmail(user);
            }

            // recupero di tutti gli shoppingList del DB
            shoppingLists = shoppingListDAO.getAllShoppingLists();
            
            request.setAttribute("goodInsertShoppingList", "true");
            response.sendRedirect("gestioneListe.jsp");
            
       } else {
            response.sendRedirect("error.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        int listToDelete = Integer.parseInt(request.getParameter("deleteList"));
        HttpSession session = request.getSession();
        
        ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
        boolean deleted = shoppingListDAO.deleteShoppingList(listToDelete);
        
        if (deleted){
            request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
        } else {
            response.sendRedirect("faq.jsp");
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
