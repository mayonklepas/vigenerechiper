/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenerechipper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Minami
 */
public class Vigenerechipper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String abjad = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ¡¢£¤¥¦§¨©ª«¬­®¯"
                + "°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþ";
        String rawkata="Kau Mawar Hitam, harum mu kepedihan, Kau arungi waktu disetiap pelukan";
        System.out.println("Kata : "+rawkata);
        String kata = rawkata;
        String shacrypt="";
        try {
           String digg="minami";
           MessageDigest md= MessageDigest.getInstance("SHA-256");
           byte[] has=md.digest(digg.getBytes(StandardCharsets.UTF_8));
           shacrypt=new String(has,StandardCharsets.UTF_8);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Vigenerechipper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("key : "+shacrypt);
        String prekey = shacrypt;
        StringBuilder sbkey = new StringBuilder();
        StringBuilder sbchipper = new StringBuilder();
        boolean start = true;
        int ii = 0;
        int jj = 0;

        
        //membuat key
        while (start) {
            if (ii == prekey.length()) {
                ii = 0;
            } else {
                char ckey = prekey.charAt(ii);
                sbkey.append(ckey);
                jj++;
                ii++;
            }

            if (jj == kata.length()) {
                start = false;
            }
        }

        String key = sbkey.toString();

        
        
        //encript
        for (int i = 0; i < kata.length(); i++) {
            char ckata = kata.charAt(i);
            char ckey = key.charAt(i);
            int posisikata = abjad.indexOf(ckata);
            int posisikey = abjad.indexOf(ckey);
            int posisichipper = posisikata+posisikey;
            if(posisichipper > abjad.length()){
                posisichipper=posisichipper-abjad.length();
            }
            char chipper = abjad.charAt(posisichipper);
            sbchipper.append(chipper);
        }

        System.out.println("encrypt");
        String hasilchipper = sbchipper.toString();
        System.out.println(hasilchipper);
        
        
        //decript
        StringBuilder sbdechip = new StringBuilder();
        for (int i = 0; i < hasilchipper.length(); i++) {
            char dechip = hasilchipper.charAt(i);
            char deckey = key.charAt(i);
            int posisidchip = abjad.indexOf(dechip);
            int posisidkey = abjad.indexOf(deckey);
            int posisidechipper = posisidchip+posisidkey;
            if(posisidechipper < 0){
                posisidechipper=posisidechipper+abjad.length();
            }
            char dechipper = abjad.charAt(posisidechipper);
            sbdechip.append(dechipper);
        }
        System.out.println("decrypt");
        System.out.println(sbdechip.toString());

    }

}
