/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.MessageDAO;
import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMessageDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
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

/**
 *
 * @author leo97
 */
public class listServlet extends HttpServlet {

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
            out.println("<title>Servlet listServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listServlet at " + request.getContextPath() + "</h1>");
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
        
        //inizializzo i DAO e sessione
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        HttpSession session = request.getSession();
        SharingDAO sharingDAO = new MySQLSharingDAOImpl();
        MessageDAO messageDAO = new MySQLMessageDAOImpl();
        UserDAO userDAO = new MySQLUserDAOImpl();
        
        //ottengo valori
        int LID = Integer.parseInt(request.getParameter("selectedList"));
        List partecipanti = sharingDAO.getAllEmailsbyList(LID);
        List messaggi = messageDAO.getMessagesByLID(LID);
        
        //salvo i partecipanti in modo da poterli passare alla jsp
        String[][] PartecipantiResult = new String[partecipanti.size()][3];
        
        for(int i=0; i<partecipanti.size(); i++){
            
            User tmp = userDAO.getUser((String)partecipanti.get(i));

            PartecipantiResult[i][0] = tmp.getAvatar();
            PartecipantiResult[i][1] = tmp.getName();
            PartecipantiResult[i][2] = tmp.getSurname();

        }
        
        //salvo i messaggi in modo da poterli passare alla jsp
        String[][] MessaggiResult = new String[messaggi.size()][6];
        
        for(int i=0; i<messaggi.size(); i++){
            
            Message tmp = (Message)messaggi.get(i);

            MessaggiResult[i][0] = (userDAO.getUser(tmp.getSender())).getName();
            MessaggiResult[i][1] = (userDAO.getUser(tmp.getSender())).getSurname();
            MessaggiResult[i][2] = tmp.getText();

        }
        
        session.setAttribute("partecipantiChat", PartecipantiResult);
        session.setAttribute("messaggiChat", MessaggiResult);
        request.getRequestDispatcher("list.jsp");
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
