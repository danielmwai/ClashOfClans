package com.commonSocket.net.util;

public class PnpcAdler32 {
    //65521  value:1111111111110001
    //65521L value:1111111111110001
    //65521H value:0xfff1

    private static final int MOD_ADLER = 65521;
    private static final int NMAX = 5552;

    public static long adler(byte[] data) {
        long baseA = 1L;
        long baseB = 0L;
        int length = data.length;
        int i = 0;
        while (length > 0) {
            int tlen = length > 5552 ? 5552 : length;
            length -= tlen;
            do {
                baseA += data[(i++)];
                baseB += baseA;
                tlen--;
            } while (tlen > 0);

            baseA %= 65521L;
            baseB %= 65521L;
        }

        return baseB << 16 | baseA;
    }

    public static long adler32(byte[] data) {
        int length = data.length;
        long adler = 1L;
        long baseA = adler & 0xFFFF;//1
        long baseB = adler >> 16 & 0xFFFF;//0

        for (int i = 0; i < length; i++) {
            baseA = (baseA + (data[i] & 0xFF)) % 65521L;
            baseB = (baseA + baseB) % 65521L;
        }
        return (baseB << 16) + baseA;
    }

    public static long adler32u(byte[] buf) {
        if (buf == null) {
            return 1L;
        }
        int index = 0;
        int length = buf.length;
        long adler = 1L;
        long baseA = adler & 0xFFFF;
        long baseB = adler >> 16 & 0xFFFF;

        while (length > 0) {
            int k = length < 5552 ? length : 5552;
            length -= k;
            while (k >= 16) {
                baseA += (buf[(index++)] & 0xFF);
                baseB += baseA;
                k -= 16;
            }
            if (k != 0) {
                do {
                    baseA += (buf[(index++)] & 0xFF);
                    baseB += baseA;
                    k--;
                } while (k != 0);
            }
            baseA %= 65521L;
            baseB %= 65521L;
        }
        return baseB << 16 | baseA;
    }
}
