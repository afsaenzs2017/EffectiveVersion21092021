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
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Luis Cubero
 */
public class HASH {

	/* Retorna un hash a partir de un tipo y un texto */
	public static String getHash(String txt, String hashType) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance(hashType);
			byte[] array = md.digest(txt.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/* Retorna un hash MD5 a partir de un texto */
	public static String md5(String txt) {
		return HASH.getHash(txt, "MD5");
	}

	/* Retorna un hash SHA1 a partir de un texto */
	public static String sha1(String txt) {
		return HASH.getHash(txt, "SHA1");
	}
        
        
        public static void main(String[] args) {
        System.out.println(HASH.md5("@dmin"));
        System.out.println(HASH.sha1("Hola Mundo!"));
}
}
