package com.assignment.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;

public class StringUtil {

    private static MessageDigest md;
    private static Random rnd;
    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    private static final int SALT_LENGTH = 7;
    private static final String ALGORITH = "SHA-256";
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("language");

    public static String generateSalt() {
        if (rnd == null) {
            rnd = new Random();
        }
        StringBuilder salt = new StringBuilder();
        while (salt.length() < SALT_LENGTH) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public static String hashPassword(String password, String salt) {
        try {
            if (md == null) {
                md = MessageDigest.getInstance(ALGORITH);
            }
            md.update(password.getBytes());
            md.update(salt.getBytes());
            final byte[] hash = md.digest();
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; ++i) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ignored) {
            return "";
        }
    }

    public static boolean comparePasswordWithSalt(String inputPassword, String salt, String hashPassword) {
        return hashPassword(inputPassword, salt).equals(hashPassword);
    }

    public static String generateActivationCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    public static long generateActivationCodeExpiredTime(){
        return (Calendar.getInstance().getTimeInMillis() + 1000*60*15);
    }

    public static String getBundle(String key) {
        return resourceBundle.getString(key);
    }

    public static void changeLanguage(HttpServletRequest request) {
        resourceBundle = ResourceBundle.getBundle("language");
        ServletContext context = request.getSession().getServletContext();
        context.setAttribute("bundle", resourceBundle);
    }
}
