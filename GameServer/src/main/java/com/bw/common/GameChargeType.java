package com.bw.common;

/**
 * 游戏币交易类型
 *
 * @author denny zhao
 */
public enum GameChargeType {
    /**
     * 金币
     */
    GOLDEN_GAME_CHARGE(0),
    /**
     * 药水
     */
    ELIXIR_GAME_CHARGE(1),
    /**
     * 宝石
     */
    GEM_GAME_CHARGE(2),
    NOTHING(50);

    private int param;

    private GameChargeType(int codeNum) {
        param = codeNum;
    }

    public int value() {
        return param;
    }

    public static GameChargeType valueOf(int value) {
        switch (value) {
            case 0:
                return GOLDEN_GAME_CHARGE;//金币
            case 1:
                return ELIXIR_GAME_CHARGE;//药水
            case 2:
                return GEM_GAME_CHARGE;//绿宝石
            default:
                return NOTHING;//没有找到匹配的类型
        }
    }
}
