package com.bw.application.utils;

import java.util.UUID;

public class CommonMethod {

    public static long creatVerifyCode() {
        UUID uid = UUID.randomUUID();
        return Math.abs(uid.getMostSignificantBits());
    }
}
