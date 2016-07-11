package com.commonSocket.net.util;

public class SequenceUtil {

    private static int sequence = 0;

    public static synchronized int generateSequence() {
        if (sequence == 2147483647) {
            sequence = 0;
        }
        return ++sequence;
    }
}
