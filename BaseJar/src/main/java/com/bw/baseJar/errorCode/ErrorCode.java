package com.bw.baseJar.errorCode;

public enum ErrorCode {
    SUCCESS(0), ERROR(1), SERVER_MAINTENACE(2), MAIL_ADDRESS_EXIST(3), GAME_DATA_MIGRATED(4), EXIST_NOT_FOR_USER(
            5), OVERFFLOW_FRIENDS_COUNT(6), EXIST_THIS_FRIENDS(7), NO_RESULT_FOR_REQUEST(8);

    private int param;

    private ErrorCode(int codeNum) {
        param = codeNum;
    }

    public int value() {
        return param;
    }

    public static ErrorCode valueOf(int value) {
        switch (value) {
            case 0:
                return SUCCESS;
            case 1:
                return ERROR;
            case 2:
                return SERVER_MAINTENACE;
            case 3:
                return MAIL_ADDRESS_EXIST;
            case 4:
                return GAME_DATA_MIGRATED;
            case 5:
                return EXIST_NOT_FOR_USER;
            case 6:
                return OVERFFLOW_FRIENDS_COUNT;
            case 7:
                return EXIST_THIS_FRIENDS;
            case 8:
                return NO_RESULT_FOR_REQUEST;
            default:
                throw new IllegalArgumentException("no matched.");
        }
    }
}
