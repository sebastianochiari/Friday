/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.dao.entities.*;
import it.unitn.aa1718.webprogramming.extra.*;
import it.unitn.aa1718.webprogramming.friday.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marta
 */
public class insertProductCategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet insertProductCategoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertProductCategoryServlet at " + request.getContextPath() + "</h1>");
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
        ProductCategoryDAO riverDAO = mySqlFactory.getProductCategoryDAO();
        
        List productCategories = null;
        ProductCategory productCategory = null;
        
        ProductCategoryDAO productCategoryDAO = new MySQLProductCategoryDAOImpl();
        
//        // cancellazione di product memorizzati sul DB
//        productCategories = productCategoryDAO.getAllProductCategories();
//        for (Object u : productCategories) {
//            productCategoryDAO.deleteProductCategory((ProductCategoryCategory) u);
//        }

        // creazione di productCategory
        Library library = new Library();
        int PCID = library.LastEntryTable("PCID", "product_categories");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        String logo = request.getParameter("logo");
        
        ProductCategory productCategory1 = new ProductCategory(PCID, name, note, library.ImageControl(logo), email);
    
        // memorizzazione del nuovo productCategory nel DB
        productCategoryDAO.createProductCategories(productCategory1);
        
        // recupero di tutti gli productCategory del DB
        productCategories = productCategoryDAO.getAllProductCategories();
        
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
