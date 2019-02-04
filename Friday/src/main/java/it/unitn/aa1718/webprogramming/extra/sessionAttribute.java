/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */

package it.unitn.aa1718.webprogramming.extra;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;

/**
 * Classe che permette la gestione degli attributi di sessione
 */
public class sessionAttribute {
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void indexSessionAttribute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        (request.getSession()).setAttribute("emailSession", null);
        (request.getSession()).setAttribute("cookieIDSession", null);
        (request.getSession()).setAttribute("nameUserSession", null);

        String DBUrl = MySQLDAOFactory.getDBUrl();
        String DBUser = MySQLDAOFactory.getDBUser();
        String DBPass = MySQLDAOFactory.getDBPass();

        (request.getSession()).setAttribute("DBUrlSession", DBUrl);
        (request.getSession()).setAttribute("DBUserSession", DBUser);
        (request.getSession()).setAttribute("DBPassSession", DBPass);

        Cookie[] cookies = request.getCookies();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        boolean boolEmailSession = false;

        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM cookies;");
            preparedStatement.execute();
            result = preparedStatement.getResultSet();

            if(cookies != null){

                while (result.next()) {

                    for(int i=0; i<cookies.length; i++){

                        System.out.println("browser cookie = "+cookies[i].getValue()+"  db cookie = "+result.getString("cookieID"));
                        if((cookies[i].getValue()).equals(result.getString("cookieID"))){

                            String emailSession = result.getString("Email");
                            (request.getSession()).setAttribute("emailSession", emailSession);
                            (request.getSession()).setAttribute("cookieIDSession", result.getString("cookieID"));
                            (request.getSession()).setAttribute("deadlineSession", result.getString("Deadline"));
                            (request.getSession()).setAttribute("LIDSession", result.getString("LID"));
                            System.out.println("zao sono dentro l'if e usersession = "+(String)(request.getSession()).getAttribute("emailSession")+" cookieID = "+(String)(request.getSession()).getAttribute("cookieIDSession"));

//                            if (emailSession.equals(null)){
//                                boolEmailSession = false;
//                            } else {
                                boolEmailSession = true;
//                            }

                            (request.getSession()).setAttribute("boolEmailSessionScriptlet", boolEmailSession);

                        }
                    }
                }

                preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE Email = ?;");
                preparedStatement.setString(1, (String)(request.getSession()).getAttribute("emailSession"));
                preparedStatement.execute();
                result = preparedStatement.getResultSet();

                while (result.next()) {
                    (request.getSession()).setAttribute("nameUserSession", result.getString("Name"));
                    (request.getSession()).setAttribute("surnameUserSession", result.getString("Surname"));
                    (request.getSession()).setAttribute("avatarUserSession", result.getString("Avatar"));
                    (request.getSession()).setAttribute("adminUserSession", result.getBoolean("Admin"));
                    (request.getSession()).setAttribute("list_OwnerUserSession", result.getBoolean("List_Owner"));
                    (request.getSession()).setAttribute("confirmedUserSession", result.getBoolean("Confirmed"));
                }

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
            
        
    }
    
}
