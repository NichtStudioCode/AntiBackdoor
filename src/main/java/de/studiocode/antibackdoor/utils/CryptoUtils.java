package de.studiocode.antibackdoor.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class CryptoUtils {
	
	//encryption - string

	public static String encryptAESToString(String data, Key key) throws Exception {
		return encryptAESToString(data.getBytes(), key);
	}
	
	public static String encryptAESToString(byte[] data, Key key) throws Exception {
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		return Base64.getEncoder().encodeToString(c.doFinal(data));
	}
	
	//encryption - byte[]
	
	public static byte[] encryptAES(String data, Key key) throws Exception {
		return encryptAES(data.getBytes(), key);
	}
	
	public static byte[] encryptAES(byte[] data, Key key) throws Exception {
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		return Base64.getEncoder().encode(c.doFinal(data));
	}

	//decryption - string
	
	public static String decryptAESToString(String encryptedData, Key key) throws Exception {
		return decryptAESToString(encryptedData.getBytes(), key);
	}
	
	public static String decryptAESToString(byte[] encrypted, Key key) throws Exception {
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		return new String(c.doFinal(Base64.getDecoder().decode(encrypted)));
	}

	// decryption - byte[]
	
	public static byte[] decryptAES(String encryptedData, Key key) throws Exception {
		return decryptAES(encryptedData.getBytes(), key);
	}

	public static byte[] decryptAES(byte[] encrypted, Key key) throws Exception {
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		return c.doFinal(Base64.getDecoder().decode(encrypted));
	}
	
	// decryption - boolean (try decrypt)

	public static boolean tryDecryptAES(String encryptedData, Key key) {
		return tryDecryptAES(encryptedData.getBytes(), key);
	}

	public static boolean tryDecryptAES(byte[] encrypted, Key key)  {
		try {
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, key);
			c.doFinal(Base64.getDecoder().decode(encrypted));
			return true;
		} catch (Exception ignored) {}
		return false;
	}


	// key
	
	public static Key getAESKey(byte[] key) {
		return new SecretKeySpec(key, "AES");
	}

	public static Key getAESKey(String key) {
		return getAESKey(key.getBytes());
	}

}
