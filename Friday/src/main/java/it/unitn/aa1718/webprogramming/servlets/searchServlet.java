/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.ProductDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.Product;
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
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        ProductDAO riverDAO = mySqlFactory.getProductDAO();
        List products = null;
        ProductDAO productDAO = new MySQLProductDAOImpl();        
        Library library = new Library();
        
        
        String input = null;
        input = request.getParameter("inputSearch");
       
        String inputClick = request.getParameter("selectedPCategory");
        if(inputClick != null){
            //int inputClickInteger = Integer.parseInt(inputClick);
            System.out.println(" selected category in generale vale: " + inputClick);
            response.sendRedirect("faq.html");
        } 
        
        else {
        //SE NON HO SELEZIONATO NULLA NELLE CATEGORIE GENERICHE 
        System.out.println("NON HAI SELEZIONATO LE CATEGORIE DI PRODOTTO GENERICHE. PASSO CONTROLLO A DROPDOWN LIST CON INPUT");
        
        
        if(input != null ){
        int inputCategory = Integer.parseInt(request.getParameter("inputCategory"));
        
   //    String selectedCategory = request.getParameter("navbarDropdownMenuLink1"); //prendo categoria selezionata da drop down list
        
        
        System.out.println("---inputCategory A SX VALE: " + inputCategory);
        
        if(inputCategory == -1) {
            
            products = productDAO.getProductsByName(input);
            for(int i=0; i<products.size(); i++){
                System.out.println(((Product)products.get(i)).getName()); //se nessuna selezione a SX stampa i tutti i prodotti
            }
            response.sendRedirect("index.jsp"); //MODIFICA REDIREZIONAMENTO
         } else {
            products = productDAO.getProductsByNameAndPCID(inputCategory, input); 
                        //se ho inserito un input nella search bar, ritorna i prodotti in base alla ricerca con filtro categoria di SX
      
        
            if(products.size() == 0){
                System.out.println("NESSUN PRODOTTO CORRISPONDE ALLA PRODUCT_CATEGORIES E PRODOTTO CERCATO");
                response.sendRedirect("index.jsp");
            } else {
            
                for(int i=0; i<products.size(); i++){
                    System.out.println(((Product)products.get(i)).getName()); 
                }
                response.sendRedirect("index.jsp"); //MODIFICA REDIREZIONAMENTO
            }
         
        }  
        } else {
          
        String reqParam = request.getParameter("selectedPCategory");
        System.out.println("----------------------Selected category vale :  " + reqParam);
        
        
     //   products = productDAO.getProductsByPCID()
        
        response.sendRedirect("index.jsp");
        
        }
        
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

    
    
    
    protected void SelectedProductCategory (){
        
        
        
        
    }
    
}
