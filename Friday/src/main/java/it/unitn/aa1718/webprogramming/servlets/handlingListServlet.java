/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */

package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.dao.ProductCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ProductDAO;
import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.dao.ShoppingListCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ShoppingListDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.Sharing;
import it.unitn.aa1718.webprogramming.friday.ShoppingList;
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
     * Metodo GET che si occupa della gestione delle liste della spes selezionata
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
        int selectedList = 0;
        
        library.recuperoListeUtenteloggato(request, response);
        library.createListIndex(request);
        library.createProductCategory(request);
        library.createShoppingListCategory(request);
        
        //se lista selezionata, inoltro nella pagina della lista, altrimenti mostro la pagina di gestione delle liste
        
        if(request.getParameter("selectedList") == null){
            
            if(session.getAttribute("selectedList") != null){
                selectedList = (int)session.getAttribute("selectedList");
            } else {
                selectedList = 0;
            }
            
        } else { 
            selectedList = Integer.parseInt(request.getParameter("selectedList"));
        }

        if (selectedList == 0) {
            session.setAttribute("listaAttiva", selectedList);
            response.sendRedirect("gestioneListe.jsp");
        } else {
            ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
            ShoppingList shoppingList = shoppingListDAO.getShoppingList(selectedList);
            ShoppingListCategoryDAO shoppingListCategoryDAO = new MySQLShoppingListCategoryDAOImpl();
            UserDAO userDAO = new MySQLUserDAOImpl();
            SharingDAO sharingDAO = new MySQLSharingDAOImpl();
            List sharing = sharingDAO.getAllEmailsbyList(selectedList);
            ProductDAO productDAO = new MySQLProductDAOImpl();
            ProductCategoryDAO productCategoryDAO = new MySQLProductCategoryDAOImpl();

            String [] listaCorrente = new String [10];
            String [] utenteProprietario = new String [5];
            String [][] listaCondivisa = new String [sharing.size()][6];
            
            User user = null;
            if (session.getAttribute("emailSession") != null){
                user = userDAO.getUser(shoppingList.getListOwner());
                for (int i=0; i<sharing.size(); i++) {
                    listaCondivisa[i][0] = userDAO.getUser(((Sharing)sharing.get(i)).getEmail()).getName();
                    listaCondivisa[i][1] = userDAO.getUser(((Sharing)sharing.get(i)).getEmail()).getSurname();
                    listaCondivisa[i][2] = ((Sharing)sharing.get(i)).getEmail();
                    listaCondivisa[i][3] = Boolean.toString(((Sharing)sharing.get(i)).getDelete());
                    listaCondivisa[i][4] = Boolean.toString(((Sharing)sharing.get(i)).getModify());
                    listaCondivisa[i][5] = Boolean.toString(((Sharing)sharing.get(i)).getAdd());
                }
            } else {
                listaCondivisa = new String [0][6];
            }
            
            listaCorrente[0] = Integer.toString(selectedList);
            listaCorrente[1] = shoppingList.getName();
            listaCorrente[2] = shoppingList.getNote();
            listaCorrente[3] = shoppingList.getImage();
            listaCorrente[4] = shoppingListCategoryDAO.getShoppingListCategory(shoppingList.getLCID()).getName();
            listaCorrente[5] = Integer.toString(shoppingList.getCookieID());
            listaCorrente[6] = Integer.toString(shoppingList.getLCID());
            listaCorrente[7] = Boolean.toString(true);
            listaCorrente[8] = Boolean.toString(true);
            listaCorrente[9] = Boolean.toString(true);

            if (session.getAttribute("emailSession") != null) {
                utenteProprietario[0] = user.getName();
                utenteProprietario[1] = user.getSurname();
                utenteProprietario[2] = user.getEmail();
                utenteProprietario[3] = user.getPassword();
                utenteProprietario[4] = user.getAvatar();
                if (!session.getAttribute("emailSession").equals(utenteProprietario[2])){
                    listaCorrente[7] = Boolean.toString(sharingDAO.getSharing(Integer.parseInt(listaCorrente[0]), (String)session.getAttribute("emailSession")).getModify());
                    listaCorrente[8] = Boolean.toString(sharingDAO.getSharing(Integer.parseInt(listaCorrente[0]), (String)session.getAttribute("emailSession")).getAdd());
                    listaCorrente[9] = Boolean.toString(sharingDAO.getSharing(Integer.parseInt(listaCorrente[0]), (String)session.getAttribute("emailSession")).getDelete());
                }
                
            } else {
                utenteProprietario[0] = "";
                utenteProprietario[1] = "";
                utenteProprietario[2] = "";
                utenteProprietario[3] = "";
                utenteProprietario[4] = "";
                
            }
            
            //mi salvo i prodotti
            List products = productDAO.getAllProducts((String)session.getAttribute("emailSession"));
            session.setAttribute("resultSearch", library.getSearchResults(products, productCategoryDAO));
            
            session.setAttribute("listaCondivisa", listaCondivisa);            
            session.setAttribute("utenteProprietario", utenteProprietario);            
            session.setAttribute("listaCorrente", listaCorrente);            
            session.setAttribute("listaAttiva", selectedList);            
            library.prodottiDellaLista(selectedList, request);
            request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
            
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Metodo POST non implementato
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
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
