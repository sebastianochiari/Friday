/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.MessageDAO;
import it.unitn.aa1718.webprogramming.dao.ProductDAO;
import it.unitn.aa1718.webprogramming.dao.ProductListDAO;
import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.dao.SharingProductDAO;
import it.unitn.aa1718.webprogramming.dao.ShoppingListDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMessageDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.extra.Library;
import it.unitn.aa1718.webprogramming.friday.Message;
import it.unitn.aa1718.webprogramming.friday.ProductList;
import it.unitn.aa1718.webprogramming.friday.Sharing;
import it.unitn.aa1718.webprogramming.friday.SharingProduct;
import it.unitn.aa1718.webprogramming.friday.User;
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
public class sharingListServlet extends HttpServlet {

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
            out.println("<title>Servlet sharingListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sharingListServlet at " + request.getContextPath() + "</h1>");
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
        
        int azioneLista = Integer.parseInt(request.getParameter("azioneLista"));
        int listaScelta = 0;
        SharingDAO sharingDAO = new MySQLSharingDAOImpl();
        ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
        ProductListDAO productListDAO = new MySQLProductListDAOImpl();
        SharingProductDAO sharingProductDAO = new MySQLSharingProductDAOImpl();
        ProductDAO productDAO = new MySQLProductDAOImpl();
        List productList = null;
        Library library = new Library();
        HttpSession session = request.getSession();
        
        switch (azioneLista) {
            case 2: 
                listaScelta = Integer.parseInt(request.getParameter("listToShare"));
                String email = request.getParameter("invitationEmail");
                sharingDAO.createSharing(new Sharing(email, listaScelta, true, true, false));
                productList = productListDAO.getPIDsByLID(listaScelta);
                for (int i=0; i<productList.size(); i++){
                    if ((productDAO.getProduct(((ProductList)productList.get(i)).getPID())).getEmail().equals((String)session.getAttribute("emailSession"))){
                        sharingProductDAO.createSharingProduct(new SharingProduct(email, ((ProductList)productList.get(i)).getPID()));
                    }
                }
                request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
                break;
            case 3:
                // questa parte va decisamente rivista, ora come ora dovrebbe essere la chat
         
                //inizializzo i DAO e sessione
                DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
                SharingDAO sharingDAO1 = new MySQLSharingDAOImpl();
                MessageDAO messageDAO = new MySQLMessageDAOImpl();
                UserDAO userDAO = new MySQLUserDAOImpl();
                int listaSelezionata = Integer.parseInt(request.getParameter("messageToList"));

                //aggiungo messaggi
                if(request.getParameter("newMessage") != null){

                    Message newMessage = new Message(library.LastEntryTable("messageID", "messages"), (int)session.getAttribute("selectedList"), (String)session.getAttribute("emailSession"), request.getParameter("newMessage"));
                    messageDAO.createMessage(newMessage);

                }

                //ottengo valori
                System.out.println(listaSelezionata);
                int LID = listaSelezionata;
                List partecipanti = sharingDAO.getAllEmailsbyList(LID);
                List messaggi = messageDAO.getMessagesByLID(LID);

                //salvo i partecipanti in modo da poterli passare alla jsp
                String[][] PartecipantiResult = new String[partecipanti.size()][3];

                for(int i=0; i<partecipanti.size(); i++){

                    User tmp = userDAO.getUser(((Sharing)partecipanti.get(i)).getEmail());

                    PartecipantiResult[i][0] = tmp.getAvatar();
                    PartecipantiResult[i][1] = tmp.getName();
                    PartecipantiResult[i][2] = tmp.getSurname();
                    System.out.println(PartecipantiResult[i][0]+" "+PartecipantiResult[i][1]+" "+PartecipantiResult[i][2]);
                }

                //salvo i messaggi in modo da poterli passare alla jsp
                String[][] MessaggiResult = new String[messaggi.size()][4];

                for(int i=0; i<messaggi.size(); i++){

                    Message tmp = (Message)messaggi.get(i);

                    MessaggiResult[i][0] = (userDAO.getUser(tmp.getSender())).getName();
                    MessaggiResult[i][1] = (userDAO.getUser(tmp.getSender())).getSurname();
                    MessaggiResult[i][2] = tmp.getText();
                    MessaggiResult[i][3] = (userDAO.getUser(tmp.getSender())).getEmail();
                    System.out.println(MessaggiResult[i][0]+" "+MessaggiResult[i][1]+" "+MessaggiResult[i][2]+" "+MessaggiResult[i][3]);

                }

                session.setAttribute("partecipantiChat", PartecipantiResult);
                session.setAttribute("messaggiChat", MessaggiResult);
                session.setAttribute("selectedList", listaSelezionata);
                request.getRequestDispatcher("chat.jsp").forward(request, response);

             
                break;
            case 4:
                listaScelta = Integer.parseInt(request.getParameter("listToEliminate"));
                shoppingListDAO.deleteShoppingList(listaScelta);
                request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
                break;
            case 5:
                listaScelta = Integer.parseInt(request.getParameter("notToShareList"));
                String emailSession = request.getParameter("emailUtenteLoggato");
                sharingDAO.deleteSharing(new Sharing(emailSession, listaScelta, sharingDAO.getSharing(listaScelta, emailSession).getModify(), sharingDAO.getSharing(listaScelta, emailSession).getAdd(), sharingDAO.getSharing(listaScelta, emailSession).getDelete()));
                request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
                break;
            default: 
                response.sendRedirect("error.jsp"); 
                break;
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
