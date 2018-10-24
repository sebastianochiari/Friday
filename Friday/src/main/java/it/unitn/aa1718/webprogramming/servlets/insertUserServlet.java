/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.servlets;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.dao.*;
import it.unitn.aa1718.webprogramming.dao.entities.*;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.friday.*;
import it.unitn.aa1718.webprogramming.extra.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author tommi
 */
public class insertUserServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        UserDAO riverDAO = mySqlFactory.getUserDAO();
        List users = null;
        UserDAO userDAO = new MySQLUserDAOImpl();
        
//        // cancellazione di user memorizzati sul DB
//        users = userDAO.getAllUsers();
//        for (Object u : users) {
//            userDAO.deleteUser((User) u);
//        }
        

        // creazione di user
        Library library = new Library();
      
        
        DBSecurity encrypt = new DBSecurity();
        //private Random RANDOM = new SecureRandom(); 
      
        String email = null;
        String password = null;
        String passwordcheck = null;
        String name = null;
        String surname = null;
        String avatar = null;
      
        
        
        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
    
        bufferedReader =  request.getReader() ; //new BufferedReader(new InputStreamReader(inputStream));
        char[] charBuffer = new char[128];
        int bytesRead;
        while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
            sb.append(charBuffer, 0, bytesRead);
        }
        
         if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                throw ex;
            }
         }
        String test = "%" + sb.toString() + "$"; //concateno \n\n come delimitatori, mi limito al penultimo, così non esco da |Stringa|

        System.out.println("REQUEST BODY DEL FORM = TEST è :" +test); 
        //Name1=remo&Surname1=zao&exampleInputEmail1=remozao@gmail.com&exampleInputPassword1=ciaone1&exampleInputPassword1=ciaone1$

        
        
        name = test.substring(test.indexOf("=") + 1, test.indexOf("&"));
        System.out.println("NAME ESTRATTO CORRETTAMENTE è " + name);
        
        
        String [] tok = test.split("&");
        for(int i=0; i<tok.length; i++){
            System.out.println("token VALE: " + tok[i] + "\n");
            
            //COMMENTATO PER TENTATIVO DI RITORNARE PASSWORD UGUALI per controllo
            //System.out.println("token VALE: " + tok[i] );
            tok[i] = tok[i] + "$";
        }
        
        surname = tok[1].substring(tok[1].indexOf("=") + 1, tok[1].indexOf("$"));
        email = tok[2].substring(tok[2].indexOf("=") + 1, tok[2].indexOf("$"));
        password = tok[3].substring(tok[3].indexOf("=") + 1, tok[3].indexOf("$"));
        passwordcheck = tok[4].substring(tok[4].indexOf("=") + 1, tok[4].indexOf("$"));
        
        System.out.println("email:" + email);
        System.out.println("surname:" + surname);
        System.out.println("psw: " + password);
        System.out.println("pswcheck: " + passwordcheck);
        
        //AVATAR ???????
        
        boolean isOkay = library.checkString(password);
        
        if(!isOkay){
             //evidenziare di rosso la laber password
             System.out.println("ERROR!!!!!!");
             
             
             String origin = null;
             Object servlet = (String)request.getRequestURI();
             request.setAttribute("origin", "insertUserServlet");
             request.getRequestDispatcher("insertUser.jsp").forward(request, response);
        } else{
       
        //String salt = encrypt.getSalt(10); 
        String pswEncrypted = encrypt.setSecurePassword(password, email);
      
        System.out.println("la password è stata criptata correttamente. SONO IN INSERTUSERSERVLET ");
        //User user1 = new User(email, pswEncrypted, name, surname, library.ImageControl(avatar), false, false); NAME CANNOT BE NULL
        User user1 = new User( email, pswEncrypted, name, surname , library.ImageControl(avatar), false, false);
  
        // memorizzazione del nuovo user nel DB
        userDAO.createUser(user1);
        
        // recupero di tutti gli user del DB
        users = userDAO.getAllUsers();
        response.sendRedirect("index.html");
        
       
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        System.out.println("SONO IN GEEEEEEEEEEEEEEEETTTTTTTTT");
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
