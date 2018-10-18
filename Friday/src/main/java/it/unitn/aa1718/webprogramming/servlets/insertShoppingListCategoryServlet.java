/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.ShoppingListCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.ShoppingListCategory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leo97
 */
public class insertShoppingListCategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet insertShoppingListCategoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertShoppingListCategoryServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        ShoppingListCategoryDAO riverDAO = mySqlFactory.getShoppingListCategoryDAO();
        
        List shoppingListCategories = null;
        ShoppingListCategory shoppingListCategory = null;
        
        ShoppingListCategoryDAO shoppingListCategoryDAO = new MySQLShoppingListCategoryDAOImpl();
        
//        // cancellazione di product memorizzati sul DB
//        shoppingListCategories = shoppingListCategoryDAO.getAllShoppingListCategoryies();
//        for (Object u : shoppingListCategories) {
//            shoppingListCategoryDAO.deleteShoppingListCategory((ShoppingListCategory) u);
//        }

        // creazione di shoppingListCategory
        Library library = new Library();
        int LCID = library.LastEntryTable("LCID", "list_categories");
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        String image = request.getParameter("image");
        String email = request.getParameter("email");
        
        ShoppingListCategory shoppingListCategory1 = new ShoppingListCategory(LCID, name, note, library.ImageControl(image), email);
    
        // memorizzazione del nuovo shoppingListCategory nel DB
        shoppingListCategoryDAO.createShoppingListCategory(shoppingListCategory1);
        
        // recupero di tutti gli shoppingListCategory del DB
        shoppingListCategories = shoppingListCategoryDAO.getAllShoppingListCategories();
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
