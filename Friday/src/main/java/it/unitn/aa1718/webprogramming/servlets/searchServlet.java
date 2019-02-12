/*
 * WebProgramming Project - Shopping List
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.ProductCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ProductDAO;
import it.unitn.aa1718.webprogramming.dao.SharingProductDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingProductDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class searchServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
     * Metodo GET della servlet che si occupa della ricerca dei prodotti in base all'input dell'utente
     * Se fallisce redireziona ad una pagina di errore predefinita.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //cose che servono
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        ProductDAO ProductriverDAO = mySqlFactory.getProductDAO();
        ProductDAO productDAO = new MySQLProductDAOImpl();
        ProductDAO ProductCategoryriverDAO = mySqlFactory.getProductDAO();
        ProductCategoryDAO productCategoryDAO = new MySQLProductCategoryDAOImpl();
        SharingProductDAO sharingProductriverDAO = mySqlFactory.getSharingProductDAO();
        SharingProductDAO sharingProductDAO = new MySQLSharingProductDAOImpl();
        HttpSession session = request.getSession();
        Library library = new Library();
        library.createProductCategory(request);

        //recupero inputs
        String input = null;
        String inputClick = null;
        String ordine = null;
        int PCID = -1;

        //System.out.println("PCID: " + request.getParameter("inputCategory"));
        //System.out.println("inputClick: " + request.getParameter("inputClick"));
        //System.out.println("inputSearch: " + request.getParameter("inputSearch"));
        //System.out.println("CategoryLeft: " + request.getParameter("CategoryLeft"));
        //System.out.println("order: " + request.getParameter("order"));

        if(request.getParameter("inputCategory") != null){
            PCID = Integer.parseInt(request.getParameter("inputCategory"));
            session.setAttribute("inputClick", null);
            session.setAttribute("PCID", PCID);
        }

        if(request.getParameter("inputClick") != null || request.getParameter("CategoryLeft") != null){

            if(request.getParameter("inputClick") != null) {
                inputClick = request.getParameter("inputClick");
            } else {
                inputClick = request.getParameter("CategoryLeft");
            }

            session.setAttribute("inputSearch", null);
            session.setAttribute("PCID", -1);
            session.setAttribute("inputClick", inputClick);

        }

        if(request.getParameter("inputSearch") != null){
            input = request.getParameter("inputSearch");


            if(input.length()< 200){

                session.setAttribute("inputClick", null);
                session.setAttribute("inputSearch", input);

            } else {

                 response.sendRedirect("error.jsp");
                 return;
            }
        }

        if(request.getParameter("order") != null && request.getParameter("order").equals("categoria")){
            ordine = request.getParameter("order");
        }

        //in caso di aggiornamento della pagina ripristino la ricerca precedente
        if(input == null && PCID < 0){
            input = (String)session.getAttribute("inputSearch");
            PCID = (int)session.getAttribute("PCID");
        }
        if(inputClick == null){
            inputClick = (String)session.getAttribute("inputClick");
        }

        //controllo input
        //System.out.println("PCID: " + PCID);
        //System.out.println("inputClick: " + inputClick);
        //System.out.println("inputSearch: " + input);
        //System.out.println("order: " + ordine);

        List<Product> products = null;
        String [] temp = new String [2];

        //se selezionato inputClick significa che non sei passato per il cerca, ma per le categorie in alto
        if(inputClick != null){

            int inputClickPCID = Integer.parseInt(inputClick);

            //calcolo risultato
            products = productDAO.getProductsByPCID(inputClickPCID, (String)session.getAttribute("emailSession"));

            temp[0] = "alfabeto";
            temp[1] = "categoria";

            //salvo risultato
            session.setAttribute("resultSearch", library.getSearchResults(products, productCategoryDAO));
            session.setAttribute("ordinamento", temp);

        }
        //se hai cercato tramite la sezione cerca, o se hai richiesto un diverso ordinamento
        else if(input != null || ordine != null){

            if(PCID > 0){
                products = productDAO.getProductsByNameAndPCID(PCID, input, (String)session.getAttribute("emailSession"));
                temp[0] = "alfabeto";
                temp[1] = "categoria";
            } else if (ordine != null && ordine.equals("categoria")){
                products = productDAO.getProductsByName(input, true, (String)session.getAttribute("emailSession"));
                temp[0] = "categoria";
                temp[1] = "alfabeto";
            } else {
                products = productDAO.getProductsByName(input, false, (String)session.getAttribute("emailSession"));
                temp[0] = "alfabeto";
                temp[1] = "categoria";
            }
            
            //salvo risultati
            session.setAttribute("resultSearch", library.getSearchResults(products, productCategoryDAO));
            session.setAttribute("ordinamento", temp);

        }

        if (products.size() > 0){
            session.setAttribute("elementi", library.getSearchResults(products, productCategoryDAO).length);
        }

        //ridireziono alla pagina di ricerca
        response.sendRedirect("search.jsp");

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
