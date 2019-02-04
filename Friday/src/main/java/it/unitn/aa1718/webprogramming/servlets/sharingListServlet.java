/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.dao.ShoppingListDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListDAOImpl;
import it.unitn.aa1718.webprogramming.friday.Sharing;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        switch (azioneLista) {
            case 2: 
                listaScelta = Integer.parseInt(request.getParameter("listToShare"));
                String email = request.getParameter("invitationEmail");
                sharingDAO.createSharing(new Sharing(email, listaScelta, shoppingListDAO.getShoppingList(listaScelta).getName(), true, true, false));
                request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
                break;
            case 4:
                listaScelta = Integer.parseInt(request.getParameter("listToEliminate"));
                shoppingListDAO.deleteShoppingList(listaScelta);
                request.getRequestDispatcher("gestioneListe.jsp").forward(request, response);
                break;
            case 5:
                listaScelta = Integer.parseInt(request.getParameter("notToShareList"));
                String emailSession = request.getParameter("emailUtenteLoggato");
                sharingDAO.deleteSharing(new Sharing(emailSession, listaScelta, shoppingListDAO.getShoppingList(listaScelta).getName(), sharingDAO.getSharing(listaScelta, emailSession).getModify(), sharingDAO.getSharing(listaScelta, emailSession).getAdd(), sharingDAO.getSharing(listaScelta, emailSession).getDelete()));
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
