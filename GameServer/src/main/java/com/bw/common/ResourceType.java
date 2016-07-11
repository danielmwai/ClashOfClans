package com.bw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 资源类型 //
 */
public enum ResourceType {
    /**
     * 营火
     */
    TROOP_HOUSING(1),
    /**
     * 兵营
     */
    BARRACK(2),
    /**
     * 科技
     */
    LABORATORY(3),
    /**
     * 联盟城堡
     */
    ALLIANCE_CASTLE(4),
    /**
     * 魔法池
     */
    SPELL_FORGE(5),
    /**
     * 主城
     */
    TOWN_HALL(100),
    /**
     * 药水水井
     */
    ELIXIR_PUMP(200),
    /**
     * 药水水罐
     */
    ELIXIR_STORAGE(201),
    /**
     * 金矿
     */
    GOLD_MINE(202),
    /**
     * 金库
     */
    GOLD_STORAGE(203),
    /**
     * 加农炮
     */
    CANNON(300),
    /**
     * 弓箭塔
     */
    ARCHER_TOWER(301),
    /**
     * 法师塔
     */
    WIZARD_TOWER(302),
    /**
     * 防空塔
     */
    AIR_DEFENSE(303),
    /**
     * 巨炮
     */
    MORTAR(304),
    /**
     * 电塔
     */
    TESLA_TOWER(305),
    /**
     * 墙
     */
    WALL(400),
    /**
     * 工房
     */
    WORKER_BUILDING(500),
    /**
     *
     */
    COMMUNICATIONS_MAST(600),
    /**
     *
     */
    GOBLIN_MAIN_BUILDING(601),
    /**
     *
     */
    GOBLIN_HUT(602),
    NOTHING(50);

    private int id;

    private ResourceType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static final Map<Integer, ResourceType> RESOURCE_TYPE_MAP = new HashMap<Integer, ResourceType>();

    static {
        for (ResourceType type : ResourceType.values()) {
            RESOURCE_TYPE_MAP.put(type.getId(), type);
        }
    }

    public static final ResourceType valueOf(int id) {
        if (RESOURCE_TYPE_MAP.containsKey(id)) {
            return RESOURCE_TYPE_MAP.get(id);
        }
        return ResourceType.NOTHING;
    }
}
