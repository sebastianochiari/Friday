/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
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
     * Metodo GET: inserisce un prodotto nuovo nel database, effettuando i relativi controlli
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        ProductDAO riverProductDAO = mySqlFactory.getProductDAO();
        UserDAO riverUserDAO = mySqlFactory.getUserDAO();
        HttpSession session = request.getSession();
        
        long RedirectAfterProduct = (long)session.getAttribute("RedirectAfterProduct");
        
        List products = null;
        Product product = null;
        
        ProductDAO productDAO = new MySQLProductDAOImpl();
        UserDAO userDAO = new MySQLUserDAOImpl();
        
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
            if(!userDAO.getUser(email).getAdmin()){
 
                SharingProductDAO riverSharingProductDAO = mySqlFactory.getSharingProductDAO();
                SharingProductDAO sharingProductDAO = new MySQLSharingProductDAOImpl();
                sharingProductDAO.createSharingProduct(new SharingProduct(email, PID));           
            }
            
            productDAO.createProduct(product1);
            request.setAttribute("goodInsertProduct", "true");
            
            if(RedirectAfterProduct == 0){
                response.sendRedirect("search.jsp");
            } else {
                response.sendRedirect("adminSection.jsp");
            }
            
        } else {
            response.sendRedirect("error.jsp");
        }
        
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Metodo POST della servlet:  COSA FA?
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        ShoppingListDAO riverShoppingListDAO = mySqlFactory.getShoppingListDAO();
        ProductDAO riverProductDAO = mySqlFactory.getProductDAO();
        
        ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
        ShoppingListCategoryDAO shoppingListCategoryDAO = new MySQLShoppingListCategoryDAOImpl();
        ProductListDAO productListDAO = new MySQLProductListDAOImpl();
        
        
        HttpSession session = request.getSession();
        Library library = new Library();
        int comando = Integer.parseInt(request.getParameter("changeProduct"));
        int lista = 0;
        int scelta = Integer.parseInt(request.getParameter("scelta"));
        
        if (session.getAttribute("emailSession") == null){

            int keyLID = library.LastEntryTable("LID", "lists");
            String keyName = "Anonymous"+keyLID;
            String keyNote = null;
            String keyImage = null;
            // soluzione pessima, ma non avevo tempo di pensare a un altro modo complicato per ricavare un LCID valido
            int keyLCID = shoppingListCategoryDAO.getShoppingListCategory(1).getLCID();
            String keyListOwner = null;
            int keyCookieID = (int)session.getAttribute("cookieIDSession");
            ShoppingList shoppingList = new ShoppingList(keyLID, keyName, keyNote, keyImage, keyLCID, null, keyCookieID);
            lista = Integer.parseInt(shoppingListDAO.createShoppingList(shoppingList));
        } else {
            lista = Integer.parseInt(request.getParameter("selectedListToChangeProduct"));
        }

        ProductList productList = null;
        
        int amount = 1;
        if (scelta != 4) {
            productList = productListDAO.getProductList(comando, lista);
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
                boolean inList = false;
                List listaProdotti = productListDAO.getPIDsByLID(lista);
                if (listaProdotti.isEmpty()){
                    productList = new ProductList(comando, lista, amount);
                    productListDAO.createProductList(productList);
                } else {
                    for (int i=0; i<listaProdotti.size(); i++) {
                        if (((ProductList)listaProdotti.get(i)).getPID() == comando){
                            amount = ((ProductList)listaProdotti.get(i)).getQuantity() + 1;
                            productList = new ProductList(comando, lista, amount);
                            productListDAO.updateProductList(productList);
                            inList = true;
                        } 
                    }
                    if (!inList) {
                        productList = new ProductList(comando, lista, amount);
                        productListDAO.createProductList(productList);
                    };
                };
                
                break;
            default: 
                response.sendRedirect("faq.jsp");
                break;
        }
        
        System.out.println(" prima di settare selectedList: ");
        request.setAttribute("goodInsertShoppingList", "true");
        session.setAttribute("selectedList", lista);
        
        System.out.println(" prima di redirezionare: ");
        response.sendRedirect("handlingListServlet?selectedList="+lista);
        System.out.println(" dopo di redirezionare: ");
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
