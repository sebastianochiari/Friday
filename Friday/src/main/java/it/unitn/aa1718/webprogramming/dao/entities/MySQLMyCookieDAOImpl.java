/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */

package it.unitn.aa1718.webprogramming.dao.entities;

import it.unitn.aa1718.webprogramming.connection.MySQLDAOFactory;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.friday.MyCookie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe DAO che permette la gestione dei cookie 
 */
public class MySQLMyCookieDAOImpl implements MyCookieDAO{
    
    private static final String Create_Query = "INSERT INTO cookies (cookieID, LID, email, deadline) VALUES (?, null, ?, ?)";
    
    private static final String Read_Query_By_Email = "SELECT cookieID, LID, email, deadline FROM cookies WHERE email = ?";
    
    private static final String Read_Query_By_CookieID = "SELECT cookieID, LID, email, deadline FROM cookies WHERE cookieID = ?";
    
    private static final String Read_All_Query = "SELECT cookieID, LID, email, deadline FROM cookies";
    
    private static final String Update_Query = "UPDATE cookies SET cookieID = ?, LID=?, email=?, deadline=? WHERE email = ?";
    
    private static final String Update_LID_Query = "UPDATE cookies SET LID = ? WHERE cookieID = ?";
    
    private static final String Update_Email_Query = "UPDATE cookies SET email = ? WHERE cookieID = ?";
    
    private static final String Delete_Query_By_Email = "DELETE FROM cookies WHERE email = ?";
    
    private static final String Delete_Query_By_CookieID = "DELETE FROM cookies WHERE cookieID = ?";
    
    private static final String Delete_Query_DB_Expired_Cookies = "DELETE FROM cookies WHERE deadline < ?";

    /**
     * Metodo che ritorna tutti i cookie in base all'email
     * @param email stringa che identifica l'utente in modo univoco
     * @return vettore di cookie
     */
    @Override
    public Vector<MyCookie> getAllCookieByEmail(String email){
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Vector<MyCookie> myCookies = new Vector();
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Query_By_Email);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while(result.next()){
                if(result.getString("cookieID") != null){
                    MyCookie myCookie = new MyCookie(result.getInt(1), result.getInt(2), result.getString(3), result.getLong(4));
                    myCookies.add(myCookie);
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
        return myCookies;
    }
    
    /**
     * Metodo che ritorna un vettore di tutti i cookie
     * @return vettore con una lista di cookie
     */
    public Vector<MyCookie> getAllCookie(){
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Vector<MyCookie> myCookies = new Vector();
        
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_All_Query);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while(result.next()){
                if(result.getString("cookieID") != null){
                    MyCookie myCookie = new MyCookie(result.getInt(1), result.getInt(2), result.getString(3), result.getLong(4));
                    myCookies.add(myCookie);
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
        return myCookies;
    
    }

    /**
     * Metodo che ritorna IL cookie specifico
     * @param myCookies
     * @param myCookieID
     * @return 
     */
    @Override
    public MyCookie getTHECookie(Vector<MyCookie> myCookies, int myCookieID){
        
        for(int i=0; i<myCookies.size(); i++){
            if(myCookieID == (myCookies.get(i)).getCookieID())
                return myCookies.get(i);
        }
        return null;
        
    }

    /**
     * Metodo che ritorna il cookie associato all'email 
     * @param request
     * @param email stringa che identifica l'utente
     * @return oggetto di tipo MyCookie che rappresenta il cookie associato
     */
    @Override
    public MyCookie getCookie(HttpServletRequest request, String email){
        
        Cookie[] cookies = request.getCookies();
        Vector<MyCookie> myCookies = getAllCookieByEmail(email);
        MyCookie myCookie = null;
        int lengthArrayCookies = 0;
        
        if(cookies != null){
            while (lengthArrayCookies < cookies.length && myCookie == null) {
                Cookie tmpcookie = cookies[lengthArrayCookies];
                if(tmpcookie.getName().equals("FridayLogin")){
                     myCookie = getTHECookie(myCookies, Integer.parseInt(tmpcookie.getValue()));
                }
                lengthArrayCookies++;
            }
        }
        
        return myCookie;
    }

    /**
     * Metodo che permette la creazione di un cookie 
     * @param myCookie istanza di cookie 
     */
    @Override
    public void createCookie(MyCookie myCookie){
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Create_Query);
            preparedStatement.setInt(1, myCookie.getCookieID());
            preparedStatement.setString(2, myCookie.getEmail());
            preparedStatement.setLong(3, myCookie.getDeadline());
            preparedStatement.execute();                   
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    /**
     * Metodo che permette la modifica di un cookie associato all'utente 
     * @param myCookie  istanza del cookie da modificare
     */
    @Override
    public void updateCookie(MyCookie myCookie){
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Query);
            preparedStatement.setInt(1, myCookie.getCookieID());
            preparedStatement.setInt(2, myCookie.getLID());
            preparedStatement.setString(3, myCookie.getEmail());
            preparedStatement.setLong(4, myCookie.getDeadline());
            preparedStatement.setInt(5, myCookie.getCookieID());
            preparedStatement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    }

    /**
     * Metodo che elimina il cookie in base all'ID del cookie stesso
     * @param myCookieID intero che rappresenta il cookie 
     */
    @Override
    public void deleteCookieByCookieID(int myCookieID){
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query_By_CookieID);
            preparedStatement.setInt(1, myCookieID);
            preparedStatement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    }
    
    /**
     * Metodo che permette l'eliminazione del cookie in base all'email
     * @param email stringa che identifica l'utente in modo univoco
     */
    @Override
    public void deleteCookieByEmail(String email){
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query_By_Email);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    
    }

    /**
     * Metodo che permette l'eliminazione del cookie se non è più valido
     */
    @Override
    public void deleteDBExpiredCookies(){
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Delete_Query_DB_Expired_Cookies);
            preparedStatement.setLong(1, timestamp.getTime());
            preparedStatement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    }

    /**
     * Metodo che permette la modifica di un cookie in base all'ID della lista
     * @param cookieID intero che rappresenta in modo univoco il cookie
     * @param LID intero che rappresenta l'ID della lista
     */
    @Override
    public void updateLIDCookie(int cookieID, int LID) {
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_LID_Query);
            preparedStatement.setInt(1, LID);
            preparedStatement.setInt(2, cookieID);
            preparedStatement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
    }

    /**
     * Metodo che permette la modifica di un cookie
     * @param cookieID intero che identifica il cookie
     * @param email stringa che rappresenta l'email alla quale il cookie è associato
     */
    @Override
    public void updateEmailCookie(int cookieID, String email) {
        
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = MySQLDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(Update_Email_Query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, cookieID);
            preparedStatement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        
    }

    /**
     * Metodo che ritorna l'ID della lista in base al cookie
     * @param cookieID intero rappresentante il cookie
     * @return intero che identifica l'ID della lista
     */
    @Override
    public int getLIDbyCookieID(int cookieID) {
        
        int LID = -1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(Read_Query_By_CookieID);
            preparedStatement.setInt(1, cookieID);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            while (result.next()) {
                LID = result.getInt(2);
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
        
        return LID;
        
    }
    
}
