/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.dao;

import it.unitn.aa1718.webprogramming.friday.MyCookie;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author leo97
 */
public interface MyCookieDAO {
    
    public Vector<MyCookie> getAllCookieByEmail(String email);
    
    public Vector<MyCookie> getAllCookie();
    
    public MyCookie getTHECookie(Vector<MyCookie> myCookies, int myCookieID);
    
    public MyCookie getCookie(HttpServletRequest request, String email);
    
    public int getLIDbyCookieID(int cookieID);
    
    public void createCookie(MyCookie myCookie);
    
    public void updateCookie(MyCookie myCookie);
    
    public void deleteCookieByCookieID(int myCookieID);
    
    public void deleteCookieByEmail(String email);
    
    public void deleteDBExpiredCookies();
    
    public void updateLIDCookie(int cookieID, int LID);
    
    public void updateEmailCookie(int cookieID, String email);
    
}
