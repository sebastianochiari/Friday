/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.dao.entities.*;
import it.unitn.aa1718.webprogramming.friday.*;
import it.unitn.aa1718.webprogramming.extra.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tommi
 */
public class insertProductServlet extends HttpServlet {

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
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insertProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertProductServlet at " + request.getContextPath() + "</h1>");
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
        ProductDAO riverDAO = mySqlFactory.getProductDAO();
        
        List products = null;
        Product product = null;
        
        ProductDAO productDAO = new MySQLProductDAOImpl();
        
//        // cancellazione di product memorizzati sul DB
//        products = productDAO.getAllProducts();
//        for (Object u : products) {
//            productDAO.deleteProduct((Product) u);
//        }

        // creazione di product
        Library library = new Library();
        int PID = library.LastEntryTable("PID", "products");
        String email = (String) (request.getSession()).getAttribute("emailSession");
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        String logo = request.getParameter("logo");
        String photo = request.getParameter("photo");
        int PCID = Integer.parseInt(request.getParameter("PCID"));
        
        if(email.length()<200 && name.length()< 200 && logo.length()< 200 && note.length()<500 && photo.length()<200){ 

            Product product1 = new Product(PID, name, note, library.ImageControl(logo), library.ImageControl(photo), PCID, email);

            // memorizzazione del nuovo product nel DB
            productDAO.createProduct(product1);

            request.setAttribute("goodInsertProduct", "true");
            response.sendRedirect("adminSection.jsp");
            
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        int comando = Integer.parseInt(request.getParameter("changeProduct"));
        int lista = Integer.parseInt(request.getParameter("selectedListToChangeProduct"));
        int scelta = Integer.parseInt(request.getParameter("scelta"));
        
        ProductListDAO productListDAO = new MySQLProductListDAOImpl();
        ProductList productList = null;
        int amount = 1;
        if (scelta != 4) {
            productList = productListDAO.getProductList(comando, lista);
            System.out.println("------"+productList);
            amount = productList.getQuantity();
        }
        
        switch (scelta){
            case 1: 
                amount--;
                productList = new ProductList(comando, lista, amount);
                if (amount>0) {
                    productListDAO.updateProductList(productList);
                } else {
                    productListDAO.deleteProductList(productList);
                };
                break;
            case 2:
                productList = new ProductList(comando, lista, amount);
                productListDAO.deleteProductList(productList);
                break;
            case 3:
                amount++;
                productList = new ProductList(comando, lista, amount);
                productListDAO.updateProductList(productList);
                break;
            case 4:
                productList = new ProductList(comando, lista, amount);
                productListDAO.createProductList(productList);
                break;
            default: 
                response.sendRedirect("faq.jsp");
                break;
        }
            
        response.sendRedirect("gestioneListe.jsp");
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
