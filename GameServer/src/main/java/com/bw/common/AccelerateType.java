package com.bw.common;

import java.util.HashMap;

/**
 * 加速类型
 *
 * @author zhYou
 */
public enum AccelerateType {
    /**
     * 加速建造
     */
    BUILDING_BUILD(1),
    /**
     * 加速生产士兵
     */
    PRODUCE_CHARACTER(2),
    /**
     * 加速生产魔法
     */
    PRODUCE_SPELL(3),
    /**
     * 加速兵种升级
     */
    UPGRADE_CHARACTER(4),
    /**
     * 加速魔法升级
     */
    UPGRADE_SPELL(5),
    /**
     * 加速升级
     */
    BUILDING_UPGRADE(6);

    private int id;

    private AccelerateType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static final HashMap<Integer, AccelerateType> map = new HashMap<Integer, AccelerateType>();

    static {
        for (AccelerateType at : AccelerateType.values()) {
            map.put(at.getId(), at);
        }
    }

    public static AccelerateType valueOf(Integer id) {
        if (map.containsKey(id)) {
            return map.get(id);
        }
        throw new IllegalArgumentException("accelerate type not found: " + id);
    }
}
