/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.extra;

import com.google.gson.Gson;
import it.unitn.aa1718.webprogramming.friday.Message;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Classe che si occupa della gestione della messaggistica
 */
public class MessageDecoder implements Decoder.Text<Message>{

    private static Gson gson = new Gson();
    
    /**
     * Metodo che converte il messaggio da JSON in un Java Object per permetterne la gestione 
     * @param s stringa contenente il json
     * @return il java object 
     * @throws DecodeException 
     */
    @Override
    public Message decode(String s) throws DecodeException {
        return gson.fromJson(s, it.unitn.aa1718.webprogramming.friday.Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
