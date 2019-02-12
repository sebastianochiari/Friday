/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.extra;

import it.unitn.aa1718.webprogramming.connection.*;
import it.unitn.aa1718.webprogramming.dao.MyCookieDAO;
import it.unitn.aa1718.webprogramming.dao.ProductCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ProductDAO;
import it.unitn.aa1718.webprogramming.dao.ProductListDAO;
import it.unitn.aa1718.webprogramming.dao.SharingDAO;
import it.unitn.aa1718.webprogramming.dao.SharingProductDAO;
import it.unitn.aa1718.webprogramming.dao.ShoppingListCategoryDAO;
import it.unitn.aa1718.webprogramming.dao.ShoppingListDAO;
import it.unitn.aa1718.webprogramming.dao.UserDAO;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLMyCookieDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLProductListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLSharingProductDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListCategoryDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLShoppingListDAOImpl;
import it.unitn.aa1718.webprogramming.dao.entities.MySQLUserDAOImpl;
import it.unitn.aa1718.webprogramming.encrypt.DBSecurity;
import it.unitn.aa1718.webprogramming.friday.MyCookie;
import it.unitn.aa1718.webprogramming.friday.Product;
import it.unitn.aa1718.webprogramming.friday.ProductCategory;
import it.unitn.aa1718.webprogramming.friday.ProductList;
import it.unitn.aa1718.webprogramming.friday.Sharing;
import it.unitn.aa1718.webprogramming.friday.ShoppingList;
import it.unitn.aa1718.webprogramming.friday.ShoppingListCategory;
import it.unitn.aa1718.webprogramming.friday.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.mail.MessagingException;
import javax.mail.internet.*;
import java.util.*;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Classe generale che permette la gestione di diverse azioni 
 */
public class Library {
    
    /**
     *  Metodo che calcola l'ID della nuova entry da inserire nel database
     * @param col stringa che rappresenta la colonna di riferimento
     * @param table stringa per la tabella di riferimento
     * @return intero che rappresenta la nuova entry della tabella
     */
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
    
    /**
     * Metodo che controlla la presenza dell'immagine all'inserimento della risorsa specificata (liste,prodotti..)
     * @param image stringa passata come parametro che identifica l'immagine
     * @return ritorna una stringa con l'eventuale nome dell'immagine, altrimenti icon seguito da un numero casuale da 0 a 4
     */
    public String ImageControl(String image) {
        
        String tmp = null;
        
        if (image != null && !image.isEmpty()){
                tmp = image;
        } else {
            int num = (new Random()).nextInt(4);
            tmp = "icon"+num;
        }
        
        return tmp;
    }
    
    /**
     * Metodo che permette il cambiamento dell'email dell'utente
     * @param request request passata per permettere il salvataggio dei parametri
     * @param response response per passare la risposta
     * @param encrypt parametro per permettere l'utilizzo di funzioni di hash crittografiche
     * @param library parametro per l'utilizzo di funzioni specificate
     * @param userDAO parametro per l'utilizzo di DAO relativi all'utente
     * @param email stringa che rappresenta l'email dell'utente
     * @param name stringa che rappresenta il nome dell'utente 
     * @param surname stringa per il cognome dell'utente
     * @param avatar stringa per identificare l'immagine dell'utente
     * @param admin boolean che specifica se l'utente è admin oppure no
     * @param list_owner boolean che speficia se l'utente è un list owner oppure no
     * @param confirmed boolean che identifica se l'utente ha confermato la registrazione a friday tramite il link nell'email
     * @throws ServletException
     * @throws IOException 
     */
    public void changePassword (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String email, String name, String surname, String avatar, boolean admin, boolean list_owner, boolean confirmed) throws ServletException, IOException {
        
        String previousPassword = request.getParameter("previousPassword");
        String inputNewPassword = request.getParameter("inputNewPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String typeError = request.getParameter("typeError");
        String changePassword = request.getParameter("changePassword");
        
        String inputNewPasswordEncrypted = encrypt.setSecurePassword(inputNewPassword, email);
        String previousPasswordEncrypted = encrypt.setSecurePassword(previousPassword, email);
        
        boolean isOkay = encrypt.checkString(inputNewPassword);
        
        //System.out.println(userDAO.getPasswordByUserEmail(email));
        //System.out.println(previousPasswordEncrypted);
        
        String errorPresentPassword = "errorPresentPassword";
        request.setAttribute("errorPresentPassword", errorPresentPassword);
        
        if (!userDAO.getPasswordByUserEmail(email).equals(previousPasswordEncrypted)) {
            
            String error = "errorPreviousPassword";
            typeError = error;
            request.setAttribute("errorPreviousPassword", typeError);                
            request.getRequestDispatcher(changePassword).forward(request, response);
            
        } else if (!isOkay) {   

            String error = "errorInputPassword";
            typeError = error;
            request.setAttribute("errorInputPassword", typeError);                
            request.getRequestDispatcher(changePassword).forward(request, response);

        } else if (!inputNewPassword.equals(confirmPassword)) {       

            String error = "errorConfirmPassword";
            typeError = error;
            request.setAttribute("errorConfirmPassword", typeError);
            
            //System.out.println( "-----Le password non coicidono");
            request.getRequestDispatcher(changePassword).forward(request, response);

        } else {
            
            request.setAttribute("errorPresentPassword", null);
            
            User user1 = new User(email, inputNewPasswordEncrypted, name, surname, library.ImageControl(avatar), admin, list_owner, true);
            userDAO.updateUserByEmail(user1);
            
            //System.out.println("ho cambiato la password correttamente");
            
            response.sendRedirect("myaccount.jsp");
            
            
        }
    }
    
    /**
     * Metodo che permette il cambiamento dell'email dell'utente
     * @param request request passata per permettere il salvataggio dei parametri
     * @param response response per passare la risposta
     * @param encrypt parametro per permettere l'utilizzo di funzioni di hash crittografiche
     * @param library parametro per l'utilizzo di funzioni specificate
     * @param userDAO parametro per l'utilizzo di DAO relativi all'utente
     * @param dbpassword stringa ritornata dal database che rappresenta la password 
     * @param name stringa che rappresenta il nome dell'utente 
     * @param surname stringa per il cognome dell'utente
     * @param avatar stringa per identificare l'immagine dell'utente
     * @param admin boolean che specifica se l'utente è admin oppure no
     * @param list_owner boolean che speficia se l'utente è un list owner oppure no
     * @param confirmed boolean che identifica se l'utente ha confermato la registrazione a friday tramite il link nell'email
     * @throws ServletException
     * @throws IOException 
     */
    public void changeEmail (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String dbpassword, String name, String surname, String avatar, boolean admin, boolean list_owner, boolean confirmed) throws ServletException, IOException {
        
        String oldEmail = request.getParameter("oldEmail");
        String inputNewEmail = request.getParameter("inputNewEmail");
        String confirmEmail = request.getParameter("confirmEmail");
        String typeError = request.getParameter("typeError");
        String changeEmail = request.getParameter("changeEmail");
        String password = request.getParameter("password");
        dbpassword = userDAO.getPasswordByUserEmail(oldEmail);
        request.setAttribute("inputEmail", inputNewEmail);
        MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();
        
        String errorPresentEmail = "errorPresentEmail";        
        request.setAttribute("errorPresentEmail", errorPresentEmail);
        String errorInputEmailFormat = "noErrorInputEmailFormat";        
        request.setAttribute("errorInputEmailFormat", errorInputEmailFormat);
        String errorInputEmail = "errorInputEmail";
        request.setAttribute("errorInputEmail", null);
        
        if (!userDAO.checkUser(oldEmail)) {
            
            //System.out.println("questa email non esiste nel database");
            String error = "errorOldEmail";
            typeError = error;
            request.setAttribute("errorOldEmail", typeError);
            request.getRequestDispatcher(changeEmail).forward(request, response);
            
        } else if(!userDAO.checkEmail(inputNewEmail)) {
            
            //System.out.println("IL FORMATO DI QUESTA EMAIL NON è CORRETTO ");
            errorInputEmailFormat = "errorInputEmailFormat";
            typeError = errorInputEmailFormat;
            request.setAttribute("errorInputEmail", errorInputEmail);
            request.setAttribute("errorInputEmailFormat", typeError);
            request.getRequestDispatcher(changeEmail).forward(request, response);
            
        } else if (userDAO.checkUser(inputNewEmail)) {
            
            request.setAttribute("errorInputEmail", errorInputEmail);
            request.setAttribute("inputEmail", inputNewEmail);
            
            request.getRequestDispatcher(changeEmail).forward(request, response);
            
        } else if (!inputNewEmail.equals(confirmEmail)) {   
            
            String error = "errorConfirmEmail";
            typeError = error;
            request.setAttribute("errorConfirmEmail", typeError);
            
            //System.out.println( "-----Le email non coicidono");
            request.getRequestDispatcher(changeEmail).forward(request, response);

        } else {
            
            String pswencrypted = encrypt.setSecurePassword(password, oldEmail);
            
            if (pswencrypted.equals(dbpassword)) {
                
                
                request.setAttribute("errorPresentEmail", null);
                
                String newpswencrypted = encrypt.setSecurePassword(password, inputNewEmail);

                (request.getSession()).setAttribute("emailSession", inputNewEmail);

                User userPassword = new User(inputNewEmail, dbpassword, name, surname, library.ImageControl(avatar), admin, list_owner, true);
                userDAO.updateUserByPassword(userPassword);
                User userEmail = new User(inputNewEmail, newpswencrypted, name, surname, library.ImageControl(avatar), admin, list_owner, true);
                userDAO.updateUserByEmail(userEmail);

                //bisogna sistemare anche la tabella cookie e quindi forse aggiornarla?

                System.out.println("ho cambiato la email correttamente");
                
                response.sendRedirect("myaccount.jsp");
            
            } else {
            
                //System.out.println("PASSWORD DIVERSE !!!!!!!!!!");
                //System.out.println("la password non corrisponde all'email inserita ");
                String error = "errorPassword";
                typeError = error;
                request.setAttribute("errorPassword", typeError);
                request.getRequestDispatcher(changeEmail).forward(request, response);
            
            }
            
        }
        
    }
    
    /**
     * Metodo per cambiare informazioni personali dell'utente come nome, cognome e avatar
     * @param request request passata per permettere il salvataggio dei parametri
     * @param response response per passare la risposta
     * @param encrypt parametro per permettere l'utilizzo di funzioni di hash crittografiche
     * @param library parametro per l'utilizzo di funzioni specificate
     * @param userDAO parametro per l'utilizzo di DAO relativi all'utente
     * @param email string che rappresenta l'ID univoco dell'utente
     * @param dbpassword stringa che rappresenta la password presente nel database 
     * @param name stringa che rappresenta il nome dell'utente
     * @param surname stringa che rappresenta il cognome dell'utente
     * @param avatar stringa che rappresenta l'avatar dell'utente
     * @param admin booleano che verifica se l'utente è un admin oppure no
     * @param list_owner booleano che verifica se è possessore di liste
     * @param confirmed booleano che verifica se è presente la conferma della registrazione del profilo 
     * @throws ServletException
     * @throws IOException 
     */
    public void changePersonal (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String email, String dbpassword, String name, String surname, String avatar, boolean admin, boolean list_owner, boolean confirmed) throws ServletException, IOException {
        
        String newName = request.getParameter("newName");
        String newSurname = request.getParameter("newSurname");
        String newAvatar = request.getParameter("newAvatar");
        String typeError = request.getParameter("typeError");
        String changePersonal = request.getParameter("changePersonal");
        
        String errorPresentPersonal = "errorPresentPersonal";
        request.setAttribute("errorPresentPersonal", errorPresentPersonal);
        
        if (newName.isEmpty()) {
            String error = "nameError";
            typeError = error;
            request.setAttribute("nameError", typeError);
            request.getRequestDispatcher(changePersonal).forward(request, response);
        } else if (newSurname.isEmpty()) {
            String error = "surnameError";
            typeError = error;
            request.setAttribute("surnameError", typeError);
            request.getRequestDispatcher(changePersonal).forward(request, response);
        } else {
            // se avatar è null che si fa? bisogna per forza sceglierne uno oppure si può anche lasciarlo vuoto?
            (request.getSession()).setAttribute("nameUserSession", newName);
            (request.getSession()).setAttribute("surnameUserSession", newSurname);
            (request.getSession()).setAttribute("avatarUserSession", newAvatar);
            request.setAttribute("errorPresentPersonal", null);
            
            User user1 = new User(email, dbpassword, newName, newSurname, library.ImageControl(newAvatar), admin, list_owner, true);
            userDAO.updateUserByEmail(user1);
            
            System.out.println("name e surname e avatar aggiornati.");
            
            response.sendRedirect("myaccount.jsp");
        }
        
    }
    
    /**
     * Metodo che permette il cambiamento da utente registrato ad admin
     * @param request request passata per permettere il salvataggio dei parametri
     * @param response response per passare la risposta
     * @param encrypt parametro per permettere l'utilizzo di funzioni di hash crittografiche
     * @param library parametro per l'utilizzo di funzioni specificate
     * @param userDAO parametro per l'utilizzo di DAO relativi all'utente
     * @param email string che rappresenta l'ID univoco dell'utente
     * @param dbpassword stringa che rappresenta la password presente nel database 
     * @param name stringa che rappresenta il nome dell'utente
     * @param surname stringa che rappresenta il cognome dell'utente
     * @param avatar stringa che rappresenta l'avatar dell'utente
     * @param list_owner booleano che verifica se è possessore di liste
     * @param confirmed booleano che verifica se è presente la conferma della registrazione del profilo 
     * @throws ServletException
     * @throws IOException 
     */
    public void changeAdmin (HttpServletRequest request, HttpServletResponse response, DBSecurity encrypt, Library library, UserDAO userDAO, String email, String dbpassword, String name, String surname, String avatar, boolean list_owner, boolean confirmed) throws ServletException, IOException {
        
        boolean admin = true;
        
        request.getSession().setAttribute("adminUserSession", admin);
        
        User user1 = new User(email, dbpassword, name, surname, library.ImageControl(avatar), admin, list_owner, confirmed);
        userDAO.updateUserByEmail(user1);

        System.out.println("utente è ora amministratore");

        response.sendRedirect("adminSection.jsp");
        
    }
    
    /**
     * Metodo che ritorna il risultato della ricerca effettuata sulla tabella prodotti
     * @param products lista di prodotti
     * @param productCategoryDAO istanza passata per permettere l'utilizzo di funzioni DAO in product category
     * @return matrice con i risultati ottenuti in base alla ricerca
     */
    public String[][] getSearchResults(List products, ProductCategoryDAO productCategoryDAO){
        
        String[][] searchProductResult = new String[products.size()][10];
        
        UserDAO userDAO = new MySQLUserDAOImpl();
        SharingProductDAO sharingProductDAO = new MySQLSharingProductDAOImpl();
        List userSharedProduct = null;
        
        for(int i=0; i<products.size(); i++){

                searchProductResult[i][0] = Integer.toString(((Product)(products.get(i))).getPID());
                searchProductResult[i][1] = ((Product)(products.get(i))).getName();
                searchProductResult[i][2] = ((Product)(products.get(i))).getNote();
                searchProductResult[i][3] = ((Product)(products.get(i))).getLogo();
                searchProductResult[i][4] = ((Product)(products.get(i))).getPhoto();
                searchProductResult[i][5] = (productCategoryDAO.getProductCategory(((Product)(products.get(i))).getPCID())).getName();
                searchProductResult[i][6] = ((Product)(products.get(i))).getEmail();
                searchProductResult[i][7] = userDAO.getUser(((Product)(products.get(i))).getEmail()).getName();
                searchProductResult[i][8] = userDAO.getUser(((Product)(products.get(i))).getEmail()).getSurname();
                
                if (!userDAO.getUser(((Product)(products.get(i))).getEmail()).getAdmin()) {
                    userSharedProduct = sharingProductDAO.getAllEmailsbyPID(Integer.parseInt(searchProductResult[i][0]));
                    if (userSharedProduct.isEmpty() || userSharedProduct.size()>1){
                        searchProductResult[i][9] = String.valueOf(userSharedProduct.size()) + " utenti";
                    } else {
                        searchProductResult[i][9] = String.valueOf(userSharedProduct.size()) + " utente";
                    }
                } else {
                    searchProductResult[i][9] = "Tutti gli utenti";
                }

            }
        return searchProductResult;
    }

    /**
     * Metodo che ritorna i prodotti della lista passata come parametro
     * @param LID intero identificativo univoco della lista passata come parametro
     * @param request request per permettere il salvataggio di parametri in input presenti in request
     */
    public void prodottiDellaLista(int LID, HttpServletRequest request){
        
        HttpSession session = request.getSession();
        
        ProductListDAO productListDAO = new MySQLProductListDAOImpl();
        ProductDAO productDAO = new MySQLProductDAOImpl();
        ProductCategoryDAO productCategoryDAO = new MySQLProductCategoryDAOImpl();
        UserDAO userDAO = new MySQLUserDAOImpl();
        
        List productList = productListDAO.getPIDsByLID(LID);
        Product product = null;
        String [][] prodotto = new String [productList.size()][10];
        
        SharingProductDAO sharingProductDAO = new MySQLSharingProductDAOImpl();
        List userSharedProduct = null;
        
        for(int i=0; i<productList.size(); i++){
            product = productDAO.getProduct(((ProductList)(productList.get(i))).getPID(), (String)session.getAttribute("emailSession"));
            prodotto[i][0] = product.getName();
            prodotto[i][1] = product.getNote();
            prodotto[i][2] = product.getLogo();
            prodotto[i][3] = product.getPhoto();
            prodotto[i][4] = productCategoryDAO.getProductCategory(product.getPCID()).getName();
            prodotto[i][5] = userDAO.getUser(product.getEmail()).getName();
            prodotto[i][6] = Integer.toString(((ProductList)(productList.get(i))).getQuantity());
            prodotto[i][7] = Integer.toString(product.getPID());
            prodotto[i][8] = userDAO.getUser(product.getEmail()).getSurname();
            
            if (!userDAO.getUser(product.getEmail()).getAdmin()) {
                userSharedProduct = sharingProductDAO.getAllEmailsbyPID(Integer.parseInt(prodotto[i][7]));
                if (userSharedProduct.isEmpty() || userSharedProduct.size()>1){
                    prodotto[i][9] = String.valueOf(userSharedProduct.size()) + " utenti";
                } else {
                    prodotto[i][9] = String.valueOf(userSharedProduct.size()) + " utente";
                }
            } else {
                prodotto[i][9] = "Tutti gli utenti";
            }
        }
        
        session.setAttribute("Prodotto", prodotto);
        
    }
    
    /**
     * Metodo che permette di recuperare le liste che appartengono all'utente che ha effettuato il login
     * @param request request passata per permettere il salvataggio dei parametri
     * @param response response per passare la risposta
     */
    public void recuperoListeUtenteloggato(HttpServletRequest request, HttpServletResponse response){
       
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        HttpSession session = request.getSession();
        
        ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
        SharingDAO sharingDAO = new MySQLSharingDAOImpl();
        ShoppingListCategoryDAO shoppingListCategoryDAO = new MySQLShoppingListCategoryDAOImpl();
        
        //elimino shopping list anonime scadute
        shoppingListDAO.deleteExpiredShoppingLists();
        
        if(session.getAttribute("emailSession") != null){
                
            // START: recupero delle liste che appartengono all'utente loggato
            
            List lists = shoppingListDAO.getShoppingListsByOwner((String)session.getAttribute("emailSession"));
            String[][] searchListResult = new String[lists.size()][4];

            for(int i=0; i<lists.size(); i++){
                searchListResult[i][0] = ((ShoppingList)(lists.get(i))).getName();
                searchListResult[i][1] = Integer.toString(((ShoppingList)(lists.get(i))).getLID());
                searchListResult[i][2] = ((ShoppingListCategory)(shoppingListCategoryDAO.getShoppingListCategory(((ShoppingList)(lists.get(i))).getLCID()))).getName();
                List listaCondivisa = sharingDAO.getAllEmailsbyList(Integer.parseInt(searchListResult[i][1]));
                if (listaCondivisa.isEmpty()){
                    searchListResult[i][3] = Integer.toString(0);
                } else {
                    searchListResult[i][3] = Integer.toString(1);
                }
            }

            session.setAttribute("ListUserSession", searchListResult);
            session.setAttribute("ListUserSessionSize", lists.size());

            // END: recupero delle liste che appartengono all'utente loggato
                
            // START: recupero delle liste condivise dell'utente loggato
            
            List sharingLists = sharingDAO.getAllListByEmail((String)session.getAttribute("emailSession"));
            ShoppingList tmp = null;
            String[][] sharingListResult = new String[sharingLists.size()][3];

            for(int i=0; i<sharingLists.size(); i++){

                tmp = shoppingListDAO.getShoppingList(((Sharing)(sharingLists.get(i))).getLID());

                sharingListResult[i][0] = tmp.getName();
                sharingListResult[i][1] = Integer.toString(tmp.getLID());
                sharingListResult[i][2] = (shoppingListCategoryDAO.getShoppingListCategory(tmp.getLCID())).getName();
            }   

            session.setAttribute("SharingListUserSession", sharingListResult);
            session.setAttribute("SharingListUserSessionSize", sharingLists.size());
                
            // END: recupero delle liste condivise dell'utente loggato                
                
        } else {
            
            int cookieID;
    
            if(session.getAttribute("cookieIDSession") == null){

            MyCookieDAO riverCookieDAO = mySqlFactory.getMyCookieDAO();
            MyCookieDAO myCookieDAO = new MySQLMyCookieDAOImpl();

            //cancello eventuali cookie scaduti
            myCookieDAO.deleteDBExpiredCookies();

            //Creo cookie
            Cookie cookie = new Cookie("FridayAnonymous", Integer.toString(LastEntryTable("cookieID", "cookies")));
            cookie.setMaxAge(-1);
            cookieID = Integer.parseInt((String)cookie.getValue());

            Long Deadline = (new Timestamp(System.currentTimeMillis())).getTime();

            myCookieDAO.createCookie(new MyCookie(LastEntryTable("cookieID", "cookies"), 0, null, Deadline));
            session.setAttribute("cookieIDSession", Integer.parseInt(cookie.getValue()));
            response.addCookie(cookie);
            
            } else {
                cookieID = (int)session.getAttribute("cookieIDSession");
            }
    
            ShoppingList shoppingList = shoppingListDAO.getAnonymusShoppingList(cookieID);
            String[][] ListResult;

            if(shoppingList != null){

                ListResult = new String[1][3];
                ListResult[0][0] = shoppingList.getName();
                ListResult[0][1] = Integer.toString(shoppingList.getLID());
                ListResult[0][2] = (shoppingListCategoryDAO.getShoppingListCategory(shoppingList.getLCID())).getName();
                session.setAttribute("ListUserSessionSize", 1);

            } else {
                ListResult = new String[0][3];
                session.setAttribute("ListUserSessionSize", 0);
            }
                
            session.setAttribute("ListUserSession", ListResult);
                
        }
        
    }
    
    /**
     * 
     * @param product
     * @param email
     * @return 
     */
    public List<Product> togliProdottiNonCondivisi(List<Product> product, String email){
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        SharingProductDAO sharingProductriverDAO = mySqlFactory.getSharingProductDAO();
        SharingProductDAO sharingProductDAO = new MySQLSharingProductDAOImpl();
        
        
        
        if(email == null){
        
            List DaTogliere = sharingProductDAO.getAllSharingProduct();
        
        
        }
        
    
        return product;
    }
    
    /**
     * Metodo che permette l'invio delle email per la registrazione a Friday 
     * @param email stringa email dell'utente
     * @param name stringa nome dell'utente
     * @param surname stringa per il cognome dell'utente
     * @throws AddressException
     * @throws MessagingException 
     */
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
                                "\n\n link di conferma : http://localhost:8080/Friday/confirmRegistrationServlet?email="+email);

                Transport.send(message);

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
    
    /**
     * Metodo che crea un attributo di sessione con l'elenco di tutte le categorie di prodotto
     * @param request 
     */
    public void createProductCategory(HttpServletRequest request){
        
        HttpSession session = request.getSession();
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        ProductCategoryDAO riverProductCategoryDAO = mySqlFactory.getProductCategoryDAO();
        ProductCategoryDAO productCategoryDAO = new MySQLProductCategoryDAOImpl();
        
        List productCategories = productCategoryDAO.getAllProductCategories();
        
        //productCategories
        ProductCategory tmp3 = null;
        String[][] productCategoriesMatrix = new String[productCategories.size()][2];

        for(int i=0; i<productCategories.size(); i++){

            tmp3 = (ProductCategory)productCategories.get(i);

            productCategoriesMatrix[i][0] = Integer.toString(tmp3.getPCID());
            productCategoriesMatrix[i][1] = tmp3.getName();
            
        }
        
        session.setAttribute("productCategories", productCategoriesMatrix);
    
    }
    
    /**
     * Metodo che crea degli attributi utilizzati per la customizzazzione della home page index.jsp
     * @param request 
     */
    public void createListIndex(HttpServletRequest request){
    
        HttpSession session = request.getSession();
        
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory();
        ShoppingListDAO riverShoppingListDAO = mySqlFactory.getShoppingListDAO();
        ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
        ProductCategoryDAO riverProductCategoryDAO = mySqlFactory.getProductCategoryDAO();
        ProductCategoryDAO productCategoryDAO = new MySQLProductCategoryDAOImpl();
        ProductListDAO riverProductListDAO = mySqlFactory.getProductListDAO();
        ProductListDAO productListDAO = new MySQLProductListDAOImpl();
        ProductDAO riverProductDAO = mySqlFactory.getProductDAO();
        ProductDAO productDAO = new MySQLProductDAOImpl();
        UserDAO riverUserDAO = mySqlFactory.getUserDAO();
        UserDAO userDAO = new MySQLUserDAOImpl();
        
        List resultSharingList = null;
        List AllProductInListRand = null;
        List resultList = null;   
        ShoppingList resultListRand = null;
        
        String email = null;
        int cookieID;
        
        if(session.getAttribute("emailSession")!=null){
            
            email = (String)session.getAttribute("emailSession");  
            
            resultSharingList = shoppingListDAO.getAllShoppingListEditable(email);
        
            if(session.getAttribute("cookieIDSession")!=null){
                
                cookieID = (int)session.getAttribute("cookieIDSession");
                
                //System.out.println(cookieID+"     "+email);
                
                resultListRand = shoppingListDAO.getRandShoppingList(email, cookieID);
                
                if(resultListRand != null){
                    AllProductInListRand = productListDAO.getPIDsByLID(resultListRand.getLID());
                }
                
                resultList = shoppingListDAO.getShoppingListByUserIDOrCookieID(email, cookieID);
            
            }
        
        }
        
        List prodottiRandom = null;
        if(session.getAttribute("emailSession")!=null){
            prodottiRandom = productDAO.getRandomProduct((String)session.getAttribute("emailSession"));
        } else {
            prodottiRandom = productDAO.getRandomProduct(null);
        }
        
        //resultListRand
        
        String[][] resultListRandMatrix = null;
        
        if(resultListRand != null){
        
            resultListRandMatrix = new String[1][2];
            session.setAttribute("listaAnonimo", true);
        
            resultListRandMatrix[0][0] = Integer.toString(resultListRand.getLID());
            resultListRandMatrix[0][1] = resultListRand.getName();
        
        } else {
            resultListRandMatrix = new String[0][2];
        }
        
        //AllProductInListRand
        Product tmp1 = null;
        User tmp2 = null;
        ProductCategory productCategory = null;
        SharingProductDAO sharingProductDAO = new MySQLSharingProductDAOImpl();
        List userSharedProduct = null;
        
        String[][] AllProductInListRandMatrix = null;
        
        if(AllProductInListRand != null){
            
            AllProductInListRandMatrix = new String[AllProductInListRand.size()][9];

            for(int i=0; i<AllProductInListRand.size(); i++){
                
                //System.out.println(((ProductList)(AllProductInListRand.get(i))).getPID()+"   "+email);

                tmp1 = productDAO.getProduct(((ProductList)(AllProductInListRand.get(i))).getPID(), email);
                tmp2 = userDAO.getUser(tmp1.getEmail());
                productCategory = productCategoryDAO.getProductCategory(tmp1.getPCID());

                AllProductInListRandMatrix[i][0] = Integer.toString(tmp1.getPID());
                AllProductInListRandMatrix[i][1] = tmp1.getName();
                AllProductInListRandMatrix[i][2] = tmp1.getNote();
                AllProductInListRandMatrix[i][3] = tmp1.getLogo();
                AllProductInListRandMatrix[i][4] = tmp1.getPhoto();
                AllProductInListRandMatrix[i][5] = tmp2.getName();
                AllProductInListRandMatrix[i][6] = tmp2.getSurname();
                AllProductInListRandMatrix[i][7] = productCategory.getName();
                
                if (!(tmp2).getAdmin()) {
                    userSharedProduct = sharingProductDAO.getAllEmailsbyPID(Integer.parseInt(AllProductInListRandMatrix [i][0]));
                    if (userSharedProduct.isEmpty() || userSharedProduct.size()>1){
                        AllProductInListRandMatrix[i][8] = String.valueOf(userSharedProduct.size()) + " utenti";
                    } else {
                        AllProductInListRandMatrix[i][8] = String.valueOf(userSharedProduct.size()) + " utente";
                    }
                } else {
                    AllProductInListRandMatrix[i][8] = "Tutti gli utenti";
                }

            }
        
        } else {
            AllProductInListRandMatrix = new String[0][9];
        }
        
        //resultList
        ShoppingList tmp = null;
        String[][] resultListMatrix = null;
        
        if(resultList != null){
            
            resultListMatrix = new String[resultList.size()][2];

            for(int i=0; i<resultList.size(); i++){

                tmp = shoppingListDAO.getShoppingList(((ShoppingList)(resultList.get(i))).getLID());

                resultListMatrix[i][0] = Integer.toString(tmp.getLID());
                resultListMatrix[i][1] = tmp.getName();

            }
        
        } else {
            resultListMatrix = new String[0][2];
        }
        
        //resultSharingList
        tmp = null;
        String[][] resultSharingListMatrix = null;
        
        if(resultSharingList != null){
            
            resultSharingListMatrix = new String[resultSharingList.size()][2];

            for(int i=0; i<resultSharingList.size(); i++){

                tmp = shoppingListDAO.getShoppingList(((ShoppingList)(resultSharingList.get(i))).getLID());

                resultSharingListMatrix[i][0] = Integer.toString(tmp.getLID());
                resultSharingListMatrix[i][1] = tmp.getName();

            }
        
        } else {
            resultSharingListMatrix = new String[0][2];
        }
        
        //prodottiRandom
        tmp1 = null;
        tmp2 = null;
        String[][] prodottiRandomMatrix = new String[prodottiRandom.size()][9];
        
        for(int i=0; i<prodottiRandom.size(); i++){
               
            tmp1 = (Product)prodottiRandom.get(i);
            tmp2 = userDAO.getUser(tmp1.getEmail());
            
            prodottiRandomMatrix[i][0] = Integer.toString(tmp1.getPID());
            prodottiRandomMatrix[i][1] = tmp1.getName();
            prodottiRandomMatrix[i][2] = tmp1.getNote();
            prodottiRandomMatrix[i][3] = tmp1.getLogo();
            prodottiRandomMatrix[i][4] = tmp1.getPhoto();
            prodottiRandomMatrix[i][5] = (productCategoryDAO.getProductCategory(tmp1.getPCID())).getName();
            prodottiRandomMatrix[i][6] = tmp2.getName();
            prodottiRandomMatrix[i][7] = tmp2.getSurname();
            if (!(tmp2).getAdmin()) {
                userSharedProduct = sharingProductDAO.getAllEmailsbyPID(Integer.parseInt(prodottiRandomMatrix [i][0]));
                if (userSharedProduct.isEmpty() || userSharedProduct.size()>1){
                    prodottiRandomMatrix[i][8] = String.valueOf(userSharedProduct.size()) + " utenti";
                } else {
                    prodottiRandomMatrix[i][8] = String.valueOf(userSharedProduct.size()) + " utente";
                }
            } else {
                prodottiRandomMatrix[i][8] = "Tutti gli utenti";
            } 

        }
        
        session.setAttribute("resultListRand", resultListRandMatrix);
        session.setAttribute("resultList", resultListMatrix);
        session.setAttribute("resultSharingList", resultSharingListMatrix); 
        session.setAttribute("AllProductInListRand", AllProductInListRandMatrix);
        session.setAttribute("prodottiRand", prodottiRandomMatrix);
    
    }
    
    /**
     * Metodo che permette la gestione dell'autocompletamento, salvando i prodotti in un vettore  
     * @param session 
     */
    public void createAutocomplete(HttpSession session){
        
        ProductDAO productDAO = new MySQLProductDAOImpl();
        List products = null;
        
        if(session.getAttribute("emailSession") != null){
            products = productDAO.getAllProducts((String)session.getAttribute("emailSession"));
        } else {
            products = productDAO.getAllProducts(null);
        }

        String [] productVector = new String [products.size()];

        for (int i = 0; i < products.size(); i++){
            productVector[i] = ((Product)products.get(i)).getName();
        }

        StringBuffer values = new StringBuffer();
        for (int i = 0; i < productVector.length; ++i) {
            if (values.length() > 0) {
                values.append(',');
            }
            values.append('"').append(productVector[i]).append('"');
        }
        
        session.setAttribute("Autocomplete", values);
    
    }
 
}

