/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.friday;

/**
 * Classe User che identifica un utente specifico
 */
public class User {
    
    // dati utente
    private String email = null;
    private String password = null;
    private String name = null;
    private String surname = null;
    private String avatar = null;
    private boolean admin;
    private boolean list_owner;
    private boolean confirmed;
    private int sharing_list[] = null;
    
    /**
     * Costruttore
     * @param email primary key nella tabella utenti, rappresenta l'unico modo per identificare l'utente in modo univoco
     * @param password password dell'utente
     * @param name nome dell'utente
     * @param surname cognome dell'utente
     * @param avatar nome immagine che l'utente ha scelto come avatar
     * @param admin booleano che se settato ad 1 identifica l'utente come admin
     * @param list_owner booleano che se settato ad 1 identifica l'utente come list owner
     * @param confirmed booleano che gestisce la conferma o no del profilo dell'utente in fase di registrazione. Se settato a 1 si ha la conferma.
     */
    public User(String email, String password, String name, String surname, String avatar, boolean admin, boolean list_owner, boolean confirmed) {
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
        this.admin = admin;
        this.list_owner = list_owner;
        this.confirmed = confirmed;
        this.sharing_list = new int[100];
    }
    
    /**
     * Metodo che ritorna l'email dell'utente sul quale viene chiamato
     * @return stringa che rappresenta l'email dell'utente 
     */
    public String getEmail() {        
        return email;    
    }  
    /**
     * Metodo che setta l'email dell'utente sul quale viene chiamato
     */
    public void setEmail() {        
        this.email = email;    
    }
    /**
     * Metodo che ritorna la password dell'utente sul quale viene chiamato
     * @return stringa che rappresenta la password dell'utente
     */
    public String getPassword() {        
        return password;    
    }   
    /**
     * Metodo che setta la password dell'utente sul quale viene chiamato
     */
    public void setPassword() {        
        this.password = password;    
    }
    /**
     * Metodo che ritorna il nome dell'utente sul quale viene chiamato
     * @return stringa che rappresenta il nome dell'utente
     */
    public String getName() {        
        return name;    
    }    
    /**
     * Metodo che setta il nome dell'utente sul quale viene chiamato
     */
    public void setName() {        
        this.name = name;    
    }
    /**
     * Metodo che ritorna il cognome dell'utente sul quale viene chiamato
     * @return stringa che rappresenta il cognome dell'utente
     */
    public String getSurname() {        
        return surname;    
    }    
    /**
     * Metodo che setta il cognome dell'utente sul quale viene passato
     */
    public void setSurname() {        
        this.surname = surname;    
    }
    /**
     * Metodo che ritorna l'avatar dell'utente sul quale viene chiamato
     * @return stringa che ritorna il nome dell'immagine utilizzata come avatar
     */
    public String getAvatar() {        
        return avatar;    
    }    
    /**
     * Metodo che setta l'avatar dell'utente sul quale viene chiamato
     */
    public void setAvatar() {        
        this.avatar = avatar;    
    }
    /**
     * Metodo che ritorna il valore 
     * @return ritorna un boolean: 1 se è admin, 0 se non lo è
     */
    public boolean getAdmin() {        
        return admin;    
    }    
    /**
     * Metodo che setta ad 1 o 0 il valore di Admin nel database
     */
    public void setAdmin() {        
        this.admin = admin;    
    }
    /**
     * Metodo che ritorna se l'utente è un possessore di liste
     * @return ritorna un boolean: 1 se è un list owner, 0 se non lo è
     */
    public boolean getListOwner() {        
        return list_owner;    
    }    
    /**
     * Metodo che setta ad 1 il valore di listOwner nel database: 1 se è list owner, 0 se non lo è
     */
    public void setListOwner() {        
        this.list_owner = list_owner;    
    }
    /**
     * Metodo che ritorna un boolean: se è 1 ha la conferma di registrazione da parte dell'utente
     * @return 
     */
    public Boolean getConfirmed() {        
        return confirmed;    
    }  
    /**
     * Metodo che setta la conferma o no della registrazione da parte di un utente
     */
    public void setConfirmed() {        
        this.confirmed = confirmed;    
    }
    /**
     * Metodo che ritorna le liste condivise dell'utente
     * @return ritorna un array di interi che rappresenta LID delle liste condivise dell'utente
     */
    public int[] getSharingList() {        
        return sharing_list;    
    }
    /**
     * Metodo che setta le liste condivise dell'utente
     */    
    public void setSharingList() {        
        this.sharing_list = sharing_list;    
    }
    
}
