/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.MyCookie;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * Interfaccia che permette la gestione e implementazione dei DAO per i cookie
 */
public interface MyCookieDAO {
    
    /**
     * Metodo che ritorna tutti i cookie in base all'email
     * @param email stringa che identifica l'utente in modo univoco
     * @return vettore di cookie
     */
    public Vector<MyCookie> getAllCookieByEmail(String email);
    
    public Vector<MyCookie> getAllCookie();
    
    /**
     * Metodo che ritorna IL cookie ! ???????????
     * @param myCookies
     * @param myCookieID
     * @return 
     */
    public MyCookie getTHECookie(Vector<MyCookie> myCookies, int myCookieID);
    
    /**
     * Metodo che ritorna il cookie associato all'email 
     * @param request
     * @param email stringa che identifica l'utente
     * @return oggetto di tipo MyCookie che rappresenta il cookie associato
     */
    public MyCookie getCookie(HttpServletRequest request, String email);
    
    /**
     * Metodo che ritorna l'ID della lista in base al cookie
     * @param cookieID intero rappresentante il cookie
     * @return intero che identifica l'ID della lista
     */
    public int getLIDbyCookieID(int cookieID);
    
    /**
     * Metodo che permette la creazione di un cookie ???
     * @param myCookie 
     */
    public void createCookie(MyCookie myCookie);
    
    /**
     * Metodo che permette la modifica di un cookie associato all'utente ?? 
     * @param myCookie  ??????
     */
    public void updateCookie(MyCookie myCookie);
    
    /**
     * Metodo che elimina il cookie in base all'ID del cookie stesso
     * @param myCookieID intero che rappresenta il cookie 
     */
    public void deleteCookieByCookieID(int myCookieID);
    
    /**
     * Metodo che permette l'eliminazione del cookie in base all'email
     * @param email stringa che identifica l'utente in modo univoco
     */
    public void deleteCookieByEmail(String email);
    
    /**
     * Metodo che permette l'eliminazione del cookie se non è più valido
     */
    public Vector<MyCookie> deleteDBExpiredCookies();
    
    /**
     * Metodo che permette la modifica di un cookie in base all'ID della lista
     * @param cookieID intero che rappresenta in modo univoco il cookie
     * @param LID intero che rappresenta l'ID della lista
     */
    public void updateLIDCookie(int cookieID, int LID);
    
    /**
     * Metodo che permette la modifica di un cookie
     * @param cookieID intero che identifica il cookie
     * @param email stringa che rappresenta l'email alla quale il cookie è associato
     */
    public void updateEmailCookie(int cookieID, String email);
    
}
