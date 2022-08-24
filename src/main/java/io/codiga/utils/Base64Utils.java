package io.codiga.utils;
import java.util.Base64;

public class Base64Utils {

    public static String encodeBase64(String clearText) {
        return Base64.getEncoder().encodeToString(clearText.getBytes());
    }

    public static String decodeBase64(String clearText) {
        return new String(Base64.getDecoder().decode(clearText.getBytes()));
    }
}
