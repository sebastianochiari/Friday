/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.aa1718.webprogramming.extra;

/**
 *
 * @author tommi
 */
public class ImageControl {
    
    String logo = null;
    
    public ImageControl(String image) {
        
        this.logo = image;
        
    }
    
    public String Check(String image) {
        String tmp = null;
        
        if (image != null && !image.isEmpty()){
                tmp = image;
        } else {
            tmp = "null";
        }
        
        return tmp;
    }

}
