package com.commonSocket.net.util;

import java.lang.reflect.Method;

public class AntiCrack {

    public static Throwable fake(Throwable throwable, Throwable throwable1) {
        try {
            throwable.getClass().getMethod("initCause", new Class[]{Throwable.class}).invoke(throwable, new Object[]{throwable1});
        } catch (Exception localException) {
        }
        return throwable;
    }
}
