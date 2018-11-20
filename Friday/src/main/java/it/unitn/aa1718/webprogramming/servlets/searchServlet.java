/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.ProductCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ProductDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.Product;
import it.unitn.aa1718.webprogramming.friday.ProductCategory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marta
 */
public class searchServlet extends HttpServlet {

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
            out.println("<title>Servlet searchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchServlet at " + request.getContextPath() + "</h1>");
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
        
        int PCID = -1;
        HttpSession session = request.getSession();
        String input = request.getParameter("inputSearch");
        if(request.getParameter("inputCategory") != null)
            PCID = Integer.parseInt(request.getParameter("inputCategory"));
        
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        ProductDAO ProductriverDAO = mySqlFactory.getProductDAO();
        ProductDAO productDAO = new MySQLProductDAOImpl();
        ProductDAO ProductCategoryriverDAO = mySqlFactory.getProductDAO();
        ProductCategoryDAO productCategoryDAO = new MySQLProductCategoryDAOImpl();
        Library library = new Library();
        
        List products = null;
        if(PCID > 0){
            products = productDAO.getProductsByNameAndPCID(PCID, input);
        } else if (request.getParameter("exampleFormControlSelect1") == "per categoria"){ 
            products = productDAO.getAllProducts("per categoria");
        } else {
            products = productDAO.getAllProducts("alfabicamente");
        }
        String[][] searchProductResult = new String[products.size()][7];
        
        for(int i=0; i<products.size(); i++){
            
            searchProductResult[i][0] = Integer.toString(((Product)(products.get(i))).getPID());
            searchProductResult[i][1] = ((Product)(products.get(i))).getName();
            searchProductResult[i][2] = ((Product)(products.get(i))).getNote();
            searchProductResult[i][3] = ((Product)(products.get(i))).getLogo();
            searchProductResult[i][4] = ((Product)(products.get(i))).getPhoto();
            searchProductResult[i][5] = (productCategoryDAO.getProductCategory(((Product)(products.get(i))).getPCID())).getName();
            searchProductResult[i][6] = ((Product)(products.get(i))).getEmail();
            
        }
        
        session.setAttribute("resultSearch", searchProductResult);
        response.sendRedirect("search.jsp");
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
