/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author
 */

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

public class ENCRIPDECRIP 
{
        public static String secretKey = "md5";   
        public static void main(String[] args) 
        {
            
            ENCRIPDECRIP mMain = new ENCRIPDECRIP();
            String cadenaAEncriptar = JOptionPane.showInputDialog("Ingresa la cadena a encriptar");
            String cadenaEncriptada = mMain.ecnode(secretKey, cadenaAEncriptar);
            JOptionPane.showMessageDialog(null, "Cadena encriptada: " + cadenaEncriptada);
            System.out.println("Cadena encriptada: " + cadenaEncriptada);
            String cadenaDesencriptada = mMain.deecnode(secretKey, cadenaEncriptada);
            JOptionPane.showMessageDialog(null, "Cadena desencriptada: " + cadenaDesencriptada);
            System.out.println("Cadena desencriptada: " +cadenaDesencriptada);
        }
        
        public String ecnode(String secretKey, String cadena) 
        {
            String encriptacion = "";
            try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = cadena.getBytes("utf-8");
            byte[] buf = cifrado.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            encriptacion = new String(base64Bytes);
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
        return encriptacion;
        }

        public static String deecnode(String secretKey, String cadenaEncriptada)
        {
            String desencriptacion = "";
            try 
            {
                byte[] message = Base64.decodeBase64(cadenaEncriptada.getBytes("utf-8"));
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
                byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
                SecretKey key = new SecretKeySpec(keyBytes, "DESede");
                Cipher decipher = Cipher.getInstance("DESede");

                decipher.init(Cipher.DECRYPT_MODE, key);
                byte[] plainText = decipher.doFinal(message);
                desencriptacion = new String(plainText, "UTF-8");
            } 
            catch (Exception ex) 
            {
                System.out.println(ex.getMessage());
            }
            return desencriptacion;
        }
}

