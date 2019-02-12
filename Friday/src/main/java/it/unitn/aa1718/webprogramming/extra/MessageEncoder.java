/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.extra;

import com.google.gson.Gson;
import it.unitn.aa1718.webprogramming.friday.Message;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Classe che si occupa della gestione della messaggistica
 */
public class MessageEncoder implements Encoder.Text<Message>{
    
    Gson gson = new Gson();
    
    /**
     * Metodo che converte il messaggio da un Java Object in JSON per permetterne l'invio 
     * @param message messaggio da convertire
     * @return il messaggio in formato JSON
     * @throws EncodeException 
     */
    @Override
    public String encode(Message message) throws EncodeException {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
