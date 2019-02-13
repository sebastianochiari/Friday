/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.extra;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author leo97
 */
@ServerEndpoint("/geolocalizzazione")
public class GeoEndpoint {
    
    String appID = "";
    String appCODE = "";
    double distanzamassima = 700;

    @OnMessage
    public String onMessage(String message) {
        return null;
        
        
        
        
        
    }
    
    

    
}
