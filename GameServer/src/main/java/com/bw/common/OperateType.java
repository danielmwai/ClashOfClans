package com.bw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作类型
 *
 * @author denny zhao
 */
public enum OperateType {
    /**
     * 0-升级建筑
     */
    BUILDING_UPGRADE(0),
    /**
     * 1-移动建筑
     */
    BUILDING_MOVED(1),
    /**
     * 2-造兵
     */
    PRODUCE_CHARACTER(2),
    /**
     * 3-升级兵种
     */
    UPGRADE_CHARACTER(3),
    /**
     * 4-造魔法
     */
    PRODUCE_SPELL(4),
    /**
     * 5-升级魔法
     */
    UPGRADE_SPELL(5),
    /**
     * 6-购买绿宝石
     */
    BUY_GEM(6),
    /**
     * 7-购买金币
     */
    BUY_GOLDEN(7),
    /**
     * 8-购买药水
     */
    BUY_ELIXIR(8),
    /**
     * 9-建造
     */
    BUILDING_BUILD(9),
    /**
     * 10-收获药水
     */
    HARVEST_ELIXIR(10),
    /**
     * 11-收获金币
     */
    HARVEST_GOLDEN(11),
    /**
     * 12-删除建筑
     */
    REMOVE_BUILD(12),
    /**
     * 13-收集尸体
     */
    REMOVE_GRAVE(13),
    /**
     * 14-购买保护时间
     */
    BUY_SHIELD(14),
    /**
     * 15-取消升级
     */
    CANCEL_UPGRADE(15),
    /**
     * 取消建造
     */
    CANCEL_BUILD(16),
    /**
     * 取消造兵
     */
    CANCEL_PRODUCE_CHARACTER(17),
    /**
     * 加速建造
     */
    ACCELERATE_BUILDING_BUILD(18),
    /**
     * 加速升级
     */
    ACCELERATE_BUILDING_UPGRADE(23),
    /**
     * 加速生产士兵
     */
    ACCELERATE_PRODUCE_CHARACTER(19),
    /**
     * 加速生产魔法
     */
    ACCELERATE_PRODUCE_SPELL(20),
    /**
     * 加速升级士兵
     */
    ACCELERATE_UPGRADE_CHARACTER(21),
    /**
     * 加速升级魔法
     */
    ACCELERATE_UPGRADE_SPELL(22),
    /**
     * 购买资源
     */
    BUY_RESOURCE(24),
    NOTHING(50);

    private int id;

    private static Map<Integer, OperateType> map = new HashMap<Integer, OperateType>();

    static {
        for (OperateType operateType : values()) {
            map.put(operateType.getId(), operateType);
        }
    }

    private OperateType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static OperateType valueOf(int value) {
        if (map.containsKey(value)) {
            return map.get(value);
        } else {
            return NOTHING;
        }
    }

    public boolean isOperate() {
        return !isAccelerate();
    }

    public boolean isAccelerate() {
        return this.id == 18 || this.id == 19 || this.id == 20 || this.id == 21 || this.id
                == 22 || this.id == 23;
    }
}
