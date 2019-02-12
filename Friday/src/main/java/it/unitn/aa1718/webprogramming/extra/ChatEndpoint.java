/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.extra;

import it.unitn.aa1718.webprogramming.connection.DAOFactory;
import it.unitn.aa1718.webprogramming.dao.MessageDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMessageDAOImpl;
import it.unitn.aa1718.webprogramming.friday.Message;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Classe che permette la gestione della chat
 */
@ServerEndpoint(value = "/chat/{LID}/{userID}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {
    
    //implementazione chat
    private Session session;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private static HashMap<String, Set<ChatEndpoint>> chats = new HashMap<>();
    
    //cose per i DAO
    DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
    MessageDAO riverMessageDAO = mySqlFactory.getMessageDAO();
    MessageDAO messageDAO = new MySQLMessageDAOImpl();
    Library library = new Library();

    /**
     * Metodo che permette l'inizializzazzione della chat e l'aggiunta di utenti 
     * @param session
     * @param LID
     * @param userID
     * @throws IOException
     * @throws EncodeException 
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("LID") int LID, @PathParam("userID") String userID) throws IOException, EncodeException {
        
        //inizializzo la chat
        this.session = session;
        Set<ChatEndpoint> chatEndpoints = chats.get(Integer.toString(LID));
        
        //se sono il primo che si connette la creo
        if(chatEndpoints == null){
            chatEndpoints = new CopyOnWriteArraySet<>();
        }
        
        //mi aggiungo
        chatEndpoints.add(this);
        users.put(session.getId(), userID);
        chats.put(Integer.toString(LID), chatEndpoints);
        
        //creo messaggio e aggiorno il DB
        //Message message = new Message(library.LastEntryTable("messageID", "messages"), LID, userID, "Connected!");
        //messageDAO.createMessage(message);
        
        //invio un messaggio che dice che mi sono connesso
        //broadcast(LID, message);
    }

    /**
     * Metodo che permette l'invio di messaggi nella chat specifica
     * @param session
     * @param message
     * @param LID
     * @throws IOException
     * @throws EncodeException 
     */
    @OnMessage
    public void onMessage(Session session, Message message, @PathParam("LID") int LID) throws IOException, EncodeException {
        
        //setto valori mancanti
        message.setSender(users.get(session.getId()));
        message.setLID(LID);
        message.setMessageID(library.LastEntryTable("messageID", "messages"));
        
        //aggiungo messaggio al DB
        messageDAO.createMessage(message);
        
        //invio messaggio
        broadcast(LID, message);
    }

    /**
     * Metodo che permette l'eliminazione dell'utente da una chat specifica
     * @param session
     * @param LID
     * @throws IOException
     * @throws EncodeException 
     */
    @OnClose
    public void onClose(Session session, @PathParam("LID") int LID) throws IOException, EncodeException {
        
        //mi tolgo dalla chat
        Set<ChatEndpoint> chatEndpoints = chats.get(Integer.toString(LID));
        chatEndpoints.remove(this);
        chats.put(Integer.toString(LID), chatEndpoints);
        
        //invio messaggio e aggiungo al DB
        //Message message = new Message(library.LastEntryTable("messageID", "messages"), LID, users.get(session.getId()), "Disonnected!");
        //messageDAO.createMessage(message);
        //broadcast(LID, message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
     
    }

    /**
     * Metodo che permette l'invio di messaggi a tutti i partecipanti della chat della lista
     * @param LID
     * @param message
     * @throws IOException
     * @throws EncodeException 
     */
    private static void broadcast(int LID, Message message) throws IOException, EncodeException {
        
        Set<ChatEndpoint> chatEndpoints = chats.get(Integer.toString(LID));
        
        for(ChatEndpoint endpoint : chatEndpoints){
            synchronized (endpoint) {
                endpoint.session.getBasicRemote()
                        .sendObject(message);
            }
        
        }
    }

}