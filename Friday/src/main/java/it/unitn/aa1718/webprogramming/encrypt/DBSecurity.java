/*
 * WebProgramming Project - Shopping List 
 * 2017-2018
 * Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
 */
package it.unitn.aa1718.webprogramming.encrypt;
   
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

//UTILI SOLO SE USIAMO LE FUNZIONI GIà IMPLEMENTATE, ALTRIMENTI TOGLI DEPENDENCY DI JASYPT
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

/**
 *  Classe che implementa funzioni di sicurezza per i dati salvati nel database
 */
public class DBSecurity {
    
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int KEY_LENGTH = 20;
    
    /**
     * Metodo che alla registrazione dell'utente prende password e attraverso un salting la cripta
     * @param passwordToHash stringa password inserita dall'utente
     * @param salt stringa salting che verrà unita alla password per aumentare la randomness dell'output
     * @return stringa che verrà salvata nel database nel campo password
     */

    public String setSecurePassword(String passwordToHash, String salt){
        System.out.println("SONO IN SETSECUREPASSWORD");
        System.out.println("in DBSECURITY PSW TO HASH: " + passwordToHash);
        System.out.println("IN DBSECURITY SALTING: " + salt);
        String generatedPassword = null;

        try {
            //Creation of MessageDigest for SHA-256 algorithm (?)
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            
//QUESTO PRIMA NON ERA COMMENTATO! SERVE PER LA CRIPTAZIONE  ... parte sotto 
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            passwordToHash = " " + passwordToHash + salt;
            System.out.println("psw + salting (= email) is : " + passwordToHash);
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
             for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
             }
             generatedPassword = sb.toString();
            } 
           catch (NoSuchAlgorithmException e){
            e.printStackTrace();
           }

        if(generatedPassword == null ){
            System.out.println("LA PSW GENERATA è NULLA! ERRORE IN CRIPTAZIONE");
        }

        System.out.println("IN DBSECURITY LA PASSWORD HASHED:" + generatedPassword);
        return generatedPassword;

    }
 
    /**
     * Metodo che controlla la correttezza della password inserita. Verifica che sia conforme ai requisiti richiesti per le pws
     * @param str stringa che rappresenta la password dell'utente
     * @return boolean che ritorna 1 se la password è corretta 
     */   
    public boolean checkString(String str) {
        char ch;
        boolean capitalLetter = false;
        boolean lowerCaseFlag = false;
        if(str.length() < 6){
            System.out.println("AT LEAST 6 CHARACTERS!");
            return false;
        } else{
        boolean number = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                System.out.println("THERE IS A NUMBER");
                number = true;
            }
            else if (Character.isUpperCase(ch)) {
                System.out.println("THERE IS An UPPERCASE");
                capitalLetter = true;
            }
            if(number && capitalLetter)
                return true;
        }
        return false;
        }
    }



    
}
