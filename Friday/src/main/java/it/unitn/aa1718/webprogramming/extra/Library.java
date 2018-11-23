/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.extra;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMyCookieDAOImpl;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.friday.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tommi
 */
public class Library {
    
    // metodo calcolo del PID dell'ultima entry della tabella prodotti
    public int LastEntryTable(String col, String table) {
        
        int tmp = 1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String command = null;
        String colTmp = "max"+col;
        try {
            command = "select max("+col+") as "+colTmp+" from "+table;
            
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.execute();
            result = preparedStatement.getResultSet(); 
            
            result.next();
            if(result.getString(colTmp) != null){
                tmp = Integer.parseInt(result.getString(colTmp))+1;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return tmp;
    }
    
    //controllo della presenza dell'immagine all'inserimento di liste, prodotti ecc
    public String ImageControl(String image) {
        
        String tmp = null;
        
        if (image != null && !image.isEmpty()){
                tmp = image;
        }
        
        return tmp;
    }
    
    public static void sendMail (String email, String name, String surname) throws AddressException,MessagingException {
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("donotreplyfriday@gmail.com","progettoweb2018");
                        }
                });

        try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("donotreplyfriday@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(email));
                message.setSubject("Benvenuto in Friday!");
                message.setText(name+" "+surname+", Benvenuto in Friday!"+
                                "\n\n link di conferma : http://localhost:8080/Friday/confirmRegistrationServlet?email="+email);

                Transport.send(message);

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
    
    public void changePassword (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String email, String name, String surname, String avatar, boolean admin, boolean list_owner, boolean confirmed) throws ServletException, IOException {
        
        String previousPassword = request.getParameter("previousPassword");
        String inputNewPassword = request.getParameter("inputNewPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String typeError = request.getParameter("typeError");
        String changePassword = request.getParameter("changePassword");
        
        String inputNewPasswordEncrypted = encrypt.setSecurePassword(inputNewPassword, email);
        String previousPasswordEncrypted = encrypt.setSecurePassword(previousPassword, email);
        
        boolean isOkay = encrypt.checkString(inputNewPassword);
        
        System.out.println(userDAO.getPasswordByUserEmail(email));
        System.out.println(previousPasswordEncrypted);
        
        String errorPresentPassword = "errorPresentPassword";
        request.setAttribute("errorPresentPassword", errorPresentPassword);
        
        if (!userDAO.getPasswordByUserEmail(email).equals(previousPasswordEncrypted)) {
            
            String error = "errorPreviousPassword";
            typeError = error;
            request.setAttribute("errorPreviousPassword", typeError);                
            request.getRequestDispatcher(changePassword).forward(request, response);
            
        } else if (!isOkay) {   

            String error = "errorInputPassword";
            typeError = error;
            request.setAttribute("errorInputPassword", typeError);                
            request.getRequestDispatcher(changePassword).forward(request, response);

        } else if (!inputNewPassword.equals(confirmPassword)) {       

            String error = "errorConfirmPassword";
            typeError = error;
            request.setAttribute("errorConfirmPassword", typeError);
            
            System.out.println( "-----Le password non coicidono");
            request.getRequestDispatcher(changePassword).forward(request, response);

        } else {
            
            request.setAttribute("errorPresentPassword", null);
            
            User user1 = new User(email, inputNewPasswordEncrypted, name, surname, library.ImageControl(avatar), admin, list_owner, true);
            userDAO.updateUserByEmail(user1);
            
            System.out.println("ho cambiato la password correttamente");
            
            response.sendRedirect("myaccount.jsp");
            
            
        }
    }
    
    public void changeEmail (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String dbpassword, String name, String surname, String avatar, boolean admin, boolean list_owner, boolean confirmed) throws ServletException, IOException {
        
        String oldEmail = request.getParameter("oldEmail");
        String inputNewEmail = request.getParameter("inputNewEmail");
        String confirmEmail = request.getParameter("confirmEmail");
        String typeError = request.getParameter("typeError");
        String changeEmail = request.getParameter("changeEmail");
        String password = request.getParameter("password");
        dbpassword = userDAO.getPasswordByUserEmail(oldEmail);
        request.setAttribute("inputEmail", inputNewEmail);
        MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();
        
        String errorPresentEmail = "errorPresentEmail";        
        request.setAttribute("errorPresentEmail", errorPresentEmail);
        String errorInputEmailFormat = "noErrorInputEmailFormat";        
        request.setAttribute("errorInputEmailFormat", errorInputEmailFormat);
        String errorInputEmail = "errorInputEmail";
        request.setAttribute("errorInputEmail", null);
        
        if (!userDAO.checkUser(oldEmail)) {
            
            System.out.println("questa email non esiste nel database");
            String error = "errorOldEmail";
            typeError = error;
            request.setAttribute("errorOldEmail", typeError);
            request.getRequestDispatcher(changeEmail).forward(request, response);
            
        } else if(!userDAO.checkEmail(inputNewEmail)) {
            
            System.out.println("IL FORMATO DI QUESTA EMAIL NON è CORRETTO ");
            errorInputEmailFormat = "errorInputEmailFormat";
            typeError = errorInputEmailFormat;
            request.setAttribute("errorInputEmail", errorInputEmail);
            request.setAttribute("errorInputEmailFormat", typeError);
            request.getRequestDispatcher(changeEmail).forward(request, response);
            
        } else if (userDAO.checkUser(inputNewEmail)) {
            
            request.setAttribute("errorInputEmail", errorInputEmail);
            request.setAttribute("inputEmail", inputNewEmail);
            
            request.getRequestDispatcher(changeEmail).forward(request, response);
            
        } else if (!inputNewEmail.equals(confirmEmail)) {   
            
            String error = "errorConfirmEmail";
            typeError = error;
            request.setAttribute("errorConfirmEmail", typeError);
            
            System.out.println( "-----Le email non coicidono");
            request.getRequestDispatcher(changeEmail).forward(request, response);

        } else {
            
            String pswencrypted = encrypt.setSecurePassword(password, oldEmail);
            
            if (pswencrypted.equals(dbpassword)) {
                
                
                request.setAttribute("errorPresentEmail", null);
                
                String newpswencrypted = encrypt.setSecurePassword(password, inputNewEmail);

                (request.getSession()).setAttribute("emailSession", inputNewEmail);

                User userPassword = new User(inputNewEmail, dbpassword, name, surname, library.ImageControl(avatar), admin, list_owner, true);
                userDAO.updateUserByPassword(userPassword);
                User userEmail = new User(inputNewEmail, newpswencrypted, name, surname, library.ImageControl(avatar), admin, list_owner, true);
                userDAO.updateUserByEmail(userEmail);

                //bisogna sistemare anche la tabella cookie e quindi forse aggiornarla?

                System.out.println("ho cambiato la email correttamente");
                
                response.sendRedirect("myaccount.jsp");
            
            } else {
            
                System.out.println("PASSWORD DIVERSE !!!!!!!!!!");
                System.out.println("la password non corrisponde all'email inserita ");
                String error = "errorPassword";
                typeError = error;
                request.setAttribute("errorPassword", typeError);
                request.getRequestDispatcher(changeEmail).forward(request, response);
            
            }
            
        }
        
    }
        
    public void changePersonal (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String email, String dbpassword, String name, String surname, String avatar, boolean admin, boolean list_owner, boolean confirmed) throws ServletException, IOException {
        
        String newName = request.getParameter("newName");
        String newSurname = request.getParameter("newSurname");
        String newAvatar = request.getParameter("newAvatar");
        String typeError = request.getParameter("typeError");
        String changePersonal = request.getParameter("changePersonal");
        
        String errorPresentPersonal = "errorPresentPersonal";
        request.setAttribute("errorPresentPersonal", errorPresentPersonal);
        
        if (newName.isEmpty()) {
            String error = "nameError";
            typeError = error;
            request.setAttribute("nameError", typeError);
            request.getRequestDispatcher(changePersonal).forward(request, response);
        } else if (newSurname.isEmpty()) {
            String error = "surnameError";
            typeError = error;
            request.setAttribute("surnameError", typeError);
            request.getRequestDispatcher(changePersonal).forward(request, response);
        } else {
            // se avatar è null che si fa? bisogna per forza sceglierne uno oppure si può anche lasciarlo vuoto?
            (request.getSession()).setAttribute("nameUserSession", newName);
            (request.getSession()).setAttribute("surnameUserSession", newSurname);
            (request.getSession()).setAttribute("avatarUserSession", newAvatar);
            request.setAttribute("errorPresentPersonal", null);
            
            User user1 = new User(email, dbpassword, newName, newSurname, library.ImageControl(newAvatar), admin, list_owner, true);
            userDAO.updateUserByEmail(user1);
            
            System.out.println("name e surname e avatar aggiornati.");
            
            response.sendRedirect("myaccount.jsp");
        }
        
    }
    
    public void changeAdmin (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String email, String dbpassword, String name, String surname, String avatar, boolean list_owner, boolean confirmed) throws ServletException, IOException {
        
        boolean admin = true;
        
        request.getSession().setAttribute("adminUserSession", admin);
        
        User user1 = new User(email, dbpassword, name, surname, library.ImageControl(avatar), admin, list_owner, confirmed);
        userDAO.updateUserByEmail(user1);

        System.out.println("utente è ora amministratore");

        response.sendRedirect("adminSection.jsp");
        
    }
    
}
