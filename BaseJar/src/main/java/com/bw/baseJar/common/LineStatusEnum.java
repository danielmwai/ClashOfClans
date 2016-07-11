package com.bw.baseJar.common;

public enum LineStatusEnum {
    ON_LINE(0), OFF_LINE(1), BATTLE_LINE(2), WAITING_LINE(3);
    private int value;

    private LineStatusEnum(int para) {
        this.value = para;
    }

    public int value() {
        return value;
    }

    public static LineStatusEnum valueOf(int value) {
        switch (value) {
            case 0:
                return ON_LINE;//在线
            case 1:
                return OFF_LINE;//下线
            case 2:
                return BATTLE_LINE;//战斗状态
            case 3:
                return WAITING_LINE;//等待状态    			
            default:
                throw new IllegalArgumentException("no matched.");
        }
    }
}
