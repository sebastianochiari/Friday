/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.extra;

import it.unitn.aa1718.webprogramming.connection.*;
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
                                "\n\n cazzo ridi ti sto hackerando");

                Transport.send(message);

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
        
}
