/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author marta
 */
public class DBSecurity {
    
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int KEY_LENGTH = 20;
    
  

    //funzione che alla registrazione dell'utente prende la password attraverso salting la encripta   
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
 

//funzione per comparare se le password durante il login coincidono
public String getSecurePassword (String planePassword, String encryptedPassword){

    

    
    return null;
    
}
    

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
