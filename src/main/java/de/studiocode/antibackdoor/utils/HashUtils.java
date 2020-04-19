package de.studiocode.antibackdoor.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    
    public static byte[] createMD5Hash(byte[] bytes) {
        try {
            return MessageDigest.getInstance("MD5").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static byte[] createMD5Hash(InputStream inputStream) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            byte[] buffer = new byte[4096];
            int len;
            while((len = inputStream.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            
            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String createMD5HashText(byte[] bytes) {
        byte[] hash = createMD5Hash(bytes);
        return DatatypeConverter.printHexBinary(hash);
    }

    public static String createMD5HashText(InputStream inputStream) {
        byte[] hash = createMD5Hash(inputStream);
        return DatatypeConverter.printHexBinary(hash);
    }
    
}
