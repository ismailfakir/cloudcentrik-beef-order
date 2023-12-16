package net.cloudcentrik.beeforder.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Callable;

public class BeefOrderUtils {

    public static LocalDateTime timeNow(){
        // create a clock
        ZoneId zid = ZoneId.of("Europe/Paris");

        // create an LocalDateTime object using now(zoneId)
        LocalDateTime lt
                = LocalDateTime.now(zid);
        return lt;
    }

    public static String passwordHash(String password) {
        String passwordToHash = "password";
        String generatedPassword = null;

        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;

    }

    public static <T> ResponseEntity<T> tryCatchWrap(Callable<ResponseEntity<T>> actions) {
        try {
            return actions.call();
        }
        catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
