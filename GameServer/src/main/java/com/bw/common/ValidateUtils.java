package com.bw.common;

public class ValidateUtils {

    public static boolean isNotNull(Object obj) {
        if (obj == null || obj.equals("")) {
            return false;
        }
        return true;
    }

}
