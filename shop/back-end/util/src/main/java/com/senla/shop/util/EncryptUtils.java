package com.senla.shop.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

public class EncryptUtils {
    public static final String DEFAULT_ENCODING = "UTF-8";
    static BASE64Encoder enc = new BASE64Encoder();

    public static String encodeToken(String text) {
        try {
            return enc.encode(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
