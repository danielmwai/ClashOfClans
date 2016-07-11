package com.commonSocket.net;

public enum IoMode {
    HTTP_CONNECT(0), LONG_CONNECT(1);

    int value;

    private IoMode(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static IoMode valueOf(int i) {
        switch (i) {
            case 0:
                return HTTP_CONNECT;
            case 1:
                return LONG_CONNECT;
        }
        throw new IllegalArgumentException("no matched.");
    }
}
