/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.extra;

import com.google.gson.Gson;
import it.unitn.aa1718.webprogramming.friday.Message;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author leo97
 */
public class MessageDecoder implements Decoder.Text<Message>{

    private static Gson gson = new Gson();
    
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
