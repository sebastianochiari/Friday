/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.dao.ProductCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ProductDAO;
import it.unitn.aa1718.webprogramming.dao.ProductListDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.Product;
import it.unitn.aa1718.webprogramming.friday.ProductList;
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
 * @author tommi
 */
public class handlingListServlet extends HttpServlet {

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
            out.println("<title>Servlet handlingListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet handlingListServlet at " + request.getContextPath() + "</h1>");
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
        
        Library library = new Library();
        library.recuperoListeUtenteloggato(request);
        HttpSession session = request.getSession();
        
        int listaSelezionata = Integer.parseInt(request.getParameter("selectedList"));
        
        if (listaSelezionata == 0) {
            request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
        } else {
            request.setAttribute("listaAttiva", listaSelezionata);
            library.prodottiDellaLista(listaSelezionata, request);
            request.getRequestDispatcher("list.jsp").forward(request, response);
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
        
        int PIDProdotto = Integer.parseInt(request.getParameter("SelectedProduct"));
        HttpSession session = request.getSession();
        
        ProductDAO productDAO = new MySQLProductDAOImpl();
        Product product = productDAO.getProduct(PIDProdotto);
        
        ProductListDAO productListDAO = new MySQLProductListDAOImpl();
        ProductCategoryDAO productCategoryDAO = new MySQLProductCategoryDAOImpl();
        UserDAO userDAO = new MySQLUserDAOImpl();
        
        String [] prodotto = new String [7];
        
        for(int i=0; i<prodotto.length; i++){
            prodotto[i] = product.getName();
            prodotto[i] = product.getNote();
            prodotto[i] = product.getLogo();
            prodotto[i] = product.getPhoto();
            prodotto[i] = productCategoryDAO.getProductCategory(product.getPCID()).getName();
            prodotto[i] = userDAO.getUser(product.getEmail()).getName();
            prodotto[i] = Integer.toString(product.getPID());
        }
        
        session.setAttribute("prodotto", prodotto);
        request.getRequestDispatcher("showProduct.jsp").forward(request, response);
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
