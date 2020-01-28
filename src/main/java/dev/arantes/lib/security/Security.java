package dev.arantes.lib.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    private final static char[] hexValues = "0123456789ABCDEF".toCharArray();

    public static String genHash(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.reset();
        return convertByteToString(messageDigest.digest(text.getBytes()));
    }

    private static String convertByteToString(byte[] bytes) {
        char[] hex = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hex[i * 2] = hexValues[v >>> 4];
            hex[i * 2 + 1] = hexValues[v & 0x0F];
        }
        return new String(hex);
    }
}
