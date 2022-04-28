package com.duogglong.tm.core.utils;

import java.util.Base64;

public class Base64Utils {
    public static String encode(String value) {
        return String.valueOf(Base64.getMimeEncoder().encodeToString(value.getBytes()));
    }

    public static String decode(String value) {
        return String.valueOf(Base64.getUrlDecoder().decode(value));
    }
}
