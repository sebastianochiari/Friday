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
 *
 * @author marta
 */
public class DBSecurity {
    
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int KEY_LENGTH = 20;
    
//funzione che genera salt in maniera randomica
     public String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }    

//funzione che alla registrazione dell'utente attraverso salting la encripta   
public String setSecurePassword(String passwordToHash, String salt){
String generatedPassword = null;
    try {
         MessageDigest md = MessageDigest.getInstance("SHA-256");
         md.update(salt.getBytes(StandardCharsets.UTF_8));
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
    return generatedPassword;
}
    

//funzione per comparare se le password durante il login coincidono
public String getSecurePassword (String planePassword, String encryptedPassword){

 
  /*  BasicPasswordEncryptor en = new BasicPasswordEncryptor();
    return en.checkPassword(planePassword, encryptedPassword);

 */

    
    return null;
    
}
    




    
}
