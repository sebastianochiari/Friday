/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */

package it.unitn.aa1718.webprogramming.servlets;

import static it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory.User;
import it.unitn.aa1718.webprogramming.dao.ProductCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ProductDAO;
import it.unitn.aa1718.webprogramming.dao.ProductListDAO;
import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.dao.ShoppingListCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ShoppingListDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.Product;
import it.unitn.aa1718.webprogramming.friday.Sharing;
import it.unitn.aa1718.webprogramming.friday.ShoppingList;
import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.MessageDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMessageDAOImpl;
import it.unitn.aa1718.webprogramming.friday.Message;
import it.unitn.aa1718.webprogramming.friday.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
        library.recuperoListeUtenteloggato(request, response);
        HttpSession session = request.getSession();
        int listaSelezionata;
        int selectedList;
        
        //se lista selezionata, inoltro nella pagina della lista, altrimenti mostro la pagina
        //di gestione delle liste
        
        if(request.getParameter("selectedList") == null){
            
            if(session.getAttribute("selectedList") != null){
                listaSelezionata = (int)session.getAttribute("selectedList");
            } else {
                listaSelezionata = 0;
            }
            
        } else { 
            
            listaSelezionata = Integer.parseInt(request.getParameter("selectedList"));
            session.setAttribute("selectedList", listaSelezionata);

            if (selectedList == 0) {
                selectedList = Integer.parseInt(request.getParameter("selectedList"));
                session.setAttribute("selectedList", selectedList);
            } else {
                ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
                ShoppingList shoppingList = shoppingListDAO.getShoppingList(selectedList);
                ShoppingListCategoryDAO shoppingListCategoryDAO = new MySQLShoppingListCategoryDAOImpl();
                UserDAO userDAO = new MySQLUserDAOImpl();
                User user = userDAO.getUser(shoppingList.getListOwner());
                SharingDAO sharingDAO = new MySQLSharingDAOImpl();
                List sharing = sharingDAO.getAllEmailsbyList(selectedList);
                
                String [] listaSelezionata = new String [5];
                String [] utenteProprietario = new String [5];
                String [][] listaCondivisa = new String [sharing.size()][4];
                
                for (int i=0; i<sharing.size(); i++) {
                    listaCondivisa[i][0] = userDAO.getUser(((Sharing)sharing.get(i)).getEmail()).getName();
                    listaCondivisa[i][1] = userDAO.getUser(((Sharing)sharing.get(i)).getEmail()).getSurname();
                    listaCondivisa[i][2] = ((Sharing)sharing.get(i)).getEmail();
                    listaCondivisa[i][3] = Boolean.toString(((Sharing)sharing.get(i)).getDelete());
                }
                
                listaSelezionata[0] = Integer.toString(shoppingList.getLID());
                listaSelezionata[1] = shoppingList.getName();
                listaSelezionata[2] = shoppingList.getNote();
                listaSelezionata[3] = shoppingList.getImage();
                listaSelezionata[4] = shoppingListCategoryDAO.getShoppingListCategory(shoppingList.getLCID()).getName();
                
                utenteProprietario[0] = user.getName();
                utenteProprietario[1] = user.getSurname();
                utenteProprietario[2] = user.getEmail();
                utenteProprietario[3] = user.getPassword();
                utenteProprietario[4] = user.getAvatar();
                
                session.setAttribute("listaCondivisa", listaCondivisa);            
                session.setAttribute("utenteProprietario", utenteProprietario);            
                session.setAttribute("listaSelezionata", listaSelezionata);            
                session.setAttribute("selectedList", selectedList);            
                library.prodottiDellaLista(selectedList, request);
                request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
            }
        }
        
        // DA TOGLIERE
        if(listaSelezionata == 10){
            listaSelezionata = 0;
        }
        //////////////
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
        prodotto[0] = product.getName();
        prodotto[1] = product.getNote();
        prodotto[2] = product.getLogo();
        prodotto[3] = product.getPhoto();
        prodotto[4] = productCategoryDAO.getProductCategory(product.getPCID()).getName();
        prodotto[5] = userDAO.getUser(product.getEmail()).getName();
        prodotto[6] = Integer.toString(product.getPID());
        
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
