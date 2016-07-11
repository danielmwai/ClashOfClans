package com.bw.application.utils;

import java.util.*;

public class CommonMethod {

    public static long creatVerifyCode() {
        final UUID uid = UUID.randomUUID();
        return Math.abs(uid.getMostSignificantBits());
    }
}
