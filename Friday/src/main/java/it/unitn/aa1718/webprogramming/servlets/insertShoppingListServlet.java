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
import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.dao.ProductListDAO;
import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMyCookieDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.friday.ProductList;
import it.unitn.aa1718.webprogramming.friday.User;


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
        int changeProduct = 0;
        
        System.out.println(" sorgente: "+session.getAttribute("sorgente"));
        System.out.println(" changeProduct1: "+session.getAttribute("changeProduct1"));
        
        if (session.getAttribute("sorgente") != null && session.getAttribute("sorgente").equals("creoListaEProdotto")) {
            changeProduct = Integer.parseInt((String)session.getAttribute("changeProduct1"));
        }
        
        System.out.println(" --------------- " + changeProduct);
        
        
        if(name.length()< 200 && note.length()< 200 && image.length()<500){ 

            //associo cookie anonimo se non loggato
            if(list_owner == null){

                cookieID = (int)session.getAttribute("cookieIDSession");
                session.setAttribute("listaAnonimo", true);
                MyCookieDAO riverCookieDAO = mySqlFactory.getMyCookieDAO();
                MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();
                
                shoppingList = new ShoppingList(LID, name, note, library.ImageControl(image), LCID, list_owner, cookieID);
                shoppingListDAO.createShoppingList(shoppingList);

                //aggiungo LID al cookie anonimo
                myCookieDAO.updateLIDCookie(cookieID, LID);

            } else {

                cookieID = (int)session.getAttribute("cookieIDSession");
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
            
            if (changeProduct > 0) {
                ProductListDAO productListDAO = new MySQLProductListDAOImpl();
                productListDAO.createProductList(new ProductList(changeProduct, shoppingList.getLID(), 1));
                response.sendRedirect("handlingListServlet?selectedList="+shoppingList.getLID());
            } else {
                request.setAttribute("goodInsertShoppingList", "true");
                session.setAttribute("selectedList", 0);
                response.sendRedirect("handlingListServlet");
            }
            
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
        
        int proprietario = Integer.parseInt(request.getParameter("proprietario"));
        int listToDelete = Integer.parseInt(request.getParameter("deleteList"));
        HttpSession session = request.getSession();
        
        ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
        SharingDAO sharingDAO = new MySQLSharingDAOImpl();
        
        switch (proprietario) {
            case 1:
                boolean deleted = shoppingListDAO.deleteShoppingList(listToDelete);
        
                if (deleted){
                    
                    if(session.getAttribute("emailSession") != null){
                        
                        if(shoppingListDAO.getShoppingListsByOwner((String)session.getAttribute("emailSession")).isEmpty()) {
                            session.setAttribute("listaAnonimo", false);
                            
                        }
                    } else {
                        
                        session.setAttribute("listaAnonimo", false);
                    }
                    response.sendRedirect("handlingListServlet?selectedList=0");
             
                } else {
                    response.sendRedirect("faq.jsp");
                };
                break;
            case 0:
                boolean exit = sharingDAO.deleteSharing(sharingDAO.getSharing(listToDelete, (String)session.getAttribute("emailSession")));
                
                if (exit){
                    response.sendRedirect("handlingListServlet?selectedList=0");
                } else {
                    response.sendRedirect("faq.jsp");
                };
                break;
            default:
                response.sendRedirect("error.jsp");
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
